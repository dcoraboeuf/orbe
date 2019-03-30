/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXGraphics;
import orbe.hex.HXRect;
import orbe.model.Grid;
import orbe.model.OrbeMap;
import orbe.model.OrbeSettings;

public class LayerGrid extends AbstractLayer {

	public Layer getId() {
		return Layer.GRID;
	}

	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		OrbeSettings settings = map.getSettings();
		Grid grid = settings.getGrid();
		HXGraphics hxGraphics = new HXGraphics(settings.getScale());
		// Calcul de la zone concernée en PX
		Double pxZone = ScaleMath.scaleScreenToPX(map, viewSettings, zone);
		// Calcul de la zone concernée en Hex
		HXRect hxRect = hxGraphics.toApproxHexs(pxZone);
		// Calcul de la grille pour cette zone
		Shape gridShape = hxGraphics.getGridShape(hxRect, getHXMapBounds(map));
		// Zoom scaling
		gridShape = viewSettings.zoom(gridShape);
		// Couleur de la grille
		Color color = grid.getColor();
		color = LayerUtils.transparentColor(color, grid.isTransparent());
		g.setColor(color);
		// Dessin de la grille
		g.setStroke(new BasicStroke());
		g.draw(gridShape);
	}

}
