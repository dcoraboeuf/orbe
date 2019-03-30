/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.form.Form;

/**
 * Field definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Field.java,v 1.4 2007/08/18 17:19:47 guinnessman Exp $
 */
public interface Field {

	String getProperty();

	void init(Form form);

	Form getForm();

	JComponent getComponent();

	void setFieldData(Object formData, Object fieldData);

	void validate(ValidationReport validationReport);

	boolean isReadOnly();

	Object getFieldData(Object formData);

	String getDisplayName();

	void setValidationError(ValidationError validationError);

	JComponent getFocusableComponent();

	/**
	 * Layout constraint associated with this field. Its type and usage depends
	 * on the form layout.
	 * 
	 * @return Constraint.
	 */
	Object getConstraint();

	/**
	 * Enables or disables the field.
	 * 
	 * @param enabled
	 *            Enabled state
	 */
	void setEnabled(boolean enabled);

	/**
	 * Gets the identifier of this field
	 * 
	 * @return Field name
	 */
	String getName();

	/**
	 * This method is called before the field is actually displayed.
	 * 
	 * @see Form#onBeforeDisplay()
	 * @see View#onBeforeDisplay()
	 */
	void onBeforeDisplay();

}
