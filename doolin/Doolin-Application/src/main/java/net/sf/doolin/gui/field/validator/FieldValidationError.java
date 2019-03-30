/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.validator;

import net.sf.doolin.gui.core.validation.AbstractValidationError;
import net.sf.doolin.gui.field.Field;

public class FieldValidationError extends AbstractValidationError {

	private Field field;

	private String detailedError;

	private String shortError;

	public FieldValidationError(Field field, String detailedError, String shortError) {
		this.detailedError = detailedError;
		this.shortError = shortError;
		this.field = field;
	}
	
	public FieldValidationError(Field field) {
		this.field = field;
	}

	public String getDetailedMessage() {
		return detailedError;
	}

	public Field getField() {
		return field;
	}
	
	@Override
	public String getShortMessage() {
		return shortError;
	}

	/**
	 * @param detailedError The detailedError to set.
	 */
	public void setDetailedError(String detailedError) {
		this.detailedError = detailedError;
	}

	/**
	 * @param field The field to set.
	 */
	protected void setField(Field field) {
		this.field = field;
	}

	/**
	 * @param shortError The shortError to set.
	 */
	protected void setShortError(String shortError) {
		this.shortError = shortError;
	}

}
