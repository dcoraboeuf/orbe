/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.form.layout;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.field.Field;

public class SingleLayout extends AbstractLayout {
	
	private Field field;
	
	private JPanel panel;

	public JComponent getComponent() {
		return panel;
	}

	public void init() {
		panel = new JPanel(new BorderLayout());
		getForm().register(field);
		panel.add(field.getComponent(), BorderLayout.CENTER);
	}

	/**
	 * @return Returns the field.
	 */
	public Field getField() {
		return field;
	}

	/**
	 * @param field The field to set.
	 */
	public void setField(Field field) {
		this.field = field;
	}

}
