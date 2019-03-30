/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.validation.ValidationReporter;
import net.sf.doolin.gui.core.validation.ValidationReporterDelegator;
import net.sf.doolin.gui.wizard.model.Wizard;
import net.sf.doolin.gui.wizard.model.WizardListener;
import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Default implementation for a wizard view.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultWizardView.java,v 1.1 2007/08/07 16:47:07 guinnessman
 *          Exp $
 */
public class DefaultWizardView extends AbstractWizardView implements WizardListener, ValidationReporterDelegator {

	private JPanel container;

	public ViewContainer createViewContainer() {
		throw new RuntimeException("NYI");
	}

	public JComponent getComponent() {
		return container;
	}

	public void init() {
		super.init();
		container = new JPanel(new BorderLayout());
	}

	/**
	 * Delegates the validation to the wizard.
	 * 
	 * @see Wizard#validate(ValidationReport)
	 * @see net.sf.doolin.gui.core.View#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void validate(ValidationReport validationReport) {
		getWizard().validate(validationReport);
	}

	/**
	 * Starts the wizard.
	 * 
	 * @see net.sf.doolin.gui.wizard.WizardView#startWizard()
	 */
	public void startWizard() {
		// Sets this view as a listener for the wizard
		getWizard().addWizardListener(this);
		// Starts the wizard
		getWizard().init(this, getViewData());
	}

	/**
	 * The wizard notifies that a new step is to be displayed.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardNewStep()
	 */
	public void wizardNewStep(Wizard w, WizardStep step) {
		// Clears all
		container.removeAll();
		// Displays the component
		container.add(step.getComponent(), BorderLayout.CENTER);
		// Validates
		container.validate();
		container.repaint();
	}

	/**
	 * @see Wizard#removeWizardListener(WizardListener)
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardFinished(net.sf.doolin.gui.wizard.model.Wizard)
	 */
	public void wizardFinished(Wizard wizard) {
		getWizard().removeWizardListener(this);
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardResumed(net.sf.doolin.gui.wizard.model.Wizard,
	 *      net.sf.doolin.gui.wizard.model.WizardNavigationState)
	 */
	public void wizardResumed(Wizard wizard, WizardNavigationState restoredNavigationState) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardSuspended(net.sf.doolin.gui.wizard.model.Wizard)
	 */
	public void wizardSuspended(Wizard wizard) {
	}

	/**
	 * Delegates to the wizard.
	 * 
	 * @see Wizard#display(ValidationReport)
	 */
	public ValidationReporter getValidationReporterDelegate() {
		return new ValidationReporter() {

			public void report(View view, ValidationReport report) {
				getWizard().display(report);
			}

		};
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.core.View#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
	}

}
