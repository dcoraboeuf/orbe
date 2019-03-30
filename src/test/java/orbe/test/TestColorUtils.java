/*
 * Created on Oct 9, 2006
 */
package orbe.test;

import java.awt.Color;

import junit.framework.TestCase;
import net.sf.doolin.util.CodeException;
import orbe.model.ColorUtils;

public class TestColorUtils extends TestCase {

	public void testFromHEX() throws Exception {

		assertNull(ColorUtils.fromHEX(null));
		assertNull(ColorUtils.fromHEX(""));

		try {
			ColorUtils.fromHEX("1");
			ColorUtils.fromHEX("ZZZZZZ");
			fail();
		} catch (CodeException ex) {
		}

		try {
			ColorUtils.fromHEX("ZZZZZZ");
			fail();
		} catch (CodeException ex) {
		}

		Color color = ColorUtils.fromHEX("FFCCAA");
		assertNotNull(color);
		assertEquals(0xFF, color.getRed());
		assertEquals(0xCC, color.getGreen());
		assertEquals(0xAA, color.getBlue());

	}

	public void testToHEX() throws Exception {

		assertNull(ColorUtils.toHEX(null));

		String hex = ColorUtils.toHEX(new Color(0xFF, 0xCC, 0x66));
		assertNotNull(hex);
		assertEquals("FFCC66", hex);

		hex = ColorUtils.toHEX(new Color(0xF, 0xC, 0x6));
		assertNotNull(hex);
		assertEquals("0F0C06", hex);
	}

}
