/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.renderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.stroke.GradStroke;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXGraphics;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.line.OrbeLine;
import orbe.model.style.LineDotType;
import orbe.model.style.LineGradType;
import orbe.model.style.StyleLine;

/**
 * Abstract line renderer.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractLineRenderer.java,v 1.1 2006/11/30 17:09:28 guinnessman
 *          Exp $
 */
public abstract class AbstractLineRenderer<L extends OrbeLine> implements LineRenderer<L> {

	protected void setStyle(L line, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		StyleLine style = line.getStyle();
		// Color
		Color color = style.getColor();
		color = LayerUtils.transparentColor(color, style.isTransparent());
		g.setColor(color);
		// Trait
		Stroke stroke = getStroke(map, viewSettings, style, line.isGradInverse());
		g.setStroke(stroke);
	}

	protected Stroke getStroke(OrbeMap map, ViewSettings viewSettings, StyleLine style, boolean gradInverse) {
		LineDotType dot = style.getDot();
		LineGradType grad = style.getGrad();
		// Epaisseur
		float thickness = getThickness(map, viewSettings, style);
		// Trait sans graduation
		if (grad == LineGradType.NONE) {
			switch (dot) {
			case SHORT:
				return new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[] { thickness, thickness * 2f }, 0f);
			case MEDIUM:
				return new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[] { thickness * 3f, thickness * 4f }, 0f);
			case LONG:
				return new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[] { thickness * 5f, thickness * 6f }, 0f);
			case SQUARE:
				return new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0f, new float[] { thickness, thickness * 2f }, 0f);
			case CIRCLE:
				return new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[] { 0f, thickness * 2f }, 0f);
			case NONE:
			default:
				// Trait sans pointillé
				return getDefaultStroke(thickness);
			}
		} else {
			// Trait gradu�
			return new GradStroke(thickness, grad, gradInverse);
		}
	}

	protected BasicStroke getDefaultStroke(float thickness) {
		return new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}

	protected float getThickness(OrbeMap map, ViewSettings viewSettings, StyleLine style) {
		double thickness = style.getThickness();
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		thickness *= hxg.getHexWidth() / 100.0;
		thickness *= viewSettings.getZoom().doubleValue();
		return (float) thickness;
	}

	protected void grow(OrbeMap map, ViewSettings viewSettings, L line, Rectangle r) {
		// Prendre en compte la largeur de la ligne
		int shift = (int) getThickness(map, viewSettings, line.getStyle());
		if (line.getStyle().getGrad() != LineGradType.NONE) {
			shift += (4 * shift);
		}
		r.grow(shift, shift);
	}

	/**
	 * @see orbe.gui.map.renderer.LineRenderer#isOver(orbe.model.OrbeMap,
	 *      orbe.gui.map.core.ViewSettings, orbe.model.element.line.OrbeLine,
	 *      java.awt.Point)
	 */
	public boolean isOver(OrbeMap map, ViewSettings viewSettings, L line, Point p) {
		GeometryFactory geoFactory = new GeometryFactory();
		// Builds the line geometry
		LineString string = getPXGeometry(geoFactory, map, line);
		if (string != null) {
			// Converts the point to PX
			PointDecimal px = ScaleMath.scaleScreenToPX(map, viewSettings, p);
			com.vividsolutions.jts.geom.Point geoPoint = geoFactory.createPoint(new Coordinate(px.x, px.y));
			// Distance
			double distance = string.distance(geoPoint);
			// Result of the test
			return (distance < 5);
		} else {
			return false;
		}
	}

	/**
	 * Builds a JTS geometry from the line
	 * 
	 * @param map
	 *            Map environment
	 * @param line
	 *            Line
	 * @return JTS geometry
	 */
	protected abstract LineString getPXGeometry(GeometryFactory factory, OrbeMap map, L line);

}
