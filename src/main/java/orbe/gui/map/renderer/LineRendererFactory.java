/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.renderer;

import java.util.HashMap;

import orbe.model.element.line.LineForm;

/**
 * Renderers for lines.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LineRendererFactory.java,v 1.3 2006/12/04 18:15:52 guinnessman Exp $
 */
public class LineRendererFactory {
	/**
	 * Unique instance
	 */
	private static LineRendererFactory instance = null;

	/**
	 * Get the instance
	 */
	public static LineRendererFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized LineRendererFactory createInstance() {
		if (instance != null) {
			return instance;
		} else {
			LineRendererFactory temp = new LineRendererFactory();
			instance = temp;
			return instance;
		}
	}

	/**
	 * All instances
	 */
	private HashMap<LineForm, LineRenderer> renderers = new HashMap<LineForm, LineRenderer>();

	/**
	 * Initialization
	 */
	private LineRendererFactory() {
		renderers.put(LineForm.POLY, new LinePolyRenderer());
		renderers.put(LineForm.HEX, new LineHexRenderer());
		renderers.put(LineForm.EDGE, new LineEdgeRenderer());
	}

	/**
	 * Renderer
	 */
	public LineRenderer getInstance(LineForm form) {
		LineRenderer renderer = renderers.get(form);
		if (renderer != null) {
			return renderer;
		} else {
			throw new IllegalStateException("No line renderer is registered for form " + form);
		}
	}
}
