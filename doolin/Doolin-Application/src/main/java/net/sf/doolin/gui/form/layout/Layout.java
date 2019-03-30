/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form.layout;

import javax.swing.JComponent;

import net.sf.doolin.gui.form.Form;

public interface Layout {

	JComponent getComponent();

	void setForm(Form form);
	
	void init();

}
