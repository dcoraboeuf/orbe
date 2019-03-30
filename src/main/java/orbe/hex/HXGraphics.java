/*
 * Created on 19 oct. 06
 */
package orbe.hex;

import static orbe.hex.HXGraphicConstants.HXDIM_H_OFFSET;
import static orbe.hex.HXGraphicConstants.HXDIM_H_WIDTH;
import static orbe.hex.HXGraphicConstants.HXDIM_V_EXTRA;
import static orbe.hex.HXGraphicConstants.HXDIM_V_HEIGHT;
import static orbe.hex.HXGraphicConstants.HXDIM_V_OFFSET1;
import static orbe.hex.HXGraphicConstants.HXDIM_V_OFFSET2;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import orbe.model.PointDecimal;
import orbe.model.Scale;

public class HXGraphics {

	private Scale scale;

	public HXGraphics(Scale aScale) {
		scale = aScale;
	}

	public PointDecimal toPixels(HXPoint point) {
		// Pixel equivalent of the scale
		double pxByHex = scale.toPixels();
		// Ratio
		double width = HXDIM_H_WIDTH * point.i + HXDIM_H_OFFSET;
		double height = HXDIM_V_OFFSET2 * point.j + HXDIM_V_EXTRA;
		// Scale
		width *= pxByHex;
		height *= pxByHex;
		// Ok
		return new PointDecimal(width, height);
	}

	public HXRect toApproxHexs(Double pxZone) {
		// Locate the TL corner
		HXPoint hxTL = locateApproxHex(new PointDecimal(pxZone.x, pxZone.y));
		// Locate the BR corner
		HXPoint hxBR = locateApproxHex(new PointDecimal(pxZone.x + pxZone.width, pxZone.y + pxZone.height));
		// Creates the hex rect
		HXRect rect = new HXRect(hxTL, hxBR);
		rect = rect.expand(1);
		return rect;
	}

	public HXPoint locateApproxHex(PointDecimal px) {
		// Pixel equivalent of the scale
		double pxByHex = scale.toPixels();
		double hpxByHex = HXDIM_H_WIDTH * pxByHex;
		double vpxByHex = HXDIM_V_OFFSET2 * pxByHex;
		double halfHpxByHex = HXDIM_H_OFFSET * pxByHex;
		// Row
		int row = (int) Math.floor(px.y / vpxByHex);
		int column;
		// Ligne paire
		if (row % 2 == 0) {
			column = (int) Math.floor(px.x / hpxByHex);
		} else {
			column = (int) Math.floor((px.x - halfHpxByHex) / hpxByHex);
		}
		// Ok
		return new HXPoint(column, row);
	}

	public Shape getGridShape(HXRect hxRect, HXRect bounds) {

		hxRect = hxRect.intersect(bounds);

		int i1 = hxRect.getMinI();
		int j1 = hxRect.getMinJ();
		int i2 = hxRect.getMaxI();
		int j2 = hxRect.getMaxJ();

		GeneralPath path = new GeneralPath();

		HXGrid grid = new HXGrid(scale);

		for (int j = j1; j <= j2; j++) {
			for (int i = i1; i <= i2; i++) {
				grid.draw(path, i, j, hxRect);
			}
		}

		return path;
	}

	public Shape getHexShape(int i, int j) {
		double pxByHex = scale.toPixels();
		// Pixel equivalent of the scale
		HXGrid grid = new HXGrid(scale);
		// Shape of the hex
		Shape path = grid.gridPath0123450;
		// Shift
		double ty = pxByHex * HXDIM_V_OFFSET2 * j;
		double tx;
		if (j % 2 == 0) {
			tx = pxByHex * HXDIM_H_WIDTH * i;
		} else {
			tx = pxByHex * (HXDIM_H_WIDTH * i + HXDIM_H_OFFSET);
		}
		AffineTransform translation = AffineTransform.getTranslateInstance(tx, ty);
		path = translation.createTransformedShape(path);
		// Ok
		return path;
	}

	public HXPoint locateHex(PointDecimal pointPX) {
		double pxByHex = scale.toPixels();
		double vOffset1 = pxByHex * HXDIM_V_OFFSET1;
		double vOffset2 = pxByHex * HXDIM_V_OFFSET2;
		double hWidth = pxByHex * HXDIM_H_WIDTH;
		double hOffset = pxByHex * HXDIM_H_OFFSET;
		double x = pointPX.x;
		double y = pointPX.y;
		int j = (int) Math.floor(y / vOffset2);
		int i = 0;
		if (j % 2 == 0)
			i = (int) Math.floor(x / hWidth);
		else
			i = (int) Math.floor((x - hOffset) / hWidth);
		double voffset = y - j * vOffset2;
		if (voffset < vOffset1) {
			double hoffset;
			if (j % 2 == 0)
				hoffset = x - i * hWidth;
			else
				hoffset = x - (i * hWidth + hOffset);
			if (hoffset < hOffset) {
				double d = (hOffset * voffset + vOffset1 * hoffset) - vOffset1 * hOffset;
				if (d < 0) {
					HXPoint hex = new HXPoint(i, j);
					HXGeom.offsetHex(hex, 0);
					return hex;
				}
			} else {
				double d = ((hOffset - hWidth) * voffset + vOffset1 * hoffset) - hOffset * vOffset1;
				if (d > 0.0D) {
					HXPoint hex = new HXPoint(i, j);
					HXGeom.offsetHex(hex, 1);
					return hex;
				}
			}
		}
		return new HXPoint(i, j);
	}

