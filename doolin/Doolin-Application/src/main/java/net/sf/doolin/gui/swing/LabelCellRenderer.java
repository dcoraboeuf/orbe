/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.swing;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.field.helper.LabelInfo;

public class LabelCellRenderer extends DefaultListCellRenderer {

	private LabelInfoProvider labelProvider;

	public LabelCellRenderer(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Get the label information
		LabelInfo labelIcon = labelProvider.getLabelIcon(value);
		// Renders
		super.getListCellRendererComponent(list, labelIcon.getText(), index, isSelected, cellHasFocus);
		// Icon
		setIcon(labelIcon.getIcon());
		// Ok
		return this;
	}

}
