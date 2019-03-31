/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.tool;

import java.awt.Point;
import java.awt.event.MouseEvent;

import orbe.gui.map.core.OrbeView;
import orbe.gui.map.core.ToolSettings;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;

public abstract class AbstractToolMap extends AbstractTool {

	protected OrbeMap getMap() {
		return getControler().getContext().getMap();
	}

	protected OrbeView getView() {
		return getControler().getView();
	}

	protected ViewSettings getViewSettings() {
		return getView().getViewSettings();
	}

	protected ToolSettings geToolSettings() {
		return getControler().getToolSettings();
	}

	public PointDecimal getPX(MouseEvent e) {
		Point point = e.getPoint();
		return getPX(point);
	}

	public PointDecimal getPX(Point point) {
		// Converts to absolute pixels
		PointDecimal pointPX = ScaleMath.scaleScreenToPX(getMap(), getViewSettings(), point);
		// Ok
		return pointPX;
	}

	/**
	 * Conversion d'un point Screen vers un point HX.
	 * 
	 * @param point
	 *            Point exprim� dans le référentiel Screen.
	 * @return Point exprim� dans le référentiel HX.
	 */
	public HXPoint2D getHX(Point point) {
		// Vers PX d'abord
		PointDecimal px = getPX(point);
		// Puis vers HX
		return getHX(px);
	}

	/**
	 * Conversion d'un point PX vers un point HX.
	 * 
	 * @param px
	 *            Point exprim� dans le référentiel PX.
	 * @return Point exprim� dans le référentiel HX.
	 */
	public HXPoint2D getHX(PointDecimal px) {
		return ScaleMath.scalePXToHX(getMap(), px);
	}

	public Point getScreen(PointDecimal px) {
		return ScaleMath.scalePXToScreen(getMap(), getViewSettings(), px);
	}

}
