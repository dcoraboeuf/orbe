/*
 * Created on 1 nov. 2005
 */
package net.sf.doolin.util;

import net.sf.doolin.util.Strings;
import junit.framework.TestCase;

/**
 * Test of {@link net.sf.doolin.util.Strings}.
 * 
 * @author damien
 * @version $Id: TestStrings.java,v 1.1 2007/07/18 18:41:19 guinnessman Exp $
 */
public class TestStrings extends TestCase {

	/**
	 * Loads the strings.
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Strings.add("net.sf.doolin.util.SampleStrings");
	}

	/**
	 * No parameter.
	 */
	public void testSimple() {
		String value = Strings.get(null, "sample.simple");
		assertEquals("Simple", value);
	}

	/**
	 * One parameter
	 */
	public void testOne() {
		String value = Strings.get(null, "sample.one", "One");
		assertEquals("One parameter: One", value);
	}

	/**
	 * Two parameters
	 */
	public void testTwo() {
		String value = Strings.get(null, "sample.two", "One", "Two");
		assertEquals("Two parameters: One, Two", value);
	}

	/**
	 * Test a non mandatory string
	 */
	public void testNotMandatory() {
		String value = Strings.get(null, "NoCode", false);
		assertNull(value);
	}

	/**
	 * Test a mandatory string
	 */
	public void testMandatory() {
		String value = Strings.get(null, "NoCode", true);
		assertNotNull(value);
		assertEquals("NoCode", value);
	}

}
