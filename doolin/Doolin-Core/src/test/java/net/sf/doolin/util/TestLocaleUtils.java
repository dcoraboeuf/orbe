/*
 * Created on 14 janv. 2006
 */
package net.sf.doolin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import net.sf.doolin.util.ELocaleMode;
import net.sf.doolin.util.LocaleUtils;

import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

/**
 * Unit test for LocaleUtils class
 * 
 * @author guinnessman
 * @version $Id: TestLocaleUtils.java,v 1.2 2007/07/31 15:32:48 guinnessman Exp $
 * @see net.sf.doolin.util.LocaleUtils
 */
public class TestLocaleUtils extends TestCase {

	/**
	 * 
	 */
	public void testParseLocale() {
		Locale locale = LocaleUtils.parseLocale(null);
		assertNull(locale);
		locale = LocaleUtils.parseLocale("en");
		assertEquals(Locale.ENGLISH.getLanguage(), locale.getLanguage());
		assertTrue(StringUtils.isBlank(locale.getCountry()));
		locale = LocaleUtils.parseLocale("fr_FR");
		assertEquals(Locale.FRANCE.getLanguage(), locale.getLanguage());
		assertEquals(Locale.FRANCE.getCountry(), locale.getCountry());
		locale = LocaleUtils.parseLocale("fr-FR");
		assertEquals(Locale.FRANCE.getLanguage(), locale.getLanguage());
		assertEquals(Locale.FRANCE.getCountry(), locale.getCountry());
	}

	/**
	 * @throws IOException
	 */
	public void testGetResourceAsStream() throws IOException {
		String path = "/net/sf/doolin/util/test.properties";

		testInputStream(path, null, true);
		testInputStream(path, Locale.FRENCH, true);
		testInputStream(path, Locale.FRANCE, true);
		testInputStream(path, Locale.ENGLISH, true);
		testInputStream(path, Locale.UK, true);

		testInputStream("/net/sf/doolin/util/fake.properties", Locale.UK, false);
	}

	private void testInputStream(String path, Locale locale, boolean found) throws IOException {
		InputStream in = LocaleUtils.getResourceAsStream(path, locale);
		try {
			assertTrue(!(in != null ^ found));
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * 
	 */
	public void testGetResourceNameStringLocale() {
		String path = "/doolin/utils/test.properties";

		String newPath = LocaleUtils.getResourceName(path, null);
		assertEquals(path, newPath);

		newPath = LocaleUtils.getResourceName(path, Locale.FRENCH);
		assertEquals("/doolin/utils/test_fr.properties", newPath);

		newPath = LocaleUtils.getResourceName(path, Locale.FRANCE);
		assertEquals("/doolin/utils/test_fr_FR.properties", newPath);
	}

	/**
	 * 
	 */
	public void testGetResourceNameStringLocaleELocaleMode() {
		String path = "/doolin/utils/test.properties";

		String newPath = LocaleUtils.getResourceName(path, Locale.FRANCE, ELocaleMode.NONE);
		assertEquals("/doolin/utils/test.properties", newPath);

		newPath = LocaleUtils.getResourceName(path, Locale.FRANCE, ELocaleMode.LANGUAGE);
		assertEquals("/doolin/utils/test_fr.properties", newPath);

		newPath = LocaleUtils.getResourceName(path, Locale.FRANCE, ELocaleMode.LANGUAGE_COUNTRY);
		assertEquals("/doolin/utils/test_fr_FR.properties", newPath);

		newPath = LocaleUtils.getResourceName(path, null, ELocaleMode.LANGUAGE);
		assertEquals("/doolin/utils/test.properties", newPath);
	}

	/**
	 * Test method for <code>isMoreSpecific</code>.
	 */
	public void testIsMoreSpecific() {
		assertTrue(LocaleUtils.isMoreSpecific(Locale.FRANCE, Locale.FRENCH));
		assertFalse(LocaleUtils.isMoreSpecific(Locale.FRENCH, Locale.FRENCH));
		assertFalse(LocaleUtils.isMoreSpecific(Locale.UK, Locale.FRANCE));
	}

}
