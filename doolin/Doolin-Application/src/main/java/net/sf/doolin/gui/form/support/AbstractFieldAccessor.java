/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.form.support;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.service.PropertyService;

/**
 * Utility ancestor for field accessors.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractFieldAccessor.java,v 1.5 2007/08/10 16:54:48
 *          guinnessman Exp $
 */
public abstract class AbstractFieldAccessor implements FieldAccessor {

	public void getFormData(Object formData, Field field) {
		String property = field.getProperty();
		PropertyService propertyService = GUIUtils.getService(PropertyService.class);
		Object fieldData = field.getFieldData(formData);
		// Controls the case where the property is 'this'
		if (fieldData != formData) {
			propertyService.setProperty(formData, property, fieldData);
		}
	}

	public void setFormData(Field field, Object formData) {
		PropertyService propertyService = GUIUtils.getService(PropertyService.class);
		Object fieldData = propertyService.getProperty(formData, field.getProperty());
		field.setFieldData(formData, fieldData);
	}

}
