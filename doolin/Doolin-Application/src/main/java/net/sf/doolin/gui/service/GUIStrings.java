/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

import java.util.Locale;

import net.sf.doolin.util.Strings;

/**
 * Service for localized strings.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUIStrings.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public class GUIStrings {

	public static String get(String code, Object... params) {
		return Strings.get(getLocale(), code, params);
	}

	public static String getIfPresent(String code) {
		return Strings.get(getLocale(), code, false);
	}

	public static Locale getLocale() {
		return Locale.getDefault();
	}

}
