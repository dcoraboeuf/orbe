/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.tool.line;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import orbe.gui.map.core.ToolSettings;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.LineRenderer;
import orbe.gui.map.renderer.LineRendererFactory;
import orbe.gui.map.tool.ToolLine;
import orbe.model.OrbeMap;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbeLine;

/**
 * Spécialisation de l'outil ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolLineDelegate.java,v 1.3 2006/12/04 14:10:49 guinnessman Exp $
 */
public abstract class ToolLineDelegate {

	/**
	 * Outil parent
	 */
	private ToolLine toolLine;

	/**
	 * Initialisation.
	 */
	public ToolLineDelegate(ToolLine tool) {
		toolLine = tool;
	}

	/**
	 * @return Outil parent
	 */
	public ToolLine getToolLine() {
		return toolLine;
	}

	/**
	 * Désactive l'outil en cours
	 */
	public abstract void stop();

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected OrbeMap getMap() {
		return getToolLine().getControler().getContext().getMap();
	}

	protected ViewSettings getViewSettings() {
		return getToolLine().getControler().getView().getViewSettings();
	}

	protected ToolSettings getToolSettings() {
		return getToolLine().getControler().getToolSettings();
	}

	/**
	 * Détection d'une ligne à proximité du point
	 */
	@SuppressWarnings("unchecked")
	protected OrbeLine getLine(Point p) {
		List<OrbeLine> elements = getMap().getLineList().getElements();
		for (OrbeLine line : elements) {
			// Fomr of the line
			LineForm form = line.getForm();
			// Associated renderer
			LineRenderer renderer = LineRendererFactory.getInstance().getInstance(form);
			// Checks the hover
			if (renderer.isOver(getMap(), getViewSettings(), line, p)) {
				return line;
			}
		}
		// No line has been found
		return null;
	}

}
