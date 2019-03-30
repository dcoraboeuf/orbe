/*
 * Created on Oct 24, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXGraphics;
import orbe.hex.HXRect;
import orbe.model.OrbeMap;
import orbe.model.OrbeSettings;
import orbe.model.Scale;
import orbe.model.hex.Hex;
import orbe.model.hex.HexMap;

public abstract class AbstractLayerHex extends AbstractLayer {

	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		HexMap hexMap = map.getHexMap();
		OrbeSettings settings = map.getSettings();
		Scale scale = settings.getScale();
		HXGraphics hxGraphics = new HXGraphics(scale);
		// Calcul de la zone concernée en PX
		Double pxZone = ScaleMath.scaleScreenToPX(map, viewSettings, zone);
		// Calcul de la zone concernée en Hex
		HXRect hxRect = hxGraphics.toApproxHexs(pxZone);
		// Bounding
		hxRect = hxRect.intersect(getHXMapBounds(map));
		int i1 = hxRect.getMinI();
		int j1 = hxRect.getMinJ();
		int i2 = hxRect.getMaxI();
		int j2 = hxRect.getMaxJ();
		// Dessin de tous les hexs
		for (int j = j1; j <= j2; j++) {
			for (int i = i1; i <= i2; i++) {
				// Hex to draw
				Hex hex = hexMap.getHex(i, j);
				// Shape of the hex
				Shape shape = hxGraphics.getHexShape(i, j);
				// Zoom
				shape = viewSettings.zoom(shape);
				// Dessin
				drawHex(g, i, j, hex, shape);
			}
		}
	}

	protected abstract void drawHex(Graphics2D g, int i, int j, Hex hex, Shape shape);

}
