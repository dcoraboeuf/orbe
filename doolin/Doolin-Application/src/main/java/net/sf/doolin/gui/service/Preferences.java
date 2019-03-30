/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

import java.io.File;

/**
 * Defines the interface of a preferences manager for a Doolin GUI application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Preferences.java,v 1.2 2007/08/15 11:52:44 guinnessman Exp $
 */
public interface Preferences {

	/**
	 * Get a preference value as a string
	 * 
	 * @param key
	 *            Preference key
	 * @param defaultValue
	 *            Default value if the key is not defined
	 * @return Preference value
	 */
	String getString(String key, String defaultValue);

	/**
	 * Get a preference value as a boolean
	 * 
	 * @param key
	 *            Preference key
	 * @param defaultValue
	 *            Default value if the key is not defined
	 * @return Preference value
	 */
	boolean getBoolean(String key, boolean defaultValue);

	/**
	 * Get a preference value as an integer
	 * 
	 * @param key
	 *            Preference key
	 * @param defaultValue
	 *            Default value if the key is not defined
	 * @return Preference value
	 */
	int getInt(String key, int defaultValue);

	/**
	 * Sets a preference value as a string
	 * 
	 * @param key
	 *            Preference key
	 * @param value
	 *            Value to store
	 */
	void setString(String key, String value);

	/**
	 * Sets a preference value as a string
	 * 
	 * @param key
	 *            Preference key
	 * @param value
	 *            Value to store
	 */
	void setBoolean(String key, boolean value);

	/**
	 * Sets a preference value as an integer
	 * 
	 * @param key
	 *            Preference key
	 * @param value
	 *            Value to store
	 */
	void setInt(String key, int value);

	/**
	 * Saves the preferences in the back store
	 */
	void save();

	/**
	 * Gets a preference value as a file.
	 * 
	 * @param key
	 *            Preference key
	 * @return File or <code>null</code> if not found
	 */
	File getFile(String key);

	/**
	 * Sets a preference value as a file.
	 * 
	 * @param key
	 *            Preference key
	 * @param file
	 *            File to store
	 */
	void setFile(String key, File file);

	/**
	 * Saves a <code>{@link Memento}</code> in the preferences.
	 * 
	 * @param memento
	 *            Memento to save
	 */
	void saveMemento(Memento memento);

	/**
	 * Restores a <code>{@link Memento}</code> from the preferences.
	 * 
	 * @param memento
	 *            Memento to restore
	 */
	void restoreMemento(Memento memento);

	/**
	 * Deletes a preference entry.
	 * 
	 * @param key
	 *            Preference entry key
	 */
	void delete(String key);

}
