/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.view;

import java.awt.Dimension;
import java.math.BigDecimal;

import orbe.gui.map.core.OrbeRenderer;
import orbe.gui.map.core.OrbeView;
import orbe.gui.map.core.ViewSettings;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.Scale;

public abstract class AbstractOrbeView implements OrbeView {

	private OrbeRenderer renderer;

	private ViewSettings viewSettings;
	
	public AbstractOrbeView(OrbeMap map, ViewSettings settings) {
		viewSettings = settings;
	}

	public OrbeRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(OrbeRenderer renderer) {
		this.renderer = renderer;
	}

	public Dimension getDisplaySize(OrbeMap map) {
		if (map == null) {
			return new Dimension(0, 0);
		} else {
			BigDecimal zoom = viewSettings.getZoom();

			// Dimensions en Hex
			int mapWidth = map.getWidth();
			int mapHeight = map.getHeight();

			// Echelle d'un hex
			Scale scale = map.getSettings().getScale();

			// Conversion d'un hex en pixels
			PointDecimal pxHex = new HXGraphics(scale).toPixels(new HXPoint(mapWidth, mapHeight));

			double width = zoom.doubleValue() * (pxHex.x);
			double height = zoom.doubleValue() * (pxHex.y);

			int pixelWidth = (int) width;
			int pixelHeight = (int) height;

			return new Dimension(pixelWidth, pixelHeight);
		}
	}

	public ViewSettings getViewSettings() {
		return viewSettings;
	}

}
