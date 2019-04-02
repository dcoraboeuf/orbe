/*
 * Created on 8 janv. 2006
 */
package net.sf.doolin.util;

import java.net.URL;
import java.util.*;

import junit.framework.TestCase;
import org.junit.Ignore;

/**
 * Test for methods of {@link net.sf.doolin.util.Utils} class.
 * 
 * @see net.sf.doolin.util.Utils
 * @author guinnessman
 * @version $Id: TestUtils.java,v 1.2 2007/07/31 15:32:48 guinnessman Exp $
 */
public class TestUtils extends TestCase {

	/**
	 * Test of <code>toMap</code> method.
	 * 
	 * @see net.sf.doolin.util.Utils#toMap(String[])
	 */
	public void testToMap() {
		// Null
		Map<String, String> map = Utils.toMap(null);
		assertNotNull(map);
		assertTrue(map.isEmpty());
		// Test
		String[] sequence = new String[] { "One=First value", "Two", "Three=Third value", " ", "", null };
		map = Utils.toMap(sequence);
		assertNotNull(map);
		assertEquals(3, map.size());
		assertNotNull(map.get("One"));
		assertEquals("First value", map.get("One"));
		assertNotNull(map.get("Two"));
		assertEquals("Two", map.get("Two"));
		assertNotNull(map.get("Three"));
		assertEquals("Third value", map.get("Three"));
	}

	/**
	 * Test of <code>toISOFormat</code> and <code>fromISOFormat</code>
	 * methods.
	 * 
	 * @throws Exception
	 * 
	 * @see net.sf.doolin.util.Utils#toISOFormat(Date)
	 * @see net.sf.doolin.util.Utils#fromISOFormat(String)
	 */
	@Ignore("Not working in headless environment")
	public void testISOFormat() throws Exception {

		Date now = new Date();
		String isoNow = Utils.toISOFormat(now);
		Date parsedNow = Utils.fromISOFormat(isoNow);
		assertEquals(now, parsedNow);

		TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");

		String iso = "2006-01-14T16:41:41.609";
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		calendar.set(Calendar.YEAR, 2006);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 14);
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 41);
		calendar.set(Calendar.SECOND, 41);
		calendar.set(Calendar.MILLISECOND, 609);
		calendar.setTimeZone(timeZone);
		Date date = calendar.getTime();

		String formattedIso = Utils.toISOFormat(date);
		assertEquals(iso, formattedIso);

		Date parsedDate = Utils.fromISOFormat(iso);
		assertEquals(date, parsedDate);

	}

	/**
	 * Test of {@link Utils#getResource(Class, String)}.
	 * 
	 */
	public void testGetResource() {
		URL url = Utils.getResource(null, "Codes.xml");
		assertNotNull(url);
		url = Utils.getResource(Utils.class, "codes.xsd");
		assertNotNull(url);
	}
}
