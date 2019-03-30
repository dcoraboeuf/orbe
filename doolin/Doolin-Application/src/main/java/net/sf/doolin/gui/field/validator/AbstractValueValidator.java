/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.validator;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;

public abstract class AbstractValueValidator extends AbstractValidator {

	public void validate(Field field, ValidationReport validationReport) {
		// Get the field data
		Object fieldData = field.getFieldData(field.getForm().getFormData());
		// Checks the value
		validateValue (field, fieldData, validationReport);
	}

	protected abstract void validateValue(Field field, Object fieldData, ValidationReport validationReport);

}
