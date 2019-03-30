/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form.layout;

import net.sf.doolin.gui.form.Form;

public abstract class AbstractLayout implements Layout {
	
	private Form form;

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

}
