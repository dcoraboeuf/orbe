/*
 * Created on 18 août 07
 */
package net.sf.doolin.gui.field.validator;

import java.util.Arrays;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.FieldPassword;
import net.sf.doolin.gui.service.GUIStrings;

/**
 * Validates that the value entered in the confirmation field is the same than
 * in the main field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PasswordConfirmValidator.java,v 1.1 2007/08/18 17:19:48 guinnessman Exp $
 */
public class PasswordConfirmValidator extends AbstractValidator {

	private String fieldName;

	@Override
	protected String getDetailedError(Field field) {
		return GUIStrings.get("net.sf.doolin.gui.field.validator.PasswordConfirmValidator.detailed", field.getDisplayName());
	}

	@Override
	protected String getShortError(Field field) {
		return GUIStrings.get("net.sf.doolin.gui.field.validator.PasswordConfirmValidator.short");
	}

	public void validate(Field field, ValidationReport validationReport) {
		FieldPassword fieldConfirmPassword = (FieldPassword) field;
		// Confirmation password
		char[] confirmPassword = fieldConfirmPassword.getSupport().getPassword();
		// Main password field
		FieldPassword fieldPassword = (FieldPassword) field.getForm().getFieldByName(fieldName);
		// Main password
		char[] password = fieldPassword.getSupport().getPassword();
		// Comparizon
		boolean ok = Arrays.equals(confirmPassword, password);
		// Result
		if (!ok) {
			reportFieldValidationError(validationReport, field);
		}
	}

	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the name of the field that contains the first entry for the
	 * password.
	 * 
	 * @param fieldName
	 *            Field name
	 * @see Field#getName()
	 */
	@Configurable(mandatory = true, description = "Name of the field that contains the first entry for the password")
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
