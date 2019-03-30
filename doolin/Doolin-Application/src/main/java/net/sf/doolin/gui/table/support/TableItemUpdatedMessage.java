/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.gui.table.support;

import net.sf.doolin.gui.table.Column;

/**
 * Bus message that is published by a table when an item has been updated.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TableItemUpdatedMessage.java,v 1.1 2007/08/14 11:48:55 guinnessman Exp $
 */
public interface TableItemUpdatedMessage {

	/**
	 * Defines the column which has been updaded.
	 * 
	 * @param column
	 *            Updated column
	 */
	void setColumn(Column column);

	/**
	 * Set the updated item
	 * 
	 * @param item
	 *            Updated item
	 */
	void setItem(Object item);

}
