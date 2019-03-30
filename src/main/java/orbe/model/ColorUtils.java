/*
 * Created on Oct 9, 2006
 */
package orbe.model;

import java.awt.Color;

import net.sf.doolin.util.CodeException;

import org.apache.commons.lang.StringUtils;

public class ColorUtils {

	public static Color fromHEX(String hex) {
		if (StringUtils.isBlank(hex)) {
			return null;
		} else if (hex.length() != 6) {
			throw new CodeException(IErrors.IO_XML_COLOR_PARSING, hex);
		} else {
			try {
				String redValue = hex.substring(0, 2);
				String greenValue = hex.substring(2, 4);
				String blueValue = hex.substring(4, 6);
				int red = parseHex(redValue);
				int green = parseHex(greenValue);
				int blue = parseHex(blueValue);
				return new Color(red, green, blue);
			} catch (Exception ex) {
				throw new CodeException(IErrors.IO_XML_COLOR_PARSING, ex, hex);
			}
		}
	}

	private static int parseHex(String value) {
		return Integer.parseInt(value, 16);
	}

	public static String toHEX(Color color) {
		if (color == null) {
			return null;
		} else {
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();

			String r = toHEX(red);
			String g = toHEX(green);
			String b = toHEX(blue);

			return r + g + b;
		}
	}

	protected static String toHEX(int red) {
		String s = Integer.toHexString(red).toUpperCase();
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}

}
