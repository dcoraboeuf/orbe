/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.model;

import java.util.List;

import net.sf.doolin.gui.ext.tabular.TabularData;

/**
 * Defines the structure of some tabular data.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabularModel.java,v 1.1 2007/08/14 14:09:22 guinnessman Exp $
 */
public interface TabularModel {

	/**
	 * Creates a tabular data from this model definition and a list of items.
	 * 
	 * @param items
	 *            List of items
	 * @return Tabular dataF
	 */
	TabularData createData(List items);

	/**
	 * Returns the list of columns in the model
	 * 
	 * @return List of columns
	 */
	Iterable<TabularColumn> getColumns();

}
