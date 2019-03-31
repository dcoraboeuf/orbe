/*
 * Created on Sep 15, 2007
 */
package orbe.model.io;

import java.awt.Color;

import orbe.hex.HXCorner;
import orbe.hex.HXPoint;
import orbe.hex.HXPoint2D;
import orbe.model.ColorUtils;
import orbe.model.hex.HexMap;

public class OrbeIOFormat {

	public String formatColor(Color o) {
		return ColorUtils.toHEX(o);
	}

	public String formatHXPoint2D(HXPoint2D o) {
		return o.x + "," + o.y;
	}

	public String formatHXPoint(HXPoint o) {
		return o.i + "," + o.j;
	}

	public String formatHXCorner(HXCorner o) {
		return o.hex.i + "," + o.hex.j + "@" + o.corner;
	}

	public String formatMap(HexMap o) {
		HexOutputIterator hexIOIterator = new HexOutputIterator();
		o.iterate(hexIOIterator);
		hexIOIterator.flush();
		String text = hexIOIterator.getString();
		return text;
	}

}
