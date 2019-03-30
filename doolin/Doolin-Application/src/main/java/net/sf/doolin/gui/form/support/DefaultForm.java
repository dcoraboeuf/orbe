/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form.support;

import javax.swing.JComponent;

import net.sf.doolin.gui.form.layout.Layout;

public class DefaultForm extends AbstractForm {

	private Layout layout;

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public JComponent getComponent() {
		return layout.getComponent();
	}

	public void init() {
		layout.setForm(this);
		layout.init();
	}

}
