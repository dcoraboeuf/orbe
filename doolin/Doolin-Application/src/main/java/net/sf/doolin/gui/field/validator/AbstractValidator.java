/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.validator;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.Validator;

public abstract class AbstractValidator implements Validator {
	
	protected void reportFieldValidationError (ValidationReport validationReport, Field field) {
		validationReport.add(new FieldValidationError(field, getDetailedError(field), getShortError(field)));
	}

	protected abstract String getDetailedError(Field field);

	protected abstract String getShortError(Field field);
	
}
