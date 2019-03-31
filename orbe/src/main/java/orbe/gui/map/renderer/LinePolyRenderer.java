/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.util.ValueHandler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.line.OrbePolyLine;
import orbe.model.element.line.SegmentIterator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * Renderer pour les lignes libres.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LinePolyRenderer.java,v 1.5 2006/12/04 14:10:48 guinnessman Exp $
 */
public class LinePolyRenderer extends AbstractLineRenderer<OrbePolyLine> {

	public boolean hasToRefresh(final OrbePolyLine line, final OrbeMap map, final ViewSettings viewSettings, final Double zone) {
		final ValueHandler<Boolean> ok = new ValueHandler<Boolean>();
		line.forEachSegment(new SegmentIterator<HXPoint2D>() {

			public boolean onSegment(HXPoint2D hxa, HXPoint2D hxb) {
				Rectangle r = getScreenZone(map, viewSettings, line, hxa, hxb);
				ok.setValue(r.intersects(zone));
				return !ok.getValue();
			}

		});
		return ok.getValue();
	}

	public void render(OrbePolyLine line, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		// Définit le style
		setStyle(line, map, viewSettings, g);
		// Construction d'une forme
		GeneralPath path = getScreenPath(map, viewSettings, line);
		// Dessin de la forme
		g.draw(path);
	}

	protected GeneralPath getScreenPath(OrbeMap map, ViewSettings viewSettings, OrbePolyLine line) {
		GeneralPath path = new GeneralPath();
		List<HXPoint2D> points = line.getPoints();
		boolean start = true;
		for (HXPoint2D hx : points) {
			// Conversion
			Point p = convertHXToScreen(map, viewSettings, hx);
			// Ajout
			if (start) {
				path.moveTo(p.x, p.y);
				start = false;
			} else {
				path.lineTo(p.x, p.y);
			}
		}
		return path;
	}

	private Point convertHXToScreen(OrbeMap map, ViewSettings viewSettings, HXPoint2D hx) {
		PointDecimal px = ScaleMath.scaleHXToPX(map, hx);
		Point p = ScaleMath.scalePXToScreen(map, viewSettings, px);
		return p;
	}

	public Rectangle getLastRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbePolyLine line) {
		List<HXPoint2D> points = line.getPoints();
		int size = points.size();
		if (size > 1) {
			// 2 derniers points
			HXPoint2D hx2 = points.get(size - 1);
			HXPoint2D hx1 = points.get(size - 2);
			return getScreenZone(map, viewSettings, line, hx2, hx1);
		} else {
			return null;
		}
	}

	protected Rectangle getScreenZone(OrbeMap map, ViewSettings viewSettings, OrbePolyLine line, HXPoint2D hx2, HXPoint2D hx1) {
		// Conversion
		Point p1 = convertHXToScreen(map, viewSettings, hx1);
		Point p2 = convertHXToScreen(map, viewSettings, hx2);
		// Zone
		Rectangle r = new Rectangle(p1);
		r.add(p2);
		// Prendre en compte la largeur de la ligne
		grow(map, viewSettings, line, r);
		// Ok
		return r;
	}

	public Rectangle getRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbePolyLine line) {
		HXPoint2D tl = null;
		HXPoint2D br = null;
		List<HXPoint2D> points = line.getPoints();
		for (HXPoint2D hx : points) {
			if (tl == null) {
				tl = new HXPoint2D(hx);
				br = new HXPoint2D(hx);
			} else {
				tl = new HXPoint2D(Math.min(tl.x, hx.x), Math.min(tl.y, hx.y));
				br = new HXPoint2D(Math.max(br.x, hx.x), Math.max(br.y, hx.y));
			}
		}
		Rectangle r = getScreenZone(map, viewSettings, line, tl, br);
		// Ok
		return r;
	}

	@Override
	protected LineString getPXGeometry(GeometryFactory factory, OrbeMap map, OrbePolyLine line) {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
		for (HXPoint2D hx : line.getPoints()) {
			// Converts to PX
			PointDecimal px = ScaleMath.scaleHXToPX(map, hx);
			// JTS coordinates
			Coordinate coordinate = new Coordinate(px.x, px.y);
			coordinates.add(coordinate);
		}
		return factory.createLineString(coordinates.toArray(new Coordinate[0]));
	}
	
	public Shape getOutline(OrbeMap map, ViewSettings viewSettings, OrbePolyLine line) {
		return getScreenPath(map, viewSettings, line);
	}

}
