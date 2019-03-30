/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.table.support;

import java.util.List;

import net.sf.doolin.gui.field.support.FieldSupport;
import net.sf.doolin.gui.table.FieldTable;

/**
 * Field support definition for a <code>{@link FieldTable}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TableSupport.java,v 1.1 2007/08/14 11:48:55 guinnessman Exp $
 */
public interface TableSupport extends FieldSupport<FieldTable> {

	/**
	 * Gets the list of items in the table
	 * 
	 * @return List of items
	 */
	List getItems();

	/**
	 * Sets the list of items in the table
	 * 
	 * @param items
	 *            List of items
	 */
	void setItems(List items);

	/**
	 * Adds an item at the end of the table
	 * 
	 * @param item
	 *            Item to add
	 */
	void addItem(Object item);

	/**
	 * Deletes an item from the table
	 * 
	 * @param item
	 *            Item to delete
	 */
	void deleteItem(Object item);

	/**
	 * Updates an item in the table
	 * 
	 * @param item
	 *            Item to update
	 */
	void updateItem(Object item);

	/**
	 * Gets the current selected item
	 * 
	 * @return Selected item or <code>null</code> if none is selected.
	 */
	Object getSelectedItem();

	/**
	 * Resets the columns.
	 * 
	 */
	void resetColumns();

}
