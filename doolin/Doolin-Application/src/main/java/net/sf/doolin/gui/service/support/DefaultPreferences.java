/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import net.sf.doolin.gui.Application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of preferences based on default JDK preferences
 * implementation.
 * 
 * @see Preferences
 * @author Damien Coraboeuf
 * @version $Id: DefaultPreferences.java,v 1.2 2007/07/31 15:33:06 guinnessman
 *          Exp $
 */
public class DefaultPreferences extends AbstractPreferences {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultPreferences.class);

	private Preferences preferences;

	/**
	 * Initializes the preferences.
	 * 
	 */
	protected void init() {
		if (preferences == null) {
			syncInit();
		}
	}

	/**
	 * Actual initialization. This method is called only once in the life-cycle
	 * of the preferences and is synchronized.
	 * 
	 */
	protected synchronized void syncInit() {
		if (preferences == null) {
			Class idClass = Application.getIdClass();
			Preferences prefs = Preferences.userNodeForPackage(idClass);
			preferences = prefs;
		}
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		init();
		return preferences.getBoolean(key, defaultValue);
	}

	public int getInt(String key, int defaultValue) {
		init();
		return preferences.getInt(key, defaultValue);
	}

	public String getString(String key, String defaultValue) {
		init();
		return preferences.get(key, defaultValue);
	}

	public void save() {
		init();
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			log.error("Cannot flush preferences", e);
		}
	}

	public void setBoolean(String key, boolean value) {
		init();
		preferences.putBoolean(key, value);
	}

	public void setInt(String key, int value) {
		init();
		preferences.putInt(key, value);
	}

	public void setString(String key, String value) {
		init();
		preferences.put(key, value);
	}

	public File getFile(String key) {
		init();
		String path = getString(key, null);
		if (path != null) {
			return new File(path);
		} else {
			return null;
		}
	}

	public void setFile(String key, File file) {
		init();
		if (file != null) {
			preferences.put(key, file.getAbsolutePath());
		} else {
			preferences.remove(key);
		}
	}

	@Override
	public void delete(String key) {
		preferences.remove(key);
	}

}
