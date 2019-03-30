/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.auth.support;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.auth.Authentication;
import net.sf.doolin.gui.auth.Session;
import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.Startup;

/**
 * Startup of the application using a login session.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LoginStartup.java,v 1.3 2007/08/17 15:06:55 guinnessman Exp $
 */
public class LoginStartup implements Startup {

	private Startup success;

	private Startup failure = new LoginFailureStartup();

	private Authentication authentication;

	public void start(ApplicationManager applicationManager) {
		if (isSessionOk()) {
			doStart(applicationManager);
		} else {
			doAuthenticate(applicationManager);
			if (isSessionOk()) {
				doStart(applicationManager);
			} else {
				doFail(applicationManager);
			}
		}
	}

	/**
	 * Launches the authentication.
	 * 
	 * @param applicationManager
	 *            Running application manager
	 */
	protected void doAuthenticate(ApplicationManager applicationManager) {
		authentication.authenticate();
	}

	/**
	 * Checks the state of the session.
	 * 
	 * @return <code>true</code> if the user is authenticated.
	 */
	protected boolean isSessionOk() {
		return Session.getInstance().getUser() != null;
	}

	/**
	 * Launches the success startup.
	 * 
	 * @param applicationManager
	 *            Running application manager
	 */
	protected void doStart(ApplicationManager applicationManager) {
		success.start(applicationManager);
	}

	/**
	 * Launches the failure startup.
	 * 
	 * @param applicationManager
	 *            Running application manager
	 */
	protected void doFail(ApplicationManager applicationManager) {
		failure.start(applicationManager);
	}

	/**
	 * Returns the <code>success</code> property.
	 * 
	 * @return <code>success</code> property.
	 */
	public Startup getSuccess() {
		return success;
	}

	/**
	 * Sets the <code>success</code> property.
	 * 
	 * @param success
	 *            <code>success</code> property.
	 */
	@Configurable(mandatory = true, description = "Startup to launch if login succeeds")
	public void setSuccess(Startup success) {
		this.success = success;
	}

	/**
	 * Returns the <code>failure</code> property.
	 * 
	 * @return <code>failure</code> property.
	 */
	public Startup getFailure() {
		return failure;
	}

	/**
	 * Sets the <code>failure</code> property.
	 * 
	 * @param failure
	 *            <code>failure</code> property.
	 */
	@Configurable(mandatory = false, description = "Startup to launch if login fails", defaultsTo = "net.sf.doolin.gui.auth.support.LoginFailureStartup")
	public void setFailure(Startup failure) {
		this.failure = failure;
	}

	/**
	 * Returns the <code>authentication</code> property.
	 * 
	 * @return <code>authentication</code> property.
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Sets the <code>authentication</code> property.
	 * 
	 * @param authentication
	 *            <code>authentication</code> property.
	 */
	@Configurable(mandatory = true, description = "Authentication process to apply")
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}
