/*
 * Created on 15 aoutt 07
 */
package net.sf.doolin.gui.service;

/**
 * Defines an object that can saves and restores itself in preferences.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Memento.java,v 1.1 2007/08/15 11:52:44 guinnessman Exp $
 */
public interface Memento {

	/**
	 * Saves this object in the preferences.
	 * 
	 * @param preferences
	 *            Preferences to save into
	 */
	void saveMemento(Preferences preferences);

	/**
	 * Restores this object from the preferences.
	 * 
	 * @param preferences
	 *            Preferences to read from
	 */
	void restoreMemento(Preferences preferences);

}
