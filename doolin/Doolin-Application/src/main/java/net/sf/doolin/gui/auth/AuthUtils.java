/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth;

import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * Utility methods for the authorization framework.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AuthUtils.java,v 1.1 2007/08/17 15:06:51 guinnessman Exp $
 */
public class AuthUtils {

	private AuthUtils() {
	}

	/**
	 * Returns the current authorization manager
	 * 
	 * @return Authorization manager
	 * @see GUIUtils#getService(Class)
	 */
	public static AuthManager getAuthManager() {
		return GUIUtils.getService(AuthManager.class);
	}

}
