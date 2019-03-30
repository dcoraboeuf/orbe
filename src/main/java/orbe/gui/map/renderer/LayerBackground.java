/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;

public class LayerBackground extends AbstractLayer {
	
	/**
	 * Default constructor.
	 */
	public LayerBackground() {
	}

	public Layer getId() {
		return Layer.BACKGROUND;
	}

	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		g.setColor(map.getSettings().getBackground());
		g.fill(zone);
	}

}
