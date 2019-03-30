/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.support;

import java.security.Principal;

import net.sf.doolin.gui.auth.AuthManager;
import net.sf.doolin.gui.auth.AuthorizableItem;
import net.sf.doolin.gui.auth.Session;
import net.sf.doolin.gui.core.View;

/**
 * Utility nancestor for authorization manager implementations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractAuthManager.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 * 
 * @param <T>
 *            Type of user managed by this authorization manager
 */
public abstract class AbstractAuthManager<T extends Principal> implements AuthManager {

	/**
	 * @see net.sf.doolin.gui.auth.AuthManager#isAuthorized(View,
	 *      AuthorizableItem)
	 */
	@SuppressWarnings("unchecked")
	public boolean isAuthorized(View view, AuthorizableItem item) {
		// Gets the current user
		T user = (T) Session.getInstance().getUser();
		// Fetch authorization for the user
		return isAuthorized(user, view, item);
	}

	/**
	 * Checks if an item is authorized to the current user.
	 * 
	 * @param user
	 *            Current user (may be <code>null</code> if no authentication
	 *            has been performed)
	 * @param view
	 *            Contextual view (can be <code>null</code> for some items).
	 * @param item
	 *            Item to authorize.
	 * @return Authorization
	 */
	protected abstract boolean isAuthorized(T user, View view, AuthorizableItem item);

}
