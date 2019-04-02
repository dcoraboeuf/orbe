/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.util;

import java.math.BigDecimal;

import net.sf.doolin.util.unit.Unit;
import net.sf.doolin.util.unit.ValueUnit;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Damien Coraboeuf
 * @version $Id: TestValueUnit.java,v 1.1 2007/07/31 15:32:48 guinnessman Exp $
 */
public class TestValueUnit {

	/**
	 *
	 */
	@Test
	public void testEmptyConstructor() {
		ValueUnit vu = new ValueUnit();
		assertNull(vu.getValue());
		assertNull(vu.getUnit());
	}

	/**
	 *
	 */
	@Test
	public void testConstructor() {
		// int
		ValueUnit vu = new ValueUnit(10, Unit.PIXEL);
		assertEquals(10, vu.getValue());
		assertEquals(Unit.PIXEL, vu.getUnit());
		// double
		vu = new ValueUnit(10.5, Unit.CM);
		assertEquals(10.5, vu.getValue());
		assertEquals(Unit.CM, vu.getUnit());
		// BigDecimal
		vu = new ValueUnit(new BigDecimal(10.5), Unit.PERCENT);
		assertEquals(new BigDecimal(10.5), vu.getValue());
		assertEquals(Unit.PERCENT, vu.getUnit());
	}

	/**
	 *
	 */
	@Test
	public void testToString() {
		// int
		ValueUnit vu = new ValueUnit(10, Unit.PIXEL);
		assertEquals("10px", vu.toString());
		// double
		vu = new ValueUnit(10.5, Unit.CM);
		assertEquals("10.5cm", vu.toString());
		// BigDecimal
		vu = new ValueUnit(new BigDecimal(10.5), Unit.PERCENT);
		assertEquals("10.5%", vu.toString());
	}

	/**
	 *
	 */
	@Test
	public void testValueOf () {
		// int
		ValueUnit vu = ValueUnit.valueOf("10px");
		assertEquals(10, vu.getValue().intValue());
		assertEquals(Unit.PIXEL, vu.getUnit());
		// double
		vu = ValueUnit.valueOf("10.5cm");
		assertEquals(10.5, vu.getValue().doubleValue());
		assertEquals(Unit.CM, vu.getUnit());
		// BigDecimal
		vu = ValueUnit.valueOf("10.5%");
		assertEquals(new BigDecimal(10.5).doubleValue(), vu.getValue().doubleValue());
		assertEquals(Unit.PERCENT, vu.getUnit());
	}

	@Ignore("Not working in headless environment")
	@Test
	public void testConvert() {
		// Identity
		ValueUnit vu = ValueUnit.valueOf("10px");
		vu = vu.convert(Unit.PIXEL, null);
		assertEquals(10, vu.getValue().intValue());
		assertEquals(Unit.PIXEL, vu.getUnit());
		// Px -> Cm
		vu = ValueUnit.valueOf("10px");
		vu = vu.convert(Unit.CM, null);
		assertEquals(0.230, vu.getValue().doubleValue(), 0.001);
		assertEquals(Unit.CM, vu.getUnit());
		// Px -> Inch
		vu = ValueUnit.valueOf("10px");
		vu = vu.convert(Unit.INCH, null);
		assertEquals(0.090, vu.getValue().doubleValue(), 0.001);
		assertEquals(Unit.INCH, vu.getUnit());
		// Px -> %
		vu = ValueUnit.valueOf("10px");
		vu = vu.convert(Unit.PERCENT, 200);
		assertEquals(5, vu.getValue().doubleValue(), 0.001);
		assertEquals(Unit.PERCENT, vu.getUnit());
		// % -> Px
		vu = ValueUnit.valueOf("10%");
		vu = vu.convert(Unit.PIXEL, 200);
		assertEquals(20, vu.getValue().doubleValue(), 0.001);
		assertEquals(Unit.PIXEL, vu.getUnit());
		// % -> Cm
		vu = ValueUnit.valueOf("10%");
		vu = vu.convert(Unit.CM, 200);
		assertEquals(20, vu.getValue().doubleValue(), 0.001);
		assertEquals(Unit.CM, vu.getUnit());
	}

}
