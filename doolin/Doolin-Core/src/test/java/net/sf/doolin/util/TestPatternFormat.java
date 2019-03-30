/*
 * Created on 1 nov. 2005
 */
package net.sf.doolin.util;

import java.text.ParseException;

import net.sf.doolin.util.PatternFormat;

import junit.framework.TestCase;

/**
 * Unit test for {@link net.sf.doolin.util.PatternFormat}.
 * 
 * @author guinnessman
 * @version $Id: TestPatternFormat.java,v 1.1 2007/07/18 18:41:19 guinnessman Exp $
 */
public class TestPatternFormat extends TestCase {

	/**
	 * Format to be used
	 */
	private PatternFormat format;

	/**
	 * Creates a format to test.
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		format = new PatternFormat("Test*Format");
	}

	/**
	 * Test method for 'doolin.utils.PatternFormat.format(Object...)'
	 */
	public void testFormat() {
		String value = format.format("1");
		assertEquals("Test1Format", value);
	}

	/**
	 * Test method for 'doolin.utils.PatternFormat.accept(String)'
	 */
	public void testAccept() {
		String value = "Test1Format";
		boolean result = format.accept(value);
		assertTrue(result);
	}

	/**
	 * Test method for 'doolin.utils.PatternFormat.parse(String)'
	 * 
	 * @throws ParseException
	 *             If the parsing cannot be done.
	 */
	public void testParse() throws ParseException {
		String value = "Test1Format";
		Object[] components = format.parse(value);
		assertEquals(1, components.length);
		assertEquals("1", components[0]);
	}

	/**
	 * Test method for 'doolin.utils.PatternFormat.toString()'
	 */
	public void testToString() {
		assertEquals("Test*Format", format.toString());
	}

	/**
	 * Test method for 'doolin.utils.PatternFormat.equals(Object)'
	 */
	public void testEqualsObject() {
		PatternFormat other = new PatternFormat("Test*Format");
		assertEquals(format, other);
	}

}
