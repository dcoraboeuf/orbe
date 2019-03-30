/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.auth;

import java.security.Principal;

/**
 * Defines the current session of a user.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Session.java,v 1.1 2007/08/17 15:06:51 guinnessman Exp $
 */
public class Session {

	/**
	 * Unique instance
	 */
	private static Session instance = null;

	/**
	 * Get the instance
	 * 
	 * @return Current session
	 */
	public static Session getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized Session createInstance() {
		if (instance != null) {
			return instance;
		} else {
			Session temp = new Session();
			instance = temp;
			return instance;
		}
	}

	private Principal user;

	/**
	 * Initialization
	 */
	private Session() {

	}

	/**
	 * Returns the <code>user</code> property.
	 * 
	 * @return <code>user</code> property.
	 */
	@SuppressWarnings("unchecked")
	public Object getUser() {
		return user;
	}

	/**
	 * Sets the <code>user</code> property.
	 * 
	 * @param user
	 *            <code>user</code> property.
	 */
	public void setUser(Principal user) {
		this.user = user;
	}

}
