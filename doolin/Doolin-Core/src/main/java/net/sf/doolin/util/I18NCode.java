package net.sf.doolin.util;

import java.io.Serializable;
import java.util.Locale;

/**
 * I18NString that is associated with a code.
 * 
 * @author Damien Coraboeuf
 * @version $Id: I18NCode.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public class I18NCode extends I18NString implements Serializable {

	private static final long serialVersionUID = 1;

	/**
	 * Code
	 */
	private String code;

	/**
	 * Constructor.
	 * 
	 * The resulting object has no code, no default value and no localized value
	 * 
	 * @see I18NString#I18NString()
	 */
	public I18NCode() {
	}

	/**
	 * Constructor.
	 * 
	 * The resulting object has a code but no default value or localized value.
	 * 
	 * @see I18NString#I18NString()
	 * @param code
	 *            Associated code
	 */
	public I18NCode(String code) {
		super();
		this.code = code;
	}

	/**
	 * Constructor.
	 * 
	 * The resulting object has a code, a localized value but no default value.
	 * 
	 * @see I18NString#I18NString(Locale, String)
	 * @param code
	 *            Associated code
	 * @param locale
	 *            Locale associated to the value
	 * @param value
	 *            Value to set with the given locale
	 */
	public I18NCode(String code, Locale locale, String value) {
		super(locale, value);
		this.code = code;
	}

	/**
	 * Constructor.
	 * 
	 * The resulting object has a code, a localized value and a default value.
	 * 
	 * @see I18NString#I18NString(String, Locale, String)
	 * @param code
	 *            Associated code
	 * @param defaultValue
	 *            Default value
	 * @param locale
	 *            Locale associated to the value
	 * @param value
	 *            Value to set with the given locale
	 */
	public I18NCode(String code, String defaultValue, Locale locale, String value) {
		super(defaultValue, locale, value);
		this.code = code;
	}

	/**
	 * Constructor.
	 * 
	 * The resulting object has a code, a default value but no localized value.
	 * 
	 * @see I18NString#I18NString(String)
	 * @param code
	 *            Associated code
	 * @param value
	 *            Default value
	 */
	public I18NCode(String code, String value) {
		super(value);
		this.code = code;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            The code to set.
	 */
	public void setCode(String code) {
		if (this.code != null) {
			throw new IllegalStateException("The code has already been set");
		}
		this.code = code;
	}
}
