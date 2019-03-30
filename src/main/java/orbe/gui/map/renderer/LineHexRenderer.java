/*
 * Created on 4 déc. 06
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.util.ValueHandler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXGeom;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.hex.HXPointCollector;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.line.OrbeHexLine;
import orbe.model.element.line.SegmentIterator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * Rendu pour les lignes de centres d'hexagones.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LineHexRenderer.java,v 1.3 2007/09/10 12:43:28 guinnessman Exp $
 */
public class LineHexRenderer extends AbstractLineRenderer<OrbeHexLine> {

	public Rectangle getLastRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbeHexLine line) {
		List<HXPoint> points = line.getPoints();
		int size = points.size();
		if (size > 1) {
			// 2 derniers points
			HXPoint hx2 = points.get(size - 1);
			HXPoint hx1 = points.get(size - 2);
			return getScreenZone(map, viewSettings, line, hx2, hx1);
		} else {
			return null;
		}
	}

	public Rectangle getRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbeHexLine line) {
		HXPoint tl = null;
		HXPoint br = null;
		List<HXPoint> points = line.getPoints();
		for (HXPoint hx : points) {
			if (tl == null) {
				tl = new HXPoint(hx);
				br = new HXPoint(hx);
			} else {
				tl = new HXPoint(Math.min(tl.i, hx.i), Math.min(tl.j, hx.j));
				br = new HXPoint(Math.max(br.i, hx.i), Math.max(br.j, hx.j));
			}
		}
		Rectangle r = getScreenZone(map, viewSettings, line, tl, br);
		// Ok
		return r;
	}

	public boolean hasToRefresh(final OrbeHexLine line, final OrbeMap map, final ViewSettings viewSettings, final Double zone) {
		final ValueHandler<Boolean> ok = new ValueHandler<Boolean>();
		ok.setValue(false);
		line.forEachSegment(new SegmentIterator<HXPoint>() {

			public boolean onSegment(HXPoint hxa, HXPoint hxb) {
				Rectangle r = getScreenZone(map, viewSettings, line, hxa, hxb);
				ok.setValue(r.intersects(zone));
				return !ok.getValue();
			}

		});
		return ok.getValue();
	}

	public void render(OrbeHexLine line, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		// Définit le style
		setStyle(line, map, viewSettings, g);
		// Construction d'une forme
		Shape path = getScreenPath(map, viewSettings, line);
		// Dessin de la forme
		g.draw(path);
	}

	protected Shape getScreenPath(OrbeMap map, ViewSettings viewSettings, OrbeHexLine line) {
		HXPoint[] hxs = line.getPoints().toArray(new HXPoint[0]);
		Shape path = createScreenPath(map, viewSettings, hxs);
		return path;
	}

	public GeneralPath createPXPath(OrbeMap map, ViewSettings viewSettings, HXPoint... hxs) {
		if (hxs == null || hxs.length < 2) {
			return null;
		} else {
			HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
			// Collects all HX points
			HXPointCollector collector = new HXPointCollector();
			// Ajout du tout premier point
			collector.forHX(hxs[0]);
			// Parcours des points
			for (int i = 1; i < hxs.length; i++) {
				HXPoint hxa = hxs[i - 1];
				HXPoint hxb = hxs[i];
				HXGeom.line(hxa, hxb, false, true, collector);
			}
			/*
			 * Tous les points ont été obtenus. On les relie maintenant sous la
			 * forme d'un chemin.
			 */
			GeneralPath path = new GeneralPath();
			boolean start = true;
			for (HXPoint hx : collector.getPoints()) {
				// Conversion de coordonnées
				PointDecimal px = hxg.getHexCenter(hx);
				// Ajout
				if (start) {
					path.moveTo((float) px.x, (float) px.y);
					start = false;
				} else {
					path.lineTo((float) px.x, (float) px.y);
				}
			}
			// Ok
			return path;
		}
	}

	public Shape createScreenPath(OrbeMap map, ViewSettings viewSettings, HXPoint... hxs) {
		// Chemin en coordonnées PX
		GeneralPath path = createPXPath(map, viewSettings, hxs);
		// Zoom
		return viewSettings.zoom(path);
	}

	protected Rectangle getScreenZone(OrbeMap map, ViewSettings viewSettings, OrbeHexLine line, HXPoint hx1, HXPoint hx2) {
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		// Collects all hexes between hx1 and hx2
		HXPointCollector collector = new HXPointCollector();
		HXGeom.line(hx1, hx2, true, true, collector);
		// Builds the zone
		Rectangle2D.Double zone = null;
		for (HXPoint hx : collector.getPoints()) {
			Double bounds = hxg.getHexBounds(hx.i, hx.j);
			if (zone == null) {
				zone = bounds;
			} else {
				Double.union(bounds, zone, zone);
			}
		}
		// Conversion en coordonnées écran
		Rectangle r = ScaleMath.scalePXToScreen(map, viewSettings, zone);
		grow(map, viewSettings, line, r);
		// Ok
		return r;
	}

	@Override
	protected LineString getPXGeometry(GeometryFactory factory, OrbeMap map, OrbeHexLine line) {
		List<HXPoint> hxs = line.getPoints();
		if (hxs.size() < 2) {
			return null;
		} else {
			HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
			// Collects all HX points
			HXPointCollector collector = new HXPointCollector();
			// Ajout du tout premier point
			collector.forHX(hxs.get(0));
			// Parcours des points
			for (int i = 1; i < hxs.size(); i++) {
				HXPoint hxa = hxs.get(i - 1);
				HXPoint hxb = hxs.get(i);
				HXGeom.line(hxa, hxb, false, true, collector);
			}
			/*
			 * Tous les points ont été obtenus. On les relie maintenant sous la
			 * forme d'un chemin.
			 */
			ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
			for (HXPoint hx : collector.getPoints()) {
				// Conversion de coordonnées
				PointDecimal px = hxg.getHexCenter(hx);
				// Ajout
				coordinates.add(new Coordinate(px.x, px.y));
			}
			// Ok
			return factory.createLineString(coordinates.toArray(new Coordinate[0]));
		}
	}

	public Shape getOutline(OrbeMap map, ViewSettings viewSettings, OrbeHexLine line) {
		return getScreenPath(map, viewSettings, line);
	}

}
