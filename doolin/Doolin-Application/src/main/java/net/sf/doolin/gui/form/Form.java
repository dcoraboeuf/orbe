/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;

public interface Form extends FieldContainer {

	void setView(View view);

	void setFormData(Object formData);

	void init();

	JComponent getComponent();

	View getView();

	Object getFormData();

	void validate(ValidationReport validationReport);

	void register(Field field);

	Iterable<Field> getFields();

	/**
	 * Reports errors in this form.
	 * 
	 * @param report
	 *            Report to display
	 */
	void display(ValidationReport report);

	/**
	 * Enables or disables the form.
	 * 
	 * @param enabled
	 *            Enabled flag.
	 */
	void setEnabled(boolean enabled);

	/**
	 * This method is called before the form is actually displayed.
	 * 
	 * @see View#onBeforeDisplay()
	 */
	void onBeforeDisplay();

}
