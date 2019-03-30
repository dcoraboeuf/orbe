/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.model;

/**
 * Defines a column in a tabular model.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabularColumn.java,v 1.1 2007/08/14 14:09:22 guinnessman Exp $
 */
public interface TabularColumn {

	/**
	 * Returns the title of this column
	 * 
	 * @return Title
	 */
	String getTitle();

	/**
	 * Computes the column value for an item
	 * 
	 * @param item
	 *            Row item
	 * @return Value for this column
	 */
	Object getValue(Object item);

}
