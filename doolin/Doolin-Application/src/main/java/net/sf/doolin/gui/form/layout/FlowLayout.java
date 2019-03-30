/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form.layout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.field.Field;

public class FlowLayout extends AbstractLayout {

	private List<Field> fieldList = new ArrayList<Field>();

	private JPanel panel;

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public JComponent getComponent() {
		return panel;
	}

	public void init() {
		panel = new JPanel();
		panel.setLayout(new java.awt.FlowLayout());
		// For each field
		for (Field field : fieldList) {
			// Init the field
			getForm().register(field);
			// Get the field component and adds it to the layout
			panel.add(field.getComponent());
		}
	}

}
