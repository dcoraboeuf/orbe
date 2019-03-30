/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.helper;

/**
 * This default implementation assumes that field data and model data are the
 * same.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class DefaultFieldDataAdapter implements FieldDataAdapter<Object,Object> {

	/**
	 * Returns the field data unchanged.
	 * 
	 * @see net.sf.doolin.gui.field.helper.FieldDataAdapter#convertFieldToModel(java.lang.Object)
	 */
	public Object convertFieldToModel(Object fieldData) {
		return fieldData;
	}

	/**
	 * Returns the model data unchanged.
	 * 
	 * @see net.sf.doolin.gui.field.helper.FieldDataAdapter#convertModelToField(java.lang.Object)
	 */
	public Object convertModelToField(Object modelValue) {
		return modelValue;
	}

}
