/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.table.support;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.sf.doolin.gui.core.support.ExtensibleListFactory;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.swing.AbstractSwingFieldSupport;
import net.sf.doolin.gui.table.Column;
import net.sf.doolin.gui.table.FieldTable;

/**
 * Default support implementation for a <code>{@link FieldTable}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingTableSupport.java,v 1.1 2007/08/14 11:48:55 guinnessman Exp $
 */
public class SwingTableSupport extends AbstractSwingFieldSupport<FieldTable> implements TableSupport {

	private JScrollPane scrollPane;

	private GUITable table;

	private GUITableModel tableModel;

	private List<Column> actualColumns;

	@Override
	protected void build() {
		// Completes the columns
		actualColumns = computeColumns();
		// Creates the table
		table = new GUITable(actualColumns);
		table.setSelectionMode(getField().isMultipleSelection() ? ListSelectionModel.MULTIPLE_INTERVAL_SELECTION : ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		// Creates a model for the table
		tableModel = new GUITableModel(actualColumns);
		table.setModel(tableModel);
		// Ok
		scrollPane = new JScrollPane(table);
	}

	/**
	 * Computes the columns
	 * 
	 * @return Actual list of columns
	 */
	protected List<Column> computeColumns() {
		List<Column> columns = getField().getColumns();
		ExtensibleListFactory<Column> columnsGenerator = getField().getColumnsGenerator();
		if (columnsGenerator != null) {
			columnsGenerator.setInitialList(columns);
			return columnsGenerator.createData();
		} else {
			return columns;
		}
	}

	/**
	 * Returns the scroll pane.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#getComponent()
	 */
	public JComponent getComponent() {
		return scrollPane;
	}

	/**
	 * Returns the table itself.
	 * 
	 * @see net.sf.doolin.gui.field.support.AbstractFieldSupport#getFocusableComponent()
	 */
	@Override
	public JComponent getFocusableComponent() {
		return table;
	}

	/**
	 * Does nothing for a table.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
	}

	public void addItem(Object item) {
		tableModel.addItem(item);
	}

	public void deleteItem(Object item) {
		tableModel.deleteItem(item);
	}

	public List getItems() {
		return tableModel.getItems();
	}

	public void setItems(List items) {
		tableModel.setItems(items);
	}

	public void updateItem(Object item) {
		tableModel.updateItem(item);
	}

	/**
	 * Gets the current selected item
	 * 
	 * @return Selected item or <code>null</code> if none is selected.
	 */
	public Object getSelectedItem() {
		int row = table.getSelectedRow();
		if (row < 0) {
			return null;
		} else {
			row = table.getRowSorter().convertRowIndexToModel(row);
			return tableModel.getItem(row);
		}
	}

	/**
	 * Reset the columns.
	 * 
	 */
	public void resetColumns() {
		// New column set
		actualColumns = computeColumns();
		table.resetColumns(actualColumns);
		// Current items & selection
		List items = tableModel.getItems();
		int selectedRow = table.getSelectedRow();
		// Creates a model for the table
		tableModel = new GUITableModel(actualColumns);
		table.setModel(tableModel);
		// Reset items & selection
		tableModel.setItems(items);
		table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
	}

	public void bindEditEvent(final EventAction eventAction) {
		tableModel.addTableModelListener(new TableModelListener() {
		
			public void tableChanged(TableModelEvent e) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
