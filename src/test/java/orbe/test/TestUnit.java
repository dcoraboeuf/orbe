/*
 * Created on Oct 4, 2006
 */
package orbe.test;

import junit.framework.TestCase;
import orbe.model.Unit;

public class TestUnit extends TestCase {

	public void testPixels() {
		Unit unit = Unit.PIXEL;
		double pixels = unit.getPixels();
		assertEquals(1, pixels, 0.01);
	}

	public void testCM() {
		Unit unit = Unit.CM;
		double pixels = unit.getPixels();
		assertEquals(37.79, pixels, 0.01);
	}

}
