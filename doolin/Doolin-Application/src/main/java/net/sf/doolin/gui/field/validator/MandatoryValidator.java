/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.validator;

import java.lang.reflect.Array;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.service.GUIStrings;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class MandatoryValidator extends AbstractValueValidator {

	private boolean acceptBlank = false;

	@Override
	protected void validateValue(Field field, Object fieldData, ValidationReport validationReport) {
		// Null
		if (fieldData == null) {
			reportFieldValidationError(validationReport, field);
		}
		// Array
		else if (fieldData.getClass().isArray()) {
			int length = Array.getLength(fieldData);
			if (length == 0) {
				reportFieldValidationError(validationReport, field);
			}
		}
		// As string
		else {
			String fieldValue = ObjectUtils.toString(fieldData, null);
			boolean ok;
			if (acceptBlank) {
				ok = StringUtils.isNotEmpty(fieldValue);
			} else {
				ok = StringUtils.isNotBlank(fieldValue);
			}
			if (!ok) {
				reportFieldValidationError(validationReport, field);
			}
		}
	}

	public boolean isAcceptBlank() {
		return acceptBlank;
	}

	public void setAcceptBlank(boolean acceptEmpty) {
		this.acceptBlank = acceptEmpty;
	}

	@Override
	protected String getDetailedError(Field field) {
		return GUIStrings.get("net.sf.doolin.gui.field.validator.MandatoryValidator.detailed", field.getDisplayName());
	}

	@Override
	protected String getShortError(Field field) {
		return GUIStrings.get("net.sf.doolin.gui.field.validator.MandatoryValidator.short");
	}

}
