/*
 * Created on 1 nov. 2005
 */
package net.sf.doolin.util;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.Strings;
import junit.framework.TestCase;

/**
 * Test of {@link net.sf.doolin.util.CodeException}.
 * 
 * @author damien
 * @version $Id: TestCodeException.java,v 1.1 2007/07/18 18:41:19 guinnessman Exp $
 */
public class TestCodeException extends TestCase {

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
		try {
			throw new CodeException("sample.simple");
		} catch (CodeException ex) {
			assertEquals("Simple", ex.getMessage());
		}
	}

	/**
	 * One parameter
	 */
	public void testOne() {
		try {
			throw new CodeException("sample.one", "One");
		} catch (CodeException ex) {
			assertEquals("One parameter: One", ex.getMessage());
		}
	}

	/**
	 * Two parameters
	 */
	public void testTwo() {
		try {
			throw new CodeException("sample.two", "One", "Two");
		} catch (CodeException ex) {
			assertEquals("Two parameters: One, Two", ex.getMessage());
		}
	}

	/**
	 * One parameter and one exception.
	 */
	public void testOneWithException() {
		try {
			Exception root = new Exception("Root");
			throw new CodeException("sample.exception", root, "One", root
					.getMessage());
		} catch (CodeException ex) {
			assertEquals("Exception for One: Root", ex.getMessage());
			assertNotNull(ex.getCause());
			assertEquals("Root", ex.getCause().getMessage());
		}
	}

}
