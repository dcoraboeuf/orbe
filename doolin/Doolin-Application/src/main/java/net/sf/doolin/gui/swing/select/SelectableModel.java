/*
 * Created on 9 avr. 2005
 */
package net.sf.doolin.gui.swing.select;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Model for a selectable list
 * 
 * @author damien
 * @version $Id: SelectableModel.java,v 1.1 2007/08/07 16:47:08 guinnessman Exp $
 * @param <T>
 *            Type of selectable item
 */
public class SelectableModel<T> extends AbstractTableModel {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(SelectableModel.class);

	/**
	 * First column, used for checkbox.
	 */
	public final static int COL_SELECT = 0;

	/**
	 * Second column, used for the item itself.
	 */
	public final static int COL_ITEM = 1;

	/**
	 * Listeners
	 */
	private ArrayList<SelectedEventListener> listeners = new ArrayList<SelectedEventListener>();

	/**
	 * List of items
	 */
	private List<SelectableItem<T>> items;

	/**
	 * Constuctor
	 */
	public SelectableModel() {
		items = new ArrayList<SelectableItem<T>>();
	}

	/**
	 * Set the items
	 * 
	 * @param list
	 *            List of items
	 */
	public void setItems(List<T> list) {
		items = new ArrayList<SelectableItem<T>>();
		for (T t : list) {
			items.add(new SelectableItem<T>(t));
		}
		fireTableDataChanged();
	}

	/**
	 * Adds a listener
	 * 
	 * @param l
	 *            Listener
	 */
	public void addSelectedEventListener(SelectedEventListener l) {
		listeners.add(l);
	}

	/**
	 * Removes a listener
	 * 
	 * @param l
	 *            Listener
	 */
	public void removeSelectedEventListener(SelectedEventListener l) {
		listeners.remove(l);
	}

	/**
	 * Fires selected event
	 */
	protected void fireSelectedEvent() {
		log.debug("Selected item");
		SelectedEvent e = new SelectedEvent(this);
		SelectedEventListener[] list = listeners.toArray(new SelectedEventListener[0]);
		for (int i = 0; i < list.length; i++) {
			SelectedEventListener listener = list[i];
			log.debug("Notify to " + listener);
			listener.itemSelected(e);
		}
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == COL_SELECT) {
			return Boolean.class;
		} else {
			return Object.class;
		}
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COL_SELECT;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 *      int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == COL_SELECT) {
			SelectableItem<T> t = items.get(rowIndex);
			t.setSelected(((Boolean) aValue).booleanValue());
			fireSelectedEvent();
		}
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 2;
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return items.size();
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		SelectableItem<T> t = items.get(rowIndex);
		if (columnIndex == COL_SELECT) {
			return t.isSelected();
		} else {
			return t.getItem();
		}
	}

	/**
	 * Set the selected items
	 * 
	 * @param list
	 *            List of selected items
	 */
	public void setSelectedItems(List<T> list) {
		for (SelectableItem<T> i : items) {
			T t = i.getItem();
			i.setSelected(list != null && list.contains(t));
		}
		fireTableDataChanged();
	}

	/**
	 * Get the selected items
	 * 
	 * @return List of selected items
	 */
	public List<T> getSelectedItems() {
		ArrayList<T> list = new ArrayList<T>();
		for (SelectableItem<T> i : items) {
			T t = i.getItem();
			if (i.isSelected()) {
				list.add(t);
			}
		}
		return list;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return null;
	}
}
