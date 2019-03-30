/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;

import net.sf.doolin.gui.auth.AuthorizableItem;

/**
 * Menu definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Menu.java,v 1.2 2007/08/17 15:07:00 guinnessman Exp $
 */
public class Menu extends MenuItem<JMenu> implements AuthorizableItem {

	private String name;

	private List<MenuItem> items = new ArrayList<MenuItem>();

	/**
	 * @return Returns the items.
	 */
	public List<MenuItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            The items to set.
	 */
	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see #getName()
	 * @see net.sf.doolin.gui.auth.AuthorizableItem#getAuthorizationIdentifier()
	 */
	public String getAuthorizationIdentifier() {
		return getName();
	}

	/**
	 * @see AuthorizableItem.Predefined#MENU
	 * @see net.sf.doolin.gui.auth.AuthorizableItem#getAuthorizableItemType()
	 */
	public String getAuthorizableItemType() {
		return AuthorizableItem.Predefined.MENU.getType();
	}

}
