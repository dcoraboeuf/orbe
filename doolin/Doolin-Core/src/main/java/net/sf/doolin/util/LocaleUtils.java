/*
 * Created on 17 mars 2005
 */
package net.sf.doolin.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * Utility methods for Locales
 * 
 * @author Damien Coraboeuf
 * @version $Id: LocaleUtils.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public class LocaleUtils {
	/**
	 * Parse a Locale. Expected text is the one that is obtained by the method
	 * <code>Locale.toString()</code>. For example, fr_FR for French
	 * (France).
	 * 
	 * @see Locale#toString()
	 * @param value
	 *            Text to parse
	 * @return Resulting locale or <code>null</code> if the value is
	 *         <code>null</code> or empty.
	 */
	public static Locale parseLocale(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(value, "_-");
		switch (st.countTokens()) {
		case 1:
			return new Locale(st.nextToken());
		case 2:
			return new Locale(st.nextToken(), st.nextToken());
		case 3:
			return new Locale(st.nextToken(), st.nextToken(), st.nextToken());
		default:
			return null;
		}
	}

	/**
	 * Get a resource using a locale.
	 * 
	 * @param path
	 *            Path to look for
	 * @param locale
	 *            Locale to look for
	 * @return Associated stream or <code>null</code> is not found.
	 * @see #getResourceName(String, Locale)
	 */
	public static InputStream getResourceAsStream(String path, Locale locale) {
		// First, use full information
		String resourcePath = getResourceName(path, locale, ELocaleMode.LANGUAGE_COUNTRY);
		InputStream in = LocaleUtils.class.getResourceAsStream(resourcePath);
		if (in != null) {
			return in;
		}
		// Then, use only language
		resourcePath = getResourceName(path, locale, ELocaleMode.LANGUAGE);
		in = LocaleUtils.class.getResourceAsStream(resourcePath);
		if (in != null) {
			return in;
		}
		// Then nothing
		resourcePath = getResourceName(path, locale, ELocaleMode.NONE);
		in = LocaleUtils.class.getResourceAsStream(resourcePath);
		return in; /* Can be null if not found */
	}

	/**
	 * Get a resource using a locale.
	 * 
	 * @param path
	 *            Path to look for
	 * @param locale
	 *            Locale to look for
	 * @return Associated URL or <code>null</code> is not found.
	 * @see #getResourceName(String, Locale)
	 */
	public static URL getResource(String path, Locale locale) {
		// First, use full information
		String resourcePath = getResourceName(path, locale, ELocaleMode.LANGUAGE_COUNTRY);
		URL url = LocaleUtils.class.getResource(resourcePath);
		if (url != null) {
			return url;
		}
		// Then, use only language
		resourcePath = getResourceName(path, locale, ELocaleMode.LANGUAGE);
		url = LocaleUtils.class.getResource(resourcePath);
		if (url != null) {
			return url;
		}
		// Then nothing
		resourcePath = getResourceName(path, locale, ELocaleMode.NONE);
		url = LocaleUtils.class.getResource(resourcePath);
		return url; /* Can be null if not found */
	}

	/**
	 * Get a resource name according to the locale. Given a resource name like
	 * <i>prefix.ext</i>, this name is updated regarding to the locale. For
	 * example, for french (fr), it becomes <i>prefix_fr.ext</i> and for french
	 * (France), i.e. fr_FR, it becomes <i>prefix_fr_FR.ext</i>.
	 * 
	 * @param name
	 *            Name of the resource
	 * @param locale
	 *            Locale to be used
	 * @return Updated resource name
	 */
	public static String getResourceName(String name, Locale locale) {
		int pos = name.lastIndexOf('.');
		if (pos < 0) {
			return name;
		} else {
			String prefix = name.substring(0, pos);
			String suffix = name.substring(pos);
			StringBuffer path = new StringBuffer();
			path.append(prefix);

			if (locale != null && StringUtils.isNotEmpty(locale.getLanguage())) {
				path.append("_").append(locale.getLanguage());
				if (StringUtils.isNotEmpty(locale.getCountry())) {
					path.append("_").append(locale.getCountry());
				}
			}
			path.append(suffix);
			return path.toString();
		}
	}

	/**
	 * Get a resource name according to the locale and a given mode.
	 * 
	 * @see #getResourceName(String, Locale)
	 * @param name
	 *            Name of the resource
	 * @param locale
	 *            Locale to be used
	 * @param localeMode
	 *            Indicates how the locale parameters must be used or not
	 * @return Updated resource name
	 */
	public static String getResourceName(String name, Locale locale, ELocaleMode localeMode) {
		locale = localeMode.getLocale(locale);
		if (locale == null) {
			return getResourceName(name, null);
		} else {
			return getResourceName(name, locale);
		}
	}

	/**
	 * Tests if <code>localeA</code> is more specific than
	 * <code>localeB</code>. A locale is more specific if it defines
	 * supplementary elements (country) while the language is the same.
	 * 
	 * @param localeA
	 *            First locale
	 * @param localeB
	 *            Second locale
	 * @return <code>true</code> if the <code>localeA</code> is more
	 *         specific than <code>localeB</code>
	 */
	public static boolean isMoreSpecific(Locale localeA, Locale localeB) {
		String languageA = localeA.getLanguage();
		String languageB = localeB.getLanguage();
		// Languages must be equals
		if (StringUtils.equals(languageA, languageB)) {
			// Country A must be set and country B must not be set
			return StringUtils.isNotBlank(localeA.getCountry()) && StringUtils.isBlank(localeB.getCountry());
		} else {
			return false;
		}
	}
}
