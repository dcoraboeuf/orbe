/*
 * Created on 8 oct. 2005
 */
package net.sf.doolin.util;

import net.sf.doolin.util.Version;
import junit.framework.TestCase;

/**
 * Version tests.
 * 
 * @author damien
 * @version $Id: TestVersion.java,v 1.1 2007/07/18 18:41:19 guinnessman Exp $
 */
public class TestVersion extends TestCase {
	/**
	 * Test new
	 */
	public void testNew() {
		Version v = new Version(1, 2, 3);
		assertEquals(1, v.getMajor());
		assertEquals(2, v.getMinor());
		assertEquals(3, v.getPatch());
	}

	/**
	 * Test string
	 */
	public void testString() {
		Version v = new Version(1, 2, 3);
		assertEquals("1.2.3", v.toString());
	}

	/**
	 * Test hashCode
	 */
	public void testHashCode() {
		Version v1 = new Version(1, 2, 3);
		Version v2 = new Version(1, 2, 3);
		assertEquals(v1.hashCode(), v2.hashCode());
	}

	/**
	 * Test equals
	 */
	public void testEquals() {
		Version v1 = new Version(1, 2, 3);
		Version v2 = new Version(1, 2, 3);
		assertEquals(v1, v2);
	}

	/**
	 * Test compare
	 */
	public void testCompare() {
		Version v123 = new Version(1, 2, 3);
		Version v123a = new Version(1, 2, 3);
		Version v124 = new Version(1, 2, 4);
		Version v132 = new Version(1, 3, 2);
		Version v200 = new Version(2, 0, 0);
		assertTrue(v123.compareTo(v123a) == 0);
		assertTrue(v123.compareTo(v124) < 0);
		assertTrue(v123.compareTo(v132) < 0);
		assertTrue(v123.compareTo(v200) < 0);
	}
	
	/**
	 * Test parsing
	 */
	public void testParsing ()
	{
		Version v = new Version("1.20.2");
		assertEquals(1, v.getMajor());
		assertEquals(20, v.getMinor());
		assertEquals(2, v.getPatch());
	}
	
	/**
	 * Test of partial parsing
	 */
	public void testPartialParsing () {
		// 0
		Version v = new Version("0");
		assertEquals(0, v.getMajor());
		assertEquals(0, v.getMinor());
		assertEquals(0, v.getPatch());
		// 0.1
		v = new Version("0.1");
		assertEquals(0, v.getMajor());
		assertEquals(1, v.getMinor());
		assertEquals(0, v.getPatch());
		// 0.1.2
		v = new Version("0.1.2");
		assertEquals(0, v.getMajor());
		assertEquals(1, v.getMinor());
		assertEquals(2, v.getPatch());
	}
}
