/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.table.support;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.ObjectUtils;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.table.Column;

import net.sf.doolin.util.Utils;

public class GUITableModel extends AbstractTableModel {

	private List<Column> columns;

	private List items = new ArrayList<Object>();

	/**
	 * @param columns
	 */
	public GUITableModel(List<Column> columns) {
		this.columns = columns;
	}

	public int getColumnCount() {
		return columns.size();
	}

	public int getRowCount() {
		return items.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Column column = columns.get(columnIndex);
		return column.getColumnClass();
	}

	@Override
	public String getColumnName(int index) {
		Column column = columns.get(index);
		String title = GUIStrings.get(column.getTitle());
		return title;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object item = items.get(rowIndex);
		Column column = columns.get(columnIndex);
		String property = column.getProperty();
		return GUIUtils.getProperty(item, property);
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
		fireTableDataChanged();
	}

	@SuppressWarnings("unchecked")
	public void addItem(Object item) {
		int row = items.size();
		items.add(item);
		fireTableRowsInserted(row, row);
	}

	public void deleteItem(Object item) {
		int row = items.indexOf(item);
		if (row >= 0) {
			items.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateItem(Object item) {
		int row = items.indexOf(item);
		if (row >= 0) {
			items.set(row, item);
			fireTableRowsUpdated(row, row);
		}
	}

	public Object getItem(int row) {
		return items.get(row);
	}

	/**
	 * A cell is editable if its column is editable or contains a custom editor.
	 * 
	 * @see Column#isEditable()
	 * @see Column#getEditor()
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 * @TODO Adds an authorization check on both the item and the column
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		Column column = columns.get(columnIndex);
		return column.isEditable() || column.getEditor() != null;
	}

	/**
	 * Updates the value for a cell after it has been edited.
	 * 
	 * First, the corresponding column property is updated in the item at the
	 * row.
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 *      int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// Edited item
		Object item = items.get(rowIndex);
		// Column property
		Column column = columns.get(columnIndex);
		String property = column.getProperty();
		// Checks the change
		Object oldValue = GUIUtils.getProperty(item, property);
		if (ObjectUtils.equals(oldValue, aValue)) {
			// No change
			return;
		}
		// Updates its property
		GUIUtils.setProperty(item, property, aValue);
		// Update mode
		switch (column.getUpdatePolicy()) {
		case TABLE:
			fireTableDataChanged();
			break;
		case ROW:
			fireTableRowsUpdated(rowIndex, rowIndex);
			break;
		case COLUMN:
			int size = items.size();
			for (int row = 0; row < size; row++) {
				fireTableCellUpdated(row, columnIndex);
			}
			break;
		default:
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		}
		// Publishes the update
		Class<? extends TableItemUpdatedMessage> messageType = column.getEditionMessageType();
		if (messageType != null) {
			TableItemUpdatedMessage message = Utils.newInstance(messageType);
			message.setColumn(column);
			message.setItem(item);
			Bus.get().publish(message);
		}
	}

}
