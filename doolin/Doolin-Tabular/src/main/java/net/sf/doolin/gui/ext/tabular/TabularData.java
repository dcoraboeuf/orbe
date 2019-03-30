/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular;

import net.sf.doolin.gui.ext.tabular.model.TabularModel;

/**
 * Defines a content which is arranged like in a table, according to a model.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabularData.java,v 1.1 2007/08/14 14:09:21 guinnessman Exp $
 */
public interface TabularData {

	/**
	 * Returns the associated model.
	 * 
	 * @return Model
	 */
	TabularModel getModel();

	/**
	 * Returns the number of rows
	 * 
	 * @return Number of rows
	 */
	int getRowCount();

	/**
	 * Returns the number of columns
	 * 
	 * @return Number of columns
	 */
	int getColumnCount();

	/**
	 * Returns the value for the cell (row, column)
	 * 
	 * @param row
	 *            Row index
	 * @param col
	 *            Column index
	 * @return Value
	 */
	Object getValue(int row, int col);

}
