/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.view.Menu;

/**
 * Defines an object subject to authorizations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AuthorizableItem.java,v 1.1 2007/08/17 15:06:50 guinnessman Exp $
 */
public interface AuthorizableItem {

	/**
	 * List of predefined authorizable item types
	 * 
	 * @author Damien Coraboeuf
	 * @version $Id: AuthorizableItem.java,v 1.1 2007/08/17 15:06:50 guinnessman Exp $
	 */
	public static enum Predefined {
		/**
		 * @see Action
		 */
		ACTION("Action"),
		/**
		 * @see Menu
		 */
		MENU("Menu");

		private String type;

		private Predefined(String pType) {
			type = pType;
		}

		/**
		 * Returns the authorizable item type
		 * 
		 * @return Authorizable item type
		 */
		public String getType() {
			return type;
		}
	}

	/**
	 * Category of authorized item
	 * 
	 * @return Category
	 */
	String getAuthorizableItemType();

	/**
	 * Identifier for the authorized item
	 * 
	 * @return Identifier
	 */
	String getAuthorizationIdentifier();

}
