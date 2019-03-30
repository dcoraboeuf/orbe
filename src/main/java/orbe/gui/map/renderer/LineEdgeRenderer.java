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
import orbe.hex.HXCorner;
import orbe.hex.HXCornerCollector;
import orbe.hex.HXGeom;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.line.OrbeEdgeLine;
import orbe.model.element.line.SegmentIterator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * Rendu pour les lignes de contours d'hexagones.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LineEdgeRenderer.java,v 1.1 2006/12/04 18:15:52 guinnessman Exp $
 */
public class LineEdgeRenderer extends AbstractLineRenderer<OrbeEdgeLine> {

	public Rectangle getLastRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbeEdgeLine line) {
		List<HXCorner> points = line.getPoints();
		int size = points.size();
		if (size > 1) {
			// 2 derniers points
			HXCorner hx2 = points.get(size - 1);
			HXCorner hx1 = points.get(size - 2);
			return getScreenZone(map, viewSettings, line, hx2, hx1);
		} else {
			return null;
		}
	}

	public Rectangle getRefreshZone(OrbeMap map, ViewSettings viewSettings, OrbeEdgeLine line) {
		HXPoint tl = null;
		HXPoint br = null;
		List<HXCorner> points = line.getPoints();
		for (HXCorner c : points) {
			HXPoint hx = c.hex;
			if (tl == null) {
				tl = new HXPoint(hx);
				br = new HXPoint(hx);
			} else {
				tl = new HXPoint(Math.min(tl.i, hx.i), Math.min(tl.j, hx.j));
				br = new HXPoint(Math.max(br.i, hx.i), Math.max(br.j, hx.j));
			}
		}
		Rectangle r = getScreenZone(map, viewSettings, line, new HXCorner(tl, 0), new HXCorner(br, 3));
		// Ok
		return r;
	}

	public boolean hasToRefresh(final OrbeEdgeLine line, final OrbeMap map, final ViewSettings viewSettings, final Double zone) {
		final ValueHandler<Boolean> ok = new ValueHandler<Boolean>();
		line.forEachSegment(new SegmentIterator<HXCorner>() {

			public boolean onSegment(HXCorner hxa, HXCorner hxb) {
				Rectangle r = getScreenZone(map, viewSettings, line, hxa, hxb);
				ok.setValue(r.intersects(zone));
				return !ok.getValue();
			}

		});
		return ok.getValue();
	}

	public void render(OrbeEdgeLine line, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		// Définit le style
		setStyle(line, map, viewSettings, g);
		// Construction d'une forme
		Shape path = getScreenPath(map, viewSettings, line);
		// Dessin de la forme
		g.draw(path);
	}

	protected Shape getScreenPath(OrbeMap map, ViewSettings viewSettings, OrbeEdgeLine line) {
		HXCorner[] hxs = line.getPoints().toArray(new HXCorner[0]);
		Shape path = createScreenPath(map, viewSettings, hxs);
		return path;
	}

	public GeneralPath createPXPath(OrbeMap map, ViewSettings viewSettings, HXCorner ... hxs) {
		if (hxs == null || hxs.length < 2) {
			return null;
		} else {
			HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
			// Collects all HX points
			HXCornerCollector collector = new HXCornerCollector();
			// Ajout du tout premier point
			collector.forCorner(hxs[0]);
			// Parcours des points
			for (int i = 1; i < hxs.length; i++) {
				HXCorner hxa = hxs[i - 1];
				HXCorner hxb = hxs[i];
				HXGeom.edge(hxa, hxb, false, true, collector);
			}
			/*
			 * Tous les points ont été obtenus. On les relie maintenant sous la
			 * forme d'un chemin.
			 */
			GeneralPath path = new GeneralPath();
			boolean start = true;
			for (HXCorner hx : collector.getCorners()) {
				// Conversion de coordonnées
				PointDecimal px = hxg.getPXCorner(hx);
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

	public Shape createScreenPath(OrbeMap map, ViewSettings viewSettings, HXCorner... hxs) {
		// Chemin en coordonnées PX
		GeneralPath path = createPXPath(map, viewSettings, hxs);
		// Zoom
		return viewSettings.zoom(path);
	}

	protected Rectangle getScreenZone(OrbeMap map, ViewSettings viewSettings, OrbeEdgeLine line, HXCorner hx1, HXCorner hx2) {
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		// Collects all hexes between hx1 and hx2
		HXCornerCollector collector = new HXCornerCollector();
		HXGeom.edge(hx1, hx2, true, true, collector);
		// Builds the zone
		Rectangle2D.Double zone = null;
		for (HXCorner c : collector.getCorners()) {
			Double bounds = hxg.getHexBounds(c.hex.i, c.hex.j);
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
	protected LineString getPXGeometry(GeometryFactory factory, OrbeMap map, OrbeEdgeLine line) {
		List<HXCorner> cs = line.getPoints();
		if (cs.size() < 2) {
			return null;
		} else {
			HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
			// Collects all HX points
			HXCornerCollector collector = new HXCornerCollector();
			// Ajout du tout premier point
			collector.forCorner(cs.get(0));
			// Parcours des points
			for (int i = 1; i < cs.size(); i++) {
				HXCorner hxa = cs.get(i - 1);
				HXCorner hxb = cs.get(i);
				HXGeom.edge(hxa, hxb, false, true, collector);
			}
			/*
			 * Tous les points ont été obtenus. On les relie maintenant sous la
			 * forme d'un chemin.
			 */
			ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
			for (HXCorner c : collector.getCorners()) {
				// Conversion de coordonnées
				PointDecimal px = hxg.getPXCorner(c);
				// Ajout
				coordinates.add(new Coordinate(px.x, px.y));
			}
			// Ok
			return factory.createLineString(coordinates.toArray(new Coordinate[0]));
		}
	}

	public Shape getOutline(OrbeMap map, ViewSettings viewSettings, OrbeEdgeLine line) {
		return getScreenPath(map, viewSettings, line);
	}

}
