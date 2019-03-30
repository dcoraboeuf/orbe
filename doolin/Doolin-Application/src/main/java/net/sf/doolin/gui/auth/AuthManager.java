/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.view.Menu;

/**
 * Authorization manager.
 * 
 * Items that can be authorized are:
 * <ul>
 * <li>{@link Action Actions} (view not <code>null</code>)
 * <li>{@link Menu Menus} (view not <code>null</code>)
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id: AuthManager.java,v 1.1 2007/08/17 15:06:51 guinnessman Exp $
 */
public interface AuthManager {

	/**
	 * Checks if an item is authorized to the current user.
	 * 
	 * @param view
	 *            Contextual view (can be <code>null</code> for some items).
	 * @param item
	 *            Item to authorize.
	 * @return Authorization
	 */
	boolean isAuthorized(View view, AuthorizableItem item);

}
