/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.auth.support;

import java.security.Principal;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.auth.Authentication;
import net.sf.doolin.gui.auth.Session;
import net.sf.doolin.gui.core.support.DataFactory;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;

/**
 * Skeleton for a dialog-based authentication.
 * 
 * @param <F>
 *            Type of the login form
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractDialogAuthentication.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 */
public abstract class AbstractDialogAuthentication<F> implements Authentication {

	private DataFactory<F> formFactory;

	private String dialogViewName;

	private int tries = 3;

	private AuthenticationFailedReporter<F> authenticationFailedReporter = new DefaultAuthenticationFailedReporter<F>();

	public void authenticate() {
		// Creates the login form
		F form = formFactory.createData();
		// All tries
		int count = 0;
		while (count < tries) {
			AuthenticationResult result = tryAuthentication(form);
			if (result == AuthenticationResult.AUTHENTICATION_SUCCESSFUL) {
				// Success
				break;
			} else if (result == AuthenticationResult.AUTHENTICATION_CANCELLED) {
				// Cancellation
				break;
			} else {
				// Failure, tries again
				count++;
			}
		}
	}

	/**
	 * Tries to authenticate the user using a dialog.
	 * 
	 * @param form
	 *            Login form
	 * @return Result of the try.
	 */
	protected AuthenticationResult tryAuthentication(F form) {
		// Displays the dialog
		boolean dialogOk = GUIUtils.openDialog(null, dialogViewName, form);
		if (dialogOk) {
			// Checks the login
			ValidationReport report = new ValidationReport();
			Principal user = doLogin(form, report);
			if (user != null) {
				Session.getInstance().setUser(user);
				return AuthenticationResult.AUTHENTICATION_SUCCESSFUL;
			} else if (!report.isOk()) {
				// Displays the errors
				authenticationFailedReporter.report(report, form);
				// Failed
				return AuthenticationResult.AUTHENTICATION_FAILED;
			} else {
				return AuthenticationResult.AUTHENTICATION_FAILED;
			}
		} else {
			return AuthenticationResult.AUTHENTICATION_CANCELLED;
		}
	}

	/**
	 * Does the actual login.
	 * 
	 * @param form
	 *            Login form
	 * @param report
	 *            Report to fill in case of errors
	 * @return Authenticated user or <code>null</code> if it cannot be
	 *         authenticated.
	 */
	protected abstract Principal doLogin(F form, ValidationReport report);

	/**
	 * Returns the <code>tries</code> property.
	 * 
	 * @return <code>tries</code> property.
	 */
	public int getTries() {
		return tries;
	}

	/**
	 * Sets the <code>tries</code> property.
	 * 
	 * @param tries
	 *            <code>tries</code> property.
	 */
	@Configurable(mandatory = false, description = "Number of tries", defaultsTo = "3")
	public void setTries(int tries) {
		this.tries = tries;
	}

	/**
	 * Returns the <code>formFactory</code> property.
	 * 
	 * @return <code>formFactory</code> property.
	 */
	public DataFactory<F> getFormFactory() {
		return formFactory;
	}

	/**
	 * Sets the <code>formFactory</code> property.
	 * 
	 * @param formFactory
	 *            <code>formFactory</code> property.
	 */
	@Configurable(mandatory = true, description = "Factory for the initial login form")
	public void setFormFactory(DataFactory<F> formFactory) {
		this.formFactory = formFactory;
	}

	/**
	 * Returns the <code>dialogViewName</code> property.
	 * 
	 * @return <code>dialogViewName</code> property.
	 */
	public String getDialogViewName() {
		return dialogViewName;
	}

	/**
	 * Sets the <code>dialogViewName</code> property.
	 * 
	 * @param dialogViewName
	 *            <code>dialogViewName</code> property.
	 */
	@Configurable(mandatory = true, description = "View name of the login dialog")
	public void setDialogViewName(String dialogViewName) {
		this.dialogViewName = dialogViewName;
	}

	/**
	 * Returns the <code>authenticationFailedReporter</code> property.
	 * 
	 * @return <code>authenticationFailedReporter</code> property.
	 */
	public AuthenticationFailedReporter<F> getAuthenticationFailedReporter() {
		return authenticationFailedReporter;
	}

	/**
	 * Sets the <code>authenticationFailedReporter</code> property.
	 * 
	 * @param authenticationFailedReporter
	 *            <code>authenticationFailedReporter</code> property.
	 */
	@Configurable(mandatory = false, description = "Reporter for authentication error")
	public void setAuthenticationFailedReporter(AuthenticationFailedReporter<F> authenticationFailedReporter) {
		this.authenticationFailedReporter = authenticationFailedReporter;
	}

}
