/*
 * Created on 18 nov. 2005
 */
package net.sf.doolin.gui.auth.support;

/**
 * List of expected results after an authentication attempt.
 * 
 * @author Damien Coraboeuf
 * @version $Id: EAuthenticationResult.java,v 1.1 2007/07/25 18:23:27
 *          guinnessman Exp $
 */
public enum AuthenticationResult {
	/**
	 * Authentication successful.
	 */
	AUTHENTICATION_SUCCESSFUL,
	/**
	 * Authentication failed
	 */
	AUTHENTICATION_FAILED,
	/**
	 * Authentication canceled.
	 */
	AUTHENTICATION_CANCELLED;
}
