/*
 * Created on Sep 19, 2007
 */
package net.sf.doolin.gui.field;

import java.awt.Color;

import net.sf.doolin.gui.field.support.ColorSupport;

/**
 * Color form field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class FieldColor extends AbstractSupportField<ColorSupport> {

	/**
	 * @see #createSupport(Class)
	 * @see ColorSupport
	 * @see net.sf.doolin.gui.field.AbstractSupportField#createSupport()
	 */
	@Override
	protected ColorSupport createSupport() {
		return createSupport(ColorSupport.class);
	}

	/**
	 * @see ColorSupport#getValue()
	 * @see net.sf.doolin.gui.field.Field#getFieldData(java.lang.Object)
	 */
	public Object getFieldData(Object formData) {
		return getSupport().getValue();
	}

	/**
	 * @see ColorSupport#setValue(java.awt.Color)
	 * @see net.sf.doolin.gui.field.Field#setFieldData(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setFieldData(Object formData, Object fieldData) {
		getSupport().setValue((Color) fieldData);
	}

}
