/*
 * Created on Aug 8, 2007
 */
package net.sf.doolin.gui.field.helper;

import java.util.List;

/**
 * Provides a list of items from a property.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ListItemProvider.java,v 1.2 2007/08/15 09:05:26 guinnessman Exp $
 */
public class ListItemProvider implements ItemProvider {

	private List items;

	/**
	 * Returns the items
	 * 
	 * @return List
	 */
	public List getItems() {
		return items;
	}

	/**
	 * Sets the items
	 * 
	 * @param items
	 *            List
	 */
	public void setItems(List items) {
		this.items = items;
	}

}
