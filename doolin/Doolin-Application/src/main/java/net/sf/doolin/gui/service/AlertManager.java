/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.service;

import java.awt.Component;

import net.sf.doolin.gui.core.View;

/**
 * This interface defines the manager in charge of displaying message to the
 * user.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AlertManager.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface AlertManager {

	/**
	 * Displays an exception to a user.
	 * 
	 * @param view
	 *            View the exception has occurred in.
	 * @param th
	 *            Exception to display details about
	 */
	void displayException(View view, Throwable th);

	/**
	 * Displays an error message
	 * 
	 * @param view
	 *            Parent view
	 * @param code
	 *            Error code
	 * @param params
	 *            Error parameters
	 * @see GUIStrings#get(String, Object[])
	 */
	void error(View view, String code, Object... params);

	/**
	 * Displays an error message
	 * 
	 * @param component
	 *            Parent component
	 * @param code
	 *            Error code
	 * @param params
	 *            Error parameters
	 * @see GUIStrings#get(String, Object[])
	 * @deprecated Please use the {@link #error(View, String, Object...)} method
	 */
	@Deprecated
	void error(Component component, String code, Object... params);

	/**
	 * Displays an information message
	 * 
	 * @param view
	 *            Parent view
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @see GUIStrings#get(String, Object[])
	 */
	void message(View view, String code, Object... params);

	/**
	 * Displays an information message
	 * 
	 * @param component
	 *            Parent component
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @see GUIStrings#get(String, Object[])
	 * @deprecated Please use the {@link #message(View, String, Object...)}
	 *             method
	 */
	@Deprecated
	void message(Component component, String code, Object... params);

	/**
	 * Displays a confirmation message
	 * 
	 * @param view
	 *            Parent view
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @return Result of the confirmation
	 * @see GUIStrings#get(String, Object[])
	 */
	boolean confirm(View view, String code, Object... params);

	/**
	 * Displays a confirmation message
	 * 
	 * @param component
	 *            Parent component
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @return Result of the confirmation
	 * @see GUIStrings#get(String, Object[])
	 * @deprecated Please use {@link #confirm(View, String, Object...)} method
	 */
	@Deprecated
	boolean confirm(Component component, String code, Object... params);

	/**
	 * Confirm for a save, with three possible results: yes, no or cancel.
	 * 
	 * @param view
	 *            Parent view
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @return Result of the confirmation
	 * @see GUIStrings#get(String, Object[])
	 */
	ConfirmResult confirmSave(View view, String code, Object... params);

	/**
	 * Confirm for a save, with three possible results: yes, no or cancel.
	 * 
	 * @param parent
	 *            Parent component
	 * @param code
	 *            Message code
	 * @param params
	 *            Message parameters
	 * @return Result of the confirmation
	 * @see GUIStrings#get(String, Object[])
	 * @deprecated Please use {@link #confirmSave(View, String, Object...)}
	 *             method
	 */
	@Deprecated
	ConfirmResult confirmSave(Component parent, String code, Object... params);

}
