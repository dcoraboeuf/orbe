/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.model;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.wizard.WizardView;

/**
 * Wizard controler.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Wizard.java,v 1.2 2007/08/10 16:54:43 guinnessman Exp $
 */
public interface Wizard {

	/**
	 * This method is executed when the wizard is closed
	 * 
	 * @return <code>true</code> if the wizard can actually be closed
	 */
	boolean onClose();

	/**
	 * Initializes the wizard.
	 * 
	 * @param wizardView
	 *            Hosting wizard view
	 * @param viewData
	 *            Root wizard data.
	 */
	void init(WizardView wizardView, Object viewData);

	/**
	 * Adds a listener for this wizard.
	 * 
	 * @param listener
	 *            Wizard listener.
	 */
	void addWizardListener(WizardListener listener);

	/**
	 * Removes a listener for this wizard.
	 * 
	 * @param listener
	 *            Wizard listener.
	 */
	void removeWizardListener(WizardListener listener);

	/**
	 * Gets the current step.
	 * 
	 * @return Wizard current step.
	 */
	WizardStep getCurrentStep();

	/**
	 * Gets the navigation step associated with the given step.
	 * 
	 * @param step
	 *            Step to calculate the navigation state from
	 * @return Navigation state
	 */
	WizardNavigationState getNavigationState(WizardStep step);

	/**
	 * Goes to the next step.
	 * 
	 */
	void nextStep();

	/**
	 * Goes to the previous step.
	 */
	void previousStep();

	/**
	 * Finishes the wizard.
	 * 
	 */
	void finish();

	/**
	 * Suspends the navigation in the wizard.
	 * 
	 */
	void suspend();

	/**
	 * Resumes the navigation in the wizard.
	 * 
	 */
	void resume();

	/**
	 * Validates the wizard.
	 * 
	 * @param validationReport
	 *            Report to fill
	 */
	void validate(ValidationReport validationReport);

	/**
	 * Displays a validation report
	 * 
	 * @param report
	 *            Validation report to display
	 */
	void display(ValidationReport report);

}
