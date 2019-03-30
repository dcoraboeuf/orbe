/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.table.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.doolin.gui.swing.HAlignment;

public class FormatTableCellRenderer extends DefaultTableCellRenderer {
	
	private HAlignment align = HAlignment.LEFT;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// Alignment
		setHorizontalAlignment(align.getAlignment());
		// TODO Format the value
		// Ok
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	public HAlignment getAlign() {
		return align;
	}

	public void setAlign(HAlignment align) {
		this.align = align;
	}

}
