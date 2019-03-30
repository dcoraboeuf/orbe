/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.form.support;

import net.sf.doolin.gui.field.Field;

/**
 * This interface defines a service that accesses field values.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldAccessor.java,v 1.3 2007/08/07 16:47:02 guinnessman Exp $
 */
public interface FieldAccessor {

	/**
	 * Sets the value for a field.
	 * 
	 * @param field
	 *            Field to set
	 * @param formData
	 *            Form data
	 */
	void setFormData(Field field, Object formData);

	/**
	 * Gets the value from a field
	 * 
	 * @param formData
	 *            Form data to update
	 * @param field
	 *            Field to get
	 */
	void getFormData(Object formData, Field field);

}
