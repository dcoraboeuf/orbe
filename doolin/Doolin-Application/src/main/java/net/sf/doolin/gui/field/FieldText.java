/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.field.support.TextSupport;

public class FieldText extends AbstractSupportField<TextSupport> {

	private int columns = 10;

	private int maxlength = -1;

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	public void setFieldData(Object formData, Object fieldData) {
		String text = (String) fieldData;
		getSupport().setText(text);
	}
	
	@Override
	protected TextSupport createSupport() {
		return createSupport(TextSupport.class);
	}

	public Object getFieldData(Object formData) {
		return getSupport().getText();
	}
}
