/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.gui.ext.tabular.TabularData;
import net.sf.doolin.gui.ext.tabular.model.TabularColumn;
import net.sf.doolin.gui.ext.tabular.model.TabularModel;

/**
 * Default implementation for a tabular model.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultTabularModel.java,v 1.1 2007/08/14 14:09:23 guinnessman Exp $
 */
public class DefaultTabularModel implements TabularModel {

	private List<TabularColumn> columns = new ArrayList<TabularColumn>();

	/**
	 * Adds a column in the model
	 * 
	 * @param column
	 *            Column to add
	 */
	public void addColumn(TabularColumn column) {
		columns.add(column);
	}

	public TabularData createData(final List items) {
		return new TabularData() {

			public Object getValue(int row, int col) {
				Object item = items.get(row);
				TabularColumn column = columns.get(col);
				return column.getValue(item);
			}

			public int getRowCount() {
				return items.size();
			}

			public TabularModel getModel() {
				return DefaultTabularModel.this;
			}

			public int getColumnCount() {
				return columns.size();
			}

		};
	}

	/**
	 * Returns the list of columns.
	 * 
	 * @see net.sf.doolin.gui.ext.tabular.model.TabularModel#getColumns()
	 */
	public Iterable<TabularColumn> getColumns() {
		return columns;
	}

}
