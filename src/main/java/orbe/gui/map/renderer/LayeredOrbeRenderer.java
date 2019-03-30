/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;

import orbe.gui.jmx.OrbeManager;
import orbe.gui.map.core.OrbeRenderer;
import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LayeredOrbeRenderer implements OrbeRenderer {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(LayeredOrbeRenderer.class);

	private ArrayList<OrbeLayer> layers;

	public LayeredOrbeRenderer() {
		layers = new ArrayList<OrbeLayer>();
		layers.add(new LayerBackground());
		layers.add(new LayerHexColor());
		layers.add(new LayerHexSymbol());
		layers.add(new LayerHexID());
		layers.add(new LayerGrid());
		layers.add(new LayerLine());
		layers.add(new LayerText());
	}

	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		boolean debug = log.isDebugEnabled() && OrbeManager.getInstance().getLayeredOrbeRendererDebug();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (OrbeLayer layer : layers) {
			if (layer.isVisible(map)) {
				long start = 0;
				if (debug) {
					start = System.currentTimeMillis();
				}
				layer.paint(map, viewSettings, g, zone);
				if (debug) {
					long end = System.currentTimeMillis();
					log.debug((end - start) + " ms for rendering " + layer.getId() + " for " + zone);
				}
			}
		}
	}

}
