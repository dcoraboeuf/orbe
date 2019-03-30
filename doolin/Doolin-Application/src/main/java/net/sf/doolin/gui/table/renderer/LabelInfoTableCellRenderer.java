/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.table.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.doolin.gui.field.helper.DefaultLabelInfoProvider;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.field.helper.LabelInfo;

public class LabelInfoTableCellRenderer extends DefaultTableCellRenderer {

	private LabelInfoProvider labelProvider = new DefaultLabelInfoProvider();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// Get label info
		LabelInfo labelIcon = labelProvider.getLabelIcon(value);
		// Text
		String text = labelIcon.getText();
		// Ok
		super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
		// Icon
		setIcon(labelIcon.getIcon());
		// Ok
		return this;
	}

	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

}
