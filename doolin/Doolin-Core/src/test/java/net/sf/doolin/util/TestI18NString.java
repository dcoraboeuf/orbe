/*
 * Created on 24 fevr. 2006
 */
package net.sf.doolin.util;

import java.util.Locale;

import net.sf.doolin.util.I18NString;

import junit.framework.TestCase;

/**
 * Test of <code>I18NString</code> class.
 * 
 * @see net.sf.doolin.util.I18NString
 * @author coraboeuf
 * @version $Id: TestI18NString.java,v 1.2 2007/07/31 15:32:48 guinnessman Exp $
 */
public class TestI18NString extends TestCase {

	private Locale oldLocale;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		oldLocale = Locale.getDefault();
		Locale.setDefault(Locale.UK);
	}

	@Override
	protected void tearDown() throws Exception {
		Locale.setDefault(oldLocale);
		super.tearDown();
	}

	/**
	 * 
	 */
	public void testConstructorEmpty() {
		I18NString string = new I18NString();
		assertNull(string.getDefaultValue());
		assertNull(string.getValue());
		assertNull(string.getValue(Locale.FRANCE));
		assertNull(string.getLocaleValue(Locale.FRANCE));
	}

	/**
	 * 
	 */
	public void testConstructorDefault() {
		I18NString string = new I18NString("Default");
		assertEquals("Default", string.getDefaultValue());
		assertEquals("Default", string.getValue());
		assertEquals("Default", string.getValue(Locale.FRANCE));
		assertNull(string.getLocaleValue(Locale.FRANCE));
	}

	/**
	 * 
	 */
	public void testConstructorLocale() {
		I18NString string = new I18NString(Locale.FRANCE, "D�faut");
		assertEquals(null, string.getDefaultValue());
		assertEquals("D�faut", string.getValue());
		assertEquals("D�faut", string.getValue(Locale.FRANCE));
		assertEquals(null, string.getValue(Locale.UK));
		assertEquals("D�faut", string.getLocaleValue(Locale.FRANCE));
	}

	/**
	 * 
	 */
	public void testConstructorFull() {
		I18NString string = new I18NString("Default", Locale.FRANCE, "D�faut");
		assertEquals("Default", string.getDefaultValue());
		assertEquals("Default", string.getValue());
		assertEquals("D�faut", string.getValue(Locale.FRANCE));
		assertEquals("Default", string.getValue(Locale.UK));
		assertEquals("D�faut", string.getLocaleValue(Locale.FRANCE));
	}

	/**
	 * 
	 */
	public void testFormatting() {
		I18NString string = new I18NString("Default", Locale.FRENCH, "D�faut\\AntiSlash");
		String format = string.format();
		assertEquals("Default\\\\fr=D�faut\\AntiSlash", format);
	}

	/**
	 * 
	 */
	public void testParsing() {
		String format = "Default\\\\fr=D�faut\\AntiSlash";
		I18NString string = I18NString.parse(format);
		assertNotNull(string);
		assertEquals("Default", string.getDefaultValue());
		assertEquals("D�faut\\AntiSlash", string.getLocaleValue(Locale.FRENCH));
	}

}
