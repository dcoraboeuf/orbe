/*
 * Created on Oct 24, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;
import orbe.model.hex.Hex;

public class LayerHexColor extends AbstractLayerHex {

	public Layer getId() {
		return Layer.HEX_COLOR;
	}

	@Override
	protected void drawHex(Graphics2D g, int i, int j, Hex hex, Shape shape) {
		// Background color
		Color color = hex.getFillColor();
		// Drawing
		g.setColor(color);
		g.fill(shape);
	}

	@Override
	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		Object oldValue = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		try {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			super.paint(map, viewSettings, g, zone);
		} finally {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldValue);
		}
	}

}
