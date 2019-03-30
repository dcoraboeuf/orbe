/*
 * Created on Nov 6, 2006
 */
package orbe.gui.map.options;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Priority;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.view.AbstractToolbar;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.tool.Tool;
import orbe.gui.message.OrbeMessageContextChanged;
import orbe.gui.message.OrbeMessageTool;

/**
 * Cet élément de la barre d'état affiche les options de l'outil couramment
 * sélectionné.
 * 
 * @author Guinness Man
 * @version $Id: StatusComponentToolOptions.java,v 1.1 2006/11/06 16:06:04
 *          guinnessman Exp $
 */
public class ToolOptionsBar extends AbstractToolbar {

	protected static final int PANEL_HEIGHT = 30;

	private JPanel panel;

	private JLabel labelTool;

	private JPanel panelTool;

	private OrbeContext orbeContext;

	protected void onContextChanged(OrbeContext context) {
		orbeContext = context;
		clean();
	}

	protected void onToolChanged(Tool tool) {
		if (orbeContext == null || tool == null) {
			clean();
		} else {
			String id = tool.getId();
			// Title of the tool
			String toolTitle = GUIStrings.get("Tool." + id);
			labelTool.setText(toolTitle);
			labelTool.setEnabled(true);
			// Options for the tool
			PanelOptions panelOptions = tool.getPanelOptions();
			panelTool.removeAll();
			if (panelOptions != null) {
				OrbeControler controler = tool.getControler();
				panelOptions.setup(controler);
				panelTool.add(panelOptions, BorderLayout.CENTER);
			}
			panel.doLayout();
		}
	}

	private void clean() {
		labelTool.setText(GUIStrings.get("Tool.None"));
		labelTool.setEnabled(false);
		panelTool.removeAll();
		panel.doLayout();
	}
	
	public JToolBar createToolbar(View view) {
		// Creates the toolbar
		JToolBar j = super.createToolbar(view);
		// Creates the content of the toolbar
		createToolbarComponent(view);
		j.add(panel);
		
		// Register for the update of the map
		Bus.get().subscribe(OrbeMessageContextChanged.class, new Subscriber<OrbeMessageContextChanged>() {

			public void receive(OrbeMessageContextChanged message) {
				OrbeContext context = message.getValue();
				onContextChanged(context);
			}

		});
		
		// Register for the update of the tool
		/*
		 * Uses low priority in order to insure that this method is called last.
		 */
		Bus.get().subscribe(OrbeMessageTool.class, new Subscriber<OrbeMessageTool>() {

			public void receive(OrbeMessageTool message) {
				Tool tool = message.getValue();
				onToolChanged(tool);
			}

		}, Priority.LOW);
		
		// Ok
		return j;
	}

	protected void createToolbarComponent(View view) {
		panel = new JPanel(new BorderLayout(4, 4));
		panel.setMinimumSize(new Dimension(100, PANEL_HEIGHT));
		panel.setPreferredSize(new Dimension(100, PANEL_HEIGHT));

		labelTool = new JLabel(" ");
		panel.add(labelTool, BorderLayout.LINE_START);

		panelTool = new JPanel(new BorderLayout());
		panel.add(panelTool, BorderLayout.CENTER);
	}

}
