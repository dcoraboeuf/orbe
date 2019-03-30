/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core;

import net.sf.doolin.gui.auth.AuthorizableItem;

/**
 * Defines an action that can be executed in a Doolin GUI application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Action.java,v 1.4 2007/08/17 15:06:57 guinnessman Exp $
 */
public interface Action extends AuthorizableItem {

	/**
	 * Sets the view the action is executed in.
	 * 
	 * @param view
	 *            View the action is executed in.
	 */
	void setView(View view);

	/**
	 * Executes the action. This method should not throw any exception and
	 * should handle them properly (by displaying a message for example).
	 * 
	 */
	void execute();

	/**
	 * Returns the logical name of the action.
	 * 
	 * @return Action name
	 */
	String getName();

	/**
	 * Returns the enabed state of this action
	 * 
	 * @return <code>true</code> if the action is enabled
	 */
	boolean isEnabled();

	/**
	 * Sets the enabled state of this action
	 * 
	 * @param enabled
	 *            Enabled state
	 */
	void setEnabled(boolean enabled);

	/**
	 * Adds an action listener for this action
	 * 
	 * @param listener
	 *            Listener
	 */
	void addActionListener(ActionListener listener);

	/**
	 * Removes an action listener for this action
	 * 
	 * @param listener
	 *            Listener
	 */
	void removeActionListener(ActionListener listener);

}
