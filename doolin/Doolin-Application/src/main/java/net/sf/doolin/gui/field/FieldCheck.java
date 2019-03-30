/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.field.support.CheckSupport;

/**
 * Checkbox field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldCheck.java,v 1.1 2007/08/10 16:54:42 guinnessman Exp $
 */
public class FieldCheck extends AbstractSupportField<CheckSupport> {

	@Override
	protected CheckSupport createSupport() {
		return createSupport(CheckSupport.class);
	}

	public Object getFieldData(Object formData) {
		return getSupport().isSelected();
	}

	public void setFieldData(Object formData, Object fieldData) {
		boolean selected = (Boolean) fieldData;
		getSupport().setSelected(selected);
	}

}
