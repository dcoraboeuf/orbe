/*
 * Created on Sep 13, 2007
 */
package net.sf.doolin.gui.core;

/**
 * Interface for objects which have to listen for changes on actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface ActionListener {

	/**
	 * This method is called whenever there is a change into the action.
	 * 
	 * @param action
	 *            Changed action
	 */
	void onActionChanged(Action action);

}
