/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.table.support;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.sf.doolin.gui.swing.SwingUtils;
import net.sf.doolin.gui.table.Column;

import net.sf.doolin.util.unit.ValueUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GUITable extends JTable {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(GUITable.class);

	private List<Column> columns;

	private boolean layoutInitialization;

	public GUITable(List<Column> columns) {
		this.columns = columns;
		this.layoutInitialization = true;
	}

	@Override
	public void doLayout() {
		if (layoutInitialization) {
			log.debug("Initialization of table layout.");
			// Column model
			TableColumnModel columnModel = getColumnModel();
			// For each column
			int colCount = getColumnCount();
			for (int colIndex = 0; colIndex < colCount; colIndex++) {
				// Column
				TableColumn tableColumn = columnModel.getColumn(colIndex);
				// Index in the model
				int modelIndex = tableColumn.getModelIndex();
				// Column definition
				Column column = columns.get(modelIndex);
				setupColumn(colIndex, tableColumn, column, true);
			}
			// Ok
			layoutInitialization = false;
		}
		super.doLayout();
	}

	protected void setupColumn(int colIndex, TableColumn tableColumn, Column column, boolean setupWidth) {
		log.debug("Setup the column no" + colIndex);
		// Renderer
		setupRenderer(colIndex, tableColumn, column);
		// TODO Sorting
		// setupSorting(colIndex, tableColumn, column);
		// Editor
		setupEditor(colIndex, tableColumn, column);
		// Size
		ValueUnit width = column.getWidth();
		if (width != null && setupWidth) {
			SwingUtils.setTableColumnWidth(this, colIndex, width, column.isResizeable());
		}
	}

	/**
	 * Setup the editor for one column
	 * 
	 * @param colIndex
	 *            Column index
	 * @param tableColumn
	 *            Column in the JTable column model
	 * @param column
	 *            Column definition
	 */
	protected void setupEditor(int colIndex, TableColumn tableColumn, Column column) {
		TableCellEditor cellEditor = column.getEditor();
		if (cellEditor != null) {
			tableColumn.setCellEditor(cellEditor);
		}
	}

	/**
	 * Setup the renderer for one column
	 * 
	 * @param colIndex
	 *            Column index
	 * @param tableColumn
	 *            Column in the JTable column model
	 * @param column
	 *            Column definition
	 */
	protected void setupRenderer(int colIndex, TableColumn tableColumn, Column column) {
		TableCellRenderer cellRenderer = column.getRenderer();
		if (cellRenderer != null) {
			tableColumn.setCellRenderer(cellRenderer);
		}
	}

	/**
	 * Resets the list of columns.
	 * 
	 * @param columns New set of columns
	 */
	public void resetColumns(List<Column> columns) {
		this.columns = columns;
		layoutInitialization = true;
	}

}
