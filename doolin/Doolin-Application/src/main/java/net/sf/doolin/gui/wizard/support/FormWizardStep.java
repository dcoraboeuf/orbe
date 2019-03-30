/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.form.Form;
import net.sf.doolin.gui.wizard.WizardView;

/**
 * Wizard step based on a form.
 * 
 * @see Form
 * @author Damien Coraboeuf
 * @version $Id: FormWizardStep.java,v 1.3 2007/08/15 09:05:24 guinnessman Exp $
 */
public class FormWizardStep extends AbstractWizardStep {

	private Form form;

	/**
	 * This step can always been closed.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#onClose()
	 */
	public boolean onClose() {
		return true;
	}

	/**
	 * 
	 * @return Form
	 */
	public Form getForm() {
		return form;
	}

	/**
	 * 
	 * @param form
	 *            Form
	 */
	public void setForm(Form form) {
		this.form = form;
	}

	/**
	 * @see Form#getComponent()
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#getComponent()
	 */
	public JComponent getComponent() {
		return form.getComponent();
	}

	/**
	 * @see Form#setFormData(Object)
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#setViewData(java.lang.Object)
	 */
	public void setViewData(Object viewData) {
		form.setFormData(viewData);
	}

	/**
	 * @see Form#setView(net.sf.doolin.gui.core.View)
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#setWizardView(net.sf.doolin.gui.wizard.WizardView)
	 */
	public void setWizardView(WizardView wizardView) {
		form.setView(wizardView);
	}

	/**
	 * @see Form#init()
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#init()
	 */
	public void init() {
		form.init();
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#start()
	 */
	public void start() {
	}

	/**
	 * Delegates to the form.
	 * 
	 * @see Form#validate(ValidationReport)
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void validate(ValidationReport validationReport) {
		form.validate(validationReport);
	}

	/**
	 * Delegates to the form.
	 * 
	 * @see Form#display(ValidationReport)
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#display(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void display(ValidationReport report) {
		form.display(report);
	}

}
