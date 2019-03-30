/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.auth;

/**
 * Defines an authentication process.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Authentication.java,v 1.1 2007/08/17 15:06:51 guinnessman Exp $
 */
public interface Authentication {

	/**
	 * When this method is called, the underlying implementation must
	 * authenticate the user and register it by calling the
	 * <code>{@link Session#setUser(java.security.Principal)}</code> method.
	 * 
	 */
	void authenticate();

}
