/*
 * Created on 9 avr. 2005
 */
package net.sf.doolin.gui.swing.select;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.doolin.util.unit.Unit;
import net.sf.doolin.util.unit.ValueUnit;

import net.sf.doolin.gui.field.helper.LabelInfo;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.swing.SwingUtils;

/**
 * List whose items can be selected
 * 
 * @author damien
 * @version $Id: SelectableList.java,v 1.1 2007/08/07 16:47:08 guinnessman Exp $
 * @param <T>
 *            Type of item in the list
 */
public class SelectableList<T> extends JPanel {
	private JTable table;

	private SelectableModel<T> model;

	private int visibleRows = 4;

	private JScrollPane scrollPane;

	private LabelInfoProvider labelProvider;

	/**
	 * Constructor
	 */
	public SelectableList() {
		model = new SelectableModel<T>();
		// Layout
		setLayout(new BorderLayout());
		// Table
		table = new JTable();
		// Scroll pane
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(200, table.getRowHeight() * visibleRows));
		add(scrollPane);
	}

	/**
	 * Set a list of items
	 * 
	 * @param items
	 *            List of items
	 */
	public void setItems(List<T> items) {
		model.setItems(items);
		table.setModel(model);
		table.getColumnModel().getColumn(1).setCellRenderer(new LabelCellRenderer());
		SwingUtils.setTableColumnWidth(table, 0, new ValueUnit(24, Unit.PIXEL), false);
	}

	/**
	 * Renderer for the items
	 */
	protected class LabelCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Icon icon = null;
			if (labelProvider != null) {
				LabelInfo labelIcon = labelProvider.getLabelIcon(value);
				value = labelIcon.getText();
				icon = labelIcon.getIcon();
			}
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			setIcon(icon);
			return this;
		}

	}

	/**
	 * Set the selected items
	 * 
	 * @param items
	 *            List of selected items
	 */
	public void setSelectedItems(List<T> items) {
		model.setSelectedItems(items);
	}

	/**
	 * Get the selected items
	 * 
	 * @return List of selected items
	 */
	public List<T> getSelectedItems() {
		return model.getSelectedItems();
	}

	/**
	 * Adds a listener for the selection
	 * 
	 * @param l
	 *            Listener
	 */
	public void addSelectedEventListener(SelectedEventListener l) {
		model.addSelectedEventListener(l);
	}

	/**
	 * Removes a listener for the selection
	 * 
	 * @param l
	 *            Listener
	 */
	public void removeSelectedEventListener(SelectedEventListener l) {
		model.removeSelectedEventListener(l);
	}

	/**
	 * Enablement
	 */
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		table.setEnabled(enabled);
	}

	/**
	 * Returns the list of visibles rows
	 * 
	 * @return Number of rows
	 */
	public int getVisibleRows() {
		return visibleRows;
	}

	/**
	 * Sets the number of visible rows
	 * 
	 * @param visibleRows
	 *            Number of rows
	 */
	public void setVisibleRows(int visibleRows) {
		this.visibleRows = visibleRows;
		Dimension d = scrollPane.getPreferredSize();
		int rowHeight = table.getRowHeight();
		d.height = rowHeight * visibleRows;
		scrollPane.setPreferredSize(d);
	}

	/**
	 * Returns the label provider
	 * 
	 * @return Label provider
	 */
	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	/**
	 * Sets the label provider
	 * 
	 * @param labelProvider
	 *            Label provider
	 */
	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
}
