/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.form;

import javax.swing.JComponent;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.validation.ValidationReporter;
import net.sf.doolin.gui.core.validation.ValidationReporterDelegator;
import net.sf.doolin.gui.core.view.AbstractView;

/**
 * This view contains a {@link Form}.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class FormView extends AbstractView implements ValidationReporterDelegator {

	private Form form;

	public void init() {
		super.init();
		form.setView(this);
		form.init();
	}

	public ViewContainer createViewContainer() {
		// TODO Code of FormView.createViewContainer method
		throw new RuntimeException("NYI");
	}

	public JComponent getComponent() {
		return form.getComponent();
	}

	public void validate(ValidationReport validationReport) {
		form.validate(validationReport);
	}

	/**
	 * @see #setForm(Form)
	 * @return Associated form
	 */
	public Form getForm() {
		return form;
	}

	/**
	 * Sets the form displayed by this view
	 * 
	 * @param form
	 *            Form
	 */
	@Configurable(mandatory = true, description = "View form")
	public void setForm(Form form) {
		this.form = form;
	}

	@Override
	public void setViewData(Object viewData) {
		super.setViewData(viewData);
		form.setFormData(viewData);
	}

	/**
	 * Delegates to the form.
	 * 
	 * @see Form#display(ValidationReport)
	 * @see net.sf.doolin.gui.core.validation.ValidationReporterDelegator#getValidationReporterDelegate()
	 */
	public ValidationReporter getValidationReporterDelegate() {
		return new ValidationReporter() {

			public void report(View view, ValidationReport report) {
				form.display(report);
			}

		};
	}

	/**
	 * Delegates to the form.
	 * 
	 * @see Form#setEnabled(boolean)
	 * @see net.sf.doolin.gui.core.View#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		form.setEnabled(enabled);
	}

	/**
	 * @see Form#onBeforeDisplay()
	 * @see net.sf.doolin.gui.core.view.AbstractView#onBeforeDisplay()
	 */
	@Override
	public void onBeforeDisplay() {
		// Delegates to form
		form.onBeforeDisplay();
		// Default behaviour
		super.onBeforeDisplay();
	}
}