	public Rectangle2D.Double getHexBounds(int i, int j) {
		double pxByHex = scale.toPixels();
		PointDecimal t = getOffset(i, j);
		// Bounds
		double x = t.x;
		double y = t.y;
		double width = pxByHex * HXDIM_H_WIDTH;
		double height = pxByHex * HXDIM_V_HEIGHT;
		// Ok
		return new Rectangle2D.Double(x, y, width, height);
	}

	protected PointDecimal getOffset(int i, int j) {
		double pxByHex = scale.toPixels();
		// Shift
		double ty = pxByHex * HXDIM_V_OFFSET2 * j;
		double tx;
		if (j % 2 == 0) {
			tx = pxByHex * HXDIM_H_WIDTH * i;
		} else {
			tx = pxByHex * (HXDIM_H_WIDTH * i + HXDIM_H_OFFSET);
		}
		PointDecimal t = new PointDecimal(tx, ty);
		return t;
	}

	/**
	 * Hauteur d'un hexagone (utilisé pour le calcul des tailles de caractères)
	 */
	public double getHexHeight() {
		double pxByHex = scale.toPixels();
		return pxByHex * HXDIM_V_HEIGHT;
	}

	/**
	 * Largeur d'un hexagone (utilisé pour le calcul de l'épaisseur des lignes)
	 */
	public double getHexWidth() {
		double pxByHex = scale.toPixels();
		return pxByHex * HXDIM_H_WIDTH;
	}

	/**
	 * Conversion d'un point PX vers un point HX.
	 * 
	 * @param px
	 *            Point exprimé dans le référentiel PX.
	 * @return Point exprimé dans le référentiel HX.
	 */
	public HXPoint2D scalePXToHX(PointDecimal px) {
		double pxByHex = scale.toPixels();
		double pxByHexH = pxByHex * HXDIM_H_WIDTH;
		double pxByHexV = pxByHex * HXDIM_V_OFFSET2;
		double x = px.x / pxByHexH;
		double y = px.y / pxByHexV;
		return new HXPoint2D(x, y);
	}

	/**
	 * Conversion d'un point HX vers un point PX.
	 * 
	 * @param hx
	 *            Point exprimé dans le référentiel HX.
	 * @return Point exprimé dans le référentiel PX.
	 */
	public PointDecimal scaleHXToPX(HXPoint2D hx) {
		double pxByHex = scale.toPixels();
		double pxByHexH = pxByHex * HXDIM_H_WIDTH;
		double pxByHexV = pxByHex * HXDIM_V_OFFSET2;
		double x = hx.x * pxByHexH;
		double y = hx.y * pxByHexV;
		return new PointDecimal(x, y);
	}

	/**
	 * Obtention du centre d'un hex
	 * 
	 * @param hx
	 *            Coordonnées de l'hex
	 * @return Centre dans le référentiel PX
	 */
	public PointDecimal getHexCenter(HXPoint hx) {
		Double bounds = getHexBounds(hx.i, hx.j);
		return new PointDecimal(bounds.getCenterX(), bounds.getCenterY());
	}

	/**
	 * Coordonnées PX d'un coin d'hex.
	 */
	public PointDecimal getPXCorner(HXCorner c) {
		// Position à l'origine
		HXGrid grid = new HXGrid(scale);
		PointDecimal cpx = grid.getCorner(c.corner);
		// Offset
		PointDecimal ox = getOffset(c.hex.i, c.hex.j);
		cpx.x += ox.x;
		cpx.y += ox.y;
		// Ok
		return cpx;
	}

	public HXCorner locateCorner(PointDecimal px) {
		// Hex
		HXPoint hp = locateHex(px);
		// Centre de l'hex
		PointDecimal center = getHexCenter(hp);
		// Différence
		double dx = px.x - center.x;
		double dy = px.y - center.y;
		// Angle en degrés (y d'abord, x ensuite, voir la doc)
		int angle = (int) Math.round(Math.atan2(dy, dx) * 180.0 / Math.PI);
		angle = -angle;
		// Calcul du coin
		int corner;
		if (angle <= -120) {
			corner = 5;
		} else if (angle <= -60) {
			corner = 4;
		} else if (angle <= 0) {
			corner = 3;
		} else if (angle <= 60) {
			corner = 2;
		} else if (angle <= 120) {
			corner = 1;
		} else {
			corner = 0;
		}
		// Ok
		return new HXCorner(hp, corner);
	}

}
