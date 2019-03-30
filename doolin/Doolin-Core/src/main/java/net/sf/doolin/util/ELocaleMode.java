/*
 * Created on 14 janv. 2006
 */
package net.sf.doolin.util;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * Defines how a Locale must be used when looking for resources
 * 
 * @author guinnessman
 * @version $Id: ELocaleMode.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public enum ELocaleMode {
	/**
	 * Defines the full locale, including language and country.
	 */
	LANGUAGE_COUNTRY {
		public Locale getLocale(Locale locale) {
			return locale;
		}
	},
	/**
	 * Defines the language only.
	 */
	LANGUAGE {
		public Locale getLocale(Locale locale) {
			if (locale == null) {
				return null;
			} else if (StringUtils.isNotBlank(locale.getLanguage())) {
				return new Locale(locale.getLanguage());
			} else {
				return null;
			}
		}
	},
	/**
	 * Independant from the locale.
	 */
	NONE {
		public Locale getLocale(Locale locale) {
			return null;
		}
	};

	/**
	 * Converts a locale
	 * 
	 * @param locale
	 *            Locale to convert.
	 * @return <code>null</code> if the conversion cannot be done.
	 */
	public abstract Locale getLocale(Locale locale);
}
