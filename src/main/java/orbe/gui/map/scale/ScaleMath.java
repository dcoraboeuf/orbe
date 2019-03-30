/*
 * Created on Oct 5, 2006
 */
package orbe.gui.map.scale;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.Scale;

public class ScaleMath {

	public static double scaleScreenToPX(OrbeMap map, ViewSettings viewSettings, int px) {
		double zoom = viewSettings.getZoom().doubleValue();
		double pixels = px / zoom;
		return pixels;
	}

	public static PointDecimal scaleScreenToPX(OrbeMap map, ViewSettings viewSettings, Point point) {
		double dx = scaleScreenToPX(map, viewSettings, point.x);
		double dy = scaleScreenToPX(map, viewSettings, point.y);
		return new PointDecimal(dx, dy);
	}

	public static Double scaleScreenToPX(OrbeMap map, ViewSettings viewSettings, Double zone) {
		double dx = scaleScreenToPX(map, viewSettings, (int) zone.x);
		double dy = scaleScreenToPX(map, viewSettings, (int) zone.y);
		double dwidth = scaleScreenToPX(map, viewSettings, (int) zone.width);
		double dheight = scaleScreenToPX(map, viewSettings, (int) zone.height);
		return new Double(dx, dy, dwidth, dheight);
	}

	public static Point scalePXToScreen(OrbeMap map, ViewSettings viewSettings, PointDecimal pointPX) {
		int ix = scalePXToScreen(map, viewSettings, pointPX.x);
		int iy = scalePXToScreen(map, viewSettings, pointPX.y);
		return new Point(ix, iy);
	}

	public static int scalePXToScreen(OrbeMap map, ViewSettings viewSettings, double px) {
		double zoom = viewSettings.getZoom().doubleValue();
		int screen = (int) (px * zoom);
		return screen;
	}

	public static Rectangle scalePXToScreen(OrbeMap map, ViewSettings viewSettings, Double zone) {
		int x = scalePXToScreen(map, viewSettings, zone.x);
		int y = scalePXToScreen(map, viewSettings, zone.y);
		int width = scalePXToScreen(map, viewSettings, zone.width);
		int height = scalePXToScreen(map, viewSettings, zone.height);
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Conversion d'un point PX vers un point HX.
	 * 
	 * @param px
	 *            Point exprimé dans le référentiel PX.
	 * @return Point exprimé dans le référentiel HX.
	 */
	public static HXPoint2D scalePXToHX(OrbeMap map, PointDecimal px) {
		Scale scale = map.getSettings().getScale();
		HXGraphics hxg = new HXGraphics(scale);
		return hxg.scalePXToHX(px);
	}

	/**
	 * Conversion d'un point HX vers un point PX.
	 * 
	 * @param hx
	 *            Point exprimé dans le référentiel HX.
	 * @return Point exprimé dans le référentiel PX.
	 */
	public static PointDecimal scaleHXToPX(OrbeMap map, HXPoint2D hx) {
		Scale scale = map.getSettings().getScale();
		HXGraphics hxg = new HXGraphics(scale);
		return hxg.scaleHXToPX(hx);
	}

}
