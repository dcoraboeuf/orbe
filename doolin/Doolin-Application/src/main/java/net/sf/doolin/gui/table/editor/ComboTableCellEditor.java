/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.table.editor;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import net.sf.doolin.gui.field.helper.DefaultLabelInfoProvider;
import net.sf.doolin.gui.field.helper.ItemProvider;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.swing.LabelCellRenderer;

public class ComboTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private ItemProvider itemProvider;

	private LabelInfoProvider labelProvider = new DefaultLabelInfoProvider();

	private JComboBox cbo;

	public Object getCellEditorValue() {
		return cbo.getSelectedItem();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (cbo == null) {
			init();
		}
		cbo.setSelectedItem(value);
		return cbo;
	}

	protected void init() {
		cbo = new JComboBox();
		// TODO Editable
		// List of items
		List items = itemProvider.getItems();
		for (Object item : items) {
			cbo.addItem(item);
		}
		// TODO Custom renderer
		// Label renderer
		cbo.setRenderer(new LabelCellRenderer(labelProvider));
	}

	public ItemProvider getItemProvider() {
		return itemProvider;
	}

	public void setItemProvider(ItemProvider itemProvider) {
		this.itemProvider = itemProvider;
	}

	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

}
