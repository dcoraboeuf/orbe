package net.sf.doolin.util;

import java.text.MessageFormat;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * String server.
 * 
 * @version $Id: Strings.java,v 1.1 2007/07/18 18:41:20 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public abstract class Strings {
	/**
	 * Logging
	 */
	private static Log log = LogFactory.getLog(Strings.class);

	/**
	 * All paths
	 */
	private static LinkedList<String> paths = new LinkedList<String>();

	/**
	 * List of resource bundle indexed by locales.
	 */
	private static HashMap<Locale, ResourceBundle> resources = new HashMap<Locale, ResourceBundle>();

	/**
	 * Custom properties, added at runtime
	 */
	private static HashMap<String, String> localProperties = new HashMap<String, String>();

	/**
	 * Default strings
	 */
	static {
		add("net.sf.doolin.util.DefaultStrings");
	}

	/**
	 * Get a bundle for a locale
	 * 
	 * @param locale
	 *            Locale used for the search. If <code>null</code>, default
	 *            locale is used.
	 * @return Resource bundle
	 */
	private static ResourceBundle getBundle(Locale locale) {
		// Default locale is null
		if (locale == null) {
			locale = Locale.getDefault();
		}
		// Find in cache
		ResourceBundle bundle = resources.get(locale);
		if (bundle == null) {
			return loadBundle(locale);
		} else {
			return bundle;
		}
	}

	/**
	 * Load a bundle
	 * 
	 * @param locale
	 *            Locale to be used.
	 * @return An instance of <code>MultiResourceBundle</code>
	 */
	private synchronized static ResourceBundle loadBundle(Locale locale) {
		MultiResourceBundle bundle = new MultiResourceBundle(locale, paths);
		resources.put(locale, bundle);
		return bundle;
	}

	/**
	 * Adds a supplementary resource path
	 * 
	 * @param path
	 *            Bundle's name to be added.
	 */
	public static void add(String path) {
		log.info("Add I18N String path " + path);
		paths.offer(path);
		resources.clear();
	}

	/**
	 * Adds a supplementary resource path so it is the first one in the queue
	 * 
	 * @param path
	 *            Bundle's name to be added.
	 */
	public static void addFirst(String path) {
		log.info("Add I18N String path " + path + " at beginning");
		paths.addFirst(path);
		resources.clear();
	}

	/**
	 * Completes the value with other codes. It means that if the located string
	 * contains references to other codes, those ones are expanded. References
	 * are expressed using @[<i>code</i>] syntax.
	 * @param locale
	 *            Locale to be used (default locale is <code>null</code>).
	 * @param value
	 *            Value to expand
	 * @return Expanded string
	 */
	private static String complete(Locale locale, String value) {
		int pos = value.indexOf("@[");
		if (pos >= 0) {
			StringBuffer buffer = new StringBuffer();
			int oldpos = 0;
			while ((pos = value.indexOf("@[", oldpos)) >= 0) {
				int endpos = value.indexOf(']', pos + 2);
				if (endpos > 0) {
					String key = value.substring(pos + 2, endpos);
					String systemValue = Strings.get(locale, key);
					if (systemValue != null) {
						buffer.append(value.substring(oldpos, pos));
						buffer.append(systemValue);
						oldpos = endpos + 1;
					} else {
						buffer.append(value.substring(oldpos, endpos + 1));
						oldpos = endpos + 1;
					}
				} else {
					pos = -1;
				}
			}
			buffer.append(value.substring(oldpos));
			return buffer.toString();
		} else {
			return value;
		}
	}

	/**
	 * Get a string using a code and some parameters.
	 * 
	 * @param locale
	 *            Locale to be used (default locale is <code>null</code>).
	 * @param code
	 *            Code of the string
	 * @param params
	 *            Parameters for the string
	 * @return Corresponding string
	 * @see #get(Locale, String, boolean)
	 */
	public static String get(Locale locale, String code, Object... params) {
		String pattern = get(locale, code, true);
		if (pattern.indexOf('{') >= 0) {
			// Replace each occurence by its localized form if possible
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					if (param != null && param instanceof Localizable) {
						Localizable localizable = (Localizable) param;
						String value = localizable.getLocalizedMessage(locale);
						params[i] = value;
					}
				}
			}
			// Ok
			return MessageFormat.format(get(locale, code, true), params);
		} else {
			return pattern;
		}
	}

	/**
	 * Get a string by its code
	 * 
	 * @param locale
	 *            Locale to use
	 * @param code
	 *            Code
	 * @param mandatory
	 *            If the string must be found
	 * @return The string if found, <code>null</code> if not mandatory, the
	 *         code itself otherwise
	 */
	public static String get(Locale locale, String code, boolean mandatory) {
		// Get the local property if any
		String localValue = localProperties.get(code);
		if (localValue != null) {
			return localValue;
		}
		// Get the associated bundle
		ResourceBundle bundle = getBundle(locale);
		try {
			String value = bundle.getString(code);
			if (value == null) {
				if (mandatory) {
					log.warn("String key not found\t" + code);
					return code;
				} else {
					return null;
				}
			} else {
				value = complete(locale, value);
				return value;
			}
		} catch (MissingResourceException ex) {
			if (mandatory) {
				log.warn("String key not found\t" + code);
				return code;
			} else {
				return null;
			}
		}
	}

	/**
	 * Adds a custom string.
	 * 
	 * @param code
	 *            Code of the string
	 * @param value
	 *            Value for the code
	 */
	public static void add(String code, String value) {
		localProperties.put(code, value);
	}

}
