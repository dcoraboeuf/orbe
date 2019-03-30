/*
 * Created on 24 fevr. 2006
 */
package net.sf.doolin.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * String that has different values according to a locale.
 * 
 * @author Damien Coraboeuf
 * @version $Id: I18NString.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public class I18NString implements Serializable {

	private static final long serialVersionUID = 1;

	/**
	 * Default value
	 */
	private String defaultValue;

	/**
	 * Localized values
	 */
	private HashMap<Locale, String> values = new HashMap<Locale, String>(1);

	/**
	 * Constructor for no value
	 */
	public I18NString() {
	}

	/**
	 * Constructor with a default value.
	 * 
	 * @param value
	 *            Default value
	 */
	public I18NString(String value) {
		defaultValue = value;
	}

	/**
	 * Constructor for a given locale and without any default value
	 * 
	 * @param locale
	 *            Locale for the given value
	 * @param value
	 *            Value to set for the given locale
	 */
	public I18NString(Locale locale, String value) {
		defaultValue = null;
		values.put(locale, value);
	}

	/**
	 * Constructor for a given locale and with a default value
	 * 
	 * @param defaultValue
	 *            Default value
	 * @param locale
	 *            Locale for the given value
	 * @param value
	 *            Value to set for the given locale
	 */
	public I18NString(String defaultValue, Locale locale, String value) {
		this.defaultValue = defaultValue;
		values.put(locale, value);
	}

	/**
	 * Copy constructor
	 * 
	 * @param name
	 *            Object to copy
	 */
	public I18NString(I18NString name) {
		defaultValue = name.defaultValue;
		Iterator<Locale> keys = name.getKeys();
		while (keys.hasNext()) {
			Locale key = keys.next();
			String value = name.getLocaleValue(key);
			setValue(key, value);
		}
	}

	/**
	 * Get value for a locale, returning default if not found
	 * 
	 * @param locale
	 *            Locale to get the value for
	 * @return Value for this locale, or default value
	 */
	public String getValue(Locale locale) {
		String value = getLocaleValue(locale);
		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	/**
	 * Get value for a locale, returning <code>null</code> if not found
	 * 
	 * @param locale
	 *            Locale to get the value for
	 * @return Value for this locale, or <code>null</code>
	 */
	public String getLocaleValue(Locale locale) {
		String value = values.get(locale);
		if (value == null) {
			Set<Locale> locales = values.keySet();
			for (Locale localeKey : locales) {
				if (LocaleUtils.isMoreSpecific(locale, localeKey)) {
					return values.get(localeKey);
				}
			}
			return null;
		} else {
			return value;
		}
	}

	/**
	 * Get the default value
	 * 
	 * @return Default value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Set the default value
	 * 
	 * @param value
	 *            Default value
	 */
	public void setDefaultValue(String value) {
		defaultValue = value;
	}

	/**
	 * Set value for a locale
	 * 
	 * @param locale
	 *            Locale to get the value for
	 * @param value
	 *            Value for this locale
	 */
	public void setValue(Locale locale, String value) {
		values.put(locale, value);
	}

	/**
	 * Utility method, whose purpose if for introspection tools.
	 * 
	 * @param locale
	 *            If <code>null</code>, set the default value.
	 * @param value
	 *            Value to set.
	 * @see LocaleUtils#parseLocale(String)
	 */
	public void setValue(String locale, String value) {
		if (StringUtils.isBlank(locale)) {
			setDefaultValue(value);
		} else {
			Locale l = LocaleUtils.parseLocale(locale);
			setValue(l, value);
		}
	}

	/**
	 * Value for the current locale
	 */
	@Override
	public String toString() {
		return getValue();
	}

	/**
	 * Value for the current locale. If not found, takes the first non-empty
	 * locale.
	 * 
	 * @return A value
	 */
	public String getValue() {
		String value = getValue(Locale.getDefault());
		if (value != null) {
			return value;
		} else if (values.isEmpty()) {
			return null;
		} else {
			value = values.values().iterator().next();
			return value;
		}
	}

	/**
	 * Get all defined locales
	 * 
	 * @return Iterator on all defined locales.
	 */
	public Iterator<Locale> getKeys() {
		return values.keySet().iterator();
	}

	/**
	 * Hash code
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(defaultValue).append(values).hashCode();
	}

	/**
	 * Equality. All strings (default and localised ones) must be the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof I18NString) {
			I18NString item = (I18NString) obj;
			if (StringUtils.equals(this.defaultValue, item.defaultValue)) {
				return ObjectUtils.equals(this.values, item.values);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Formatted representation, used for encoding.
	 * 
	 * The format is defined as below:
	 * 
	 * <pre>
	 *   &lt;defaultValue&gt;{\\&lt;locale&gt;=&lt;value&gt;}*
	 * </pre>
	 * 
	 * @return Representation for this I18String.
	 */
	public String format() {
		StringBuffer b = new StringBuffer();
		String value = defaultValue;
		b.append(value);
		if (values != null) {
			for (Map.Entry<Locale, String> entry : values.entrySet()) {
				Locale locale = entry.getKey();
				String valueLocale = entry.getValue();
				b.append("\\\\");
				b.append(locale);
				b.append("=");
				b.append(valueLocale);
			}
		}
		return b.toString();
	}

	/**
	 * Parses a formatted string.
	 * 
	 * Expected format is the one which has been defined in
	 * {@link #format() <code>format</code>} method.
	 * 
	 * @param value
	 *            Value to parse (it must be a result of a previous call to the
	 *            {@link #format() <code>format</code>} method
	 * @return Parsed I18String.
	 */
	public static I18NString parse(String value) {
		if (value == null) {
			return null;
		} else if (StringUtils.isBlank(value)) {
			return new I18NString();
		} else {
			I18NString i = new I18NString();
			String[] tokens = StringUtils.splitByWholeSeparator(value, "\\\\");
			for (String token : tokens) {
				int indexEquals = StringUtils.indexOf(token, "=");
				if (indexEquals >= 0) {
					String localeValue = token.substring(0, indexEquals);
					String valueForLocale = token.substring(indexEquals + 1);
					Locale locale = LocaleUtils.parseLocale(localeValue);
					i.setValue(locale, valueForLocale);
				} else {
					i.setDefaultValue(token);
				}
			}
			return i;
		}
	}
}
