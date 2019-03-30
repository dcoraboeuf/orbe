/*
 * Created on Oct 20, 2006
 */
package orbe.hex;

import static orbe.hex.HXGridConstants.G0123;
import static orbe.hex.HXGridConstants.G0123450;
import static orbe.hex.HXGridConstants.G34501;
import static orbe.hex.HXGridConstants.G345012;
import static orbe.hex.HXGridConstants.G45012;
import static orbe.hex.HXGridConstants.G5012;
import static orbe.hex.HXGridConstants.G50123;
import static orbe.hex.HXGridConstants.G501234;

import static orbe.hex.HXGraphicConstants.*;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import orbe.model.PointDecimal;
import orbe.model.Scale;

public class HXGrid {

	private Scale scale;

	public HXGrid(Scale scale) {
		this.scale = scale;
		x = new float[6];
		y = new float[6];
		double[] dx = new double[] { 0, HXDIM_H_OFFSET, HXDIM_H_WIDTH, HXDIM_H_WIDTH, HXDIM_H_OFFSET, 0 };
		double[] dy = new double[] { HXDIM_V_OFFSET1, 0, HXDIM_V_OFFSET1, HXDIM_V_OFFSET2, HXDIM_V_HEIGHT, HXDIM_V_OFFSET2 };
		double pxByHex = scale.toPixels();
		for (int i = 0; i < 6; i++) {
			x[i] = (float) (dx[i] * pxByHex);
			y[i] = (float) (dy[i] * pxByHex);
		}
		init();
	}

	public PointDecimal getCorner(int corner) {
		float cx = x[corner];
		float cy = y[corner];
		return new PointDecimal(cx, cy);
	}

	private void init() {
		gridPath45012 = init(G45012);
		gridPath34501 = init(G34501);
		gridPath345012 = init(G345012);
		gridPath5012 = init(G5012);
		gridPath0123 = init(G0123);
		gridPath0123450 = init(G0123450);
		gridPath501234 = init(G501234);
		gridPath50123 = init(G50123);
	}

	private GeneralPath init(int indexes[]) {
		GeneralPath path = new GeneralPath();
		int index = indexes[0];
		path.moveTo(x[index], y[index]);
		int n = indexes.length;
		for (int i = 1; i < n; i++) {
			index = indexes[i];
			path.lineTo(x[index], y[index]);
		}

		return path;
	}

	private float x[];

	private float y[];

	public GeneralPath gridPath45012;

	public GeneralPath gridPath34501;

	public GeneralPath gridPath345012;

	public GeneralPath gridPath5012;

	public GeneralPath gridPath0123;

	public GeneralPath gridPath0123450;

	public GeneralPath gridPath501234;

	public GeneralPath gridPath50123;

	public void draw(GeneralPath path, int i, int j, HXRect hxRect) {
		double pxByHex = scale.toPixels();

		double offsetY = j * HXDIM_V_OFFSET2 * pxByHex;
		double offsetX;
		if (j % 2 == 0) {
			offsetX = i * HXDIM_H_WIDTH * pxByHex;
		} else {
			offsetX = (i * HXDIM_H_WIDTH + HXDIM_H_OFFSET) * pxByHex;
		}

		AffineTransform t = AffineTransform.getTranslateInstance(offsetX, offsetY);

		int minI = hxRect.getMinI();
		int maxI = hxRect.getMaxI();
		int minJ = hxRect.getMinJ();
		int maxJ = hxRect.getMaxJ();
		if (i == minI) {
			if (j == minJ) {
				path.append(t.createTransformedShape(gridPath45012), false);
			} else if (j == maxJ) {
				if (j % 2 != 0) {
					path.append(t.createTransformedShape(gridPath345012), false);
				} else {
					path.append(t.createTransformedShape(gridPath345012), false);
				}
			} else if (j % 2 != 0) {
				path.append(t.createTransformedShape(gridPath5012), false);
			} else {
				path.append(t.createTransformedShape(gridPath45012), false);
			}
		} else if (i == maxI) {
			if (j == minJ) {
				path.append(t.createTransformedShape(gridPath50123), false);
			} else if (j == maxJ) {
				path.append(t.createTransformedShape(gridPath0123450), false);
			} else if (j % 2 != 0) {
				path.append(t.createTransformedShape(gridPath501234), false);
			} else {
				path.append(t.createTransformedShape(gridPath50123), false);
			}
		} else {
			if (j == minJ) {
				path.append(t.createTransformedShape(gridPath5012), false);
			} else if (j == maxJ) {
				path.append(t.createTransformedShape(gridPath345012), false);
			} else {
				path.append(t.createTransformedShape(gridPath5012), false);
			}
		}
	}
}
