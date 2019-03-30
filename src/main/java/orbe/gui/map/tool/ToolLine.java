/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.tool;

import java.awt.event.MouseEvent;
import java.util.Stack;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.map.options.PanelOptionsLine;
import orbe.gui.map.tool.line.ToolLineDelegate;
import orbe.gui.map.tool.line.ToolLineDelegateEdge;
import orbe.gui.map.tool.line.ToolLineDelegateHex;
import orbe.gui.map.tool.line.ToolLineDelegatePoly;
import orbe.gui.message.OrbeMessageLineForm;
import orbe.model.element.line.LineForm;

/**
 * Outil de création/modification de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolLine.java,v 1.4 2006/12/04 18:15:52 guinnessman Exp $
 */
public class ToolLine extends AbstractTool {

	/**
	 * Délégations
	 */
	private Stack<ToolLineDelegate> delegates = new Stack<ToolLineDelegate>();

	/**
	 * Initialisation
	 */
	public ToolLine() {
		/*
		 * Délégation par défaut
		 */
		ToolLineDelegatePoly delegate = new ToolLineDelegatePoly(this);
		delegates.push(delegate);
		/**
		 * Changement de délégation
		 */
		Bus.get().subscribe(OrbeMessageLineForm.class, new Subscriber<OrbeMessageLineForm>() {

			public void receive(OrbeMessageLineForm message) {
				// Nettoyage
				while (!delegates.isEmpty()) {
					ToolLineDelegate delegate = delegates.pop();
					delegate.stop();
				}
				// Ok
				LineForm form = message.getValue();
				switch (form) {
				case POLY: {
					ToolLineDelegatePoly delegate = new ToolLineDelegatePoly(ToolLine.this);
					delegates.push(delegate);
				}
					break;
				case HEX: {
					ToolLineDelegateHex delegate = new ToolLineDelegateHex(ToolLine.this);
					delegates.push(delegate);
				}
					break;
				case EDGE: {
					ToolLineDelegateEdge delegate = new ToolLineDelegateEdge(ToolLine.this);
					delegates.push(delegate);
				}
					break;
				}
			}

		});
	}

	/**
	 * @return ID
	 * @see orbe.gui.map.tool.Tool#getId()
	 */
	public String getId() {
		return ITool.LINE;
	}

	/**
	 * Options
	 */
	private PanelOptionsLine panelOptions;

	@Override
	public PanelOptions getPanelOptions() {
		if (panelOptions == null) {
			panelOptions = new PanelOptionsLine();
		}
		return panelOptions;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		delegates.peek().mouseClicked(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		delegates.peek().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		delegates.peek().mouseMoved(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		delegates.peek().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		delegates.peek().mouseReleased(e);
	}

	public void pushDelegate(ToolLineDelegate delegate) {
		delegates.push(delegate);
	}

	public void popDelegate(ToolLineDelegate delegate) {
		ToolLineDelegate d = delegates.pop();
		if (d != delegate) {
			throw new IllegalStateException("Inconsistent stack of tool delegates");
		}
	}
	
	@Override
	public void unregister() {
		while (!delegates.isEmpty()) {
			ToolLineDelegate delegate = delegates.pop();
			delegate.stop();
		}
	}

}
