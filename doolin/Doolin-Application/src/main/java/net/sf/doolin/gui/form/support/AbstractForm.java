/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.validator.FieldValidationError;
import net.sf.doolin.gui.form.FieldContainer;
import net.sf.doolin.gui.form.Form;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility ancestor for form implementations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractForm.java,v 1.6 2007/08/18 17:19:48 guinnessman Exp $
 */
public abstract class AbstractForm implements Form {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractForm.class);

	private List<Field> fieldList = new ArrayList<Field>();

	private FieldAccessor fieldAccessor = new DefaultFieldAccessor();

	public void register(Field field) {
		// Initializes the field
		field.init(this);
		// Adds the field to the list
		fieldList.add(field);
	}

	private View view;

	private Object formData;

	public Object getFormData() {
		return formData;
	}

	public void setFormData(Object formData) {
		this.formData = formData;
		// Set the value of all fields
		for (Field field : fieldList) {
			fieldAccessor.setFormData(field, formData);
		}
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void validate(ValidationReport validationReport) {
		// Validates each field
		for (Field field : fieldList) {
			if (!field.isReadOnly()) {
				field.validate(validationReport);
			}
		}

		// Does not save data is the form is not valid
		if (!validationReport.isOk()) {
			return;
		}

		// Now that the validation is complete, fills the model
		for (Field field : fieldList) {
			if (!field.isReadOnly()) {
				fieldAccessor.getFormData(formData, field);
			}
		}
	}

	public void display(ValidationReport report) {
		// Clears all errors
		for (Field field : fieldList) {
			field.setValidationError(null);
		}
		// For each errors
		for (ValidationError error : report.getValidationErrors()) {
			// Field error ?
			if (error instanceof FieldValidationError) {
				FieldValidationError fieldValidationError = (FieldValidationError) error;
				Field field = fieldValidationError.getField();
				field.setValidationError(fieldValidationError);
			}
			// Other kind of error
			else {
				log.warn("Unmanageable validation error: " + error.getDetailedMessage());
			}
		}
	}

	/**
	 * Gets the accessor which is used to access field properties
	 * 
	 * @return Field accessor
	 */
	public FieldAccessor getFieldAccessor() {
		return fieldAccessor;
	}

	/**
	 * Sets the accessor which is used to access field properties
	 * 
	 * @param fieldAccessor
	 *            Field accessor
	 */
	public void setFieldAccessor(FieldAccessor fieldAccessor) {
		this.fieldAccessor = fieldAccessor;
	}

	public Iterable<Field> getFields() {
		return fieldList;
	}

	/**
	 * Delegates to each field.
	 * 
	 * @see Field#setEnabled(boolean)
	 * @see net.sf.doolin.gui.form.Form#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		for (Field field : fieldList) {
			field.setEnabled(enabled);
		}
	}

	/**
	 * @see net.sf.doolin.gui.form.Form#getFieldByName(java.lang.String)
	 */
	public Field getFieldByName(String fieldName) {
		for (Field field : fieldList) {
			if (StringUtils.equals(fieldName, field.getName())) {
				return field;
			} else if (field instanceof FieldContainer) {
				Field subField = ((FieldContainer) field).getFieldByName(fieldName);
				if (subField != null) {
					return subField;
				}
			}
		}
		throw new IllegalArgumentException("Cannot find field with name " + fieldName);
	}

	/**
	 * Delegates to the fields.
	 * 
	 * @see Field#onBeforeDisplay()
	 * @see net.sf.doolin.gui.form.Form#onBeforeDisplay()
	 */
	public void onBeforeDisplay() {
		for (Field field : fieldList) {
			field.onBeforeDisplay();
		}
	}

}
