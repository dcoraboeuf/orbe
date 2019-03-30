/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.wizard.WizardView;
import net.sf.doolin.gui.wizard.model.WizardNavigation;
import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Default wizard definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultWizard.java,v 1.3 2007/08/15 09:05:24 guinnessman Exp $
 */
public class DefaultWizard extends AbstractWizard {

	private Object viewData;

	private WizardView wizardView;

	private WizardNavigationState savedNavigationState;

	/**
	 * Navigation
	 */
	private WizardNavigation navigation = new LinearWizardNavigation();

	/**
	 * Initializes this wizard.
	 * 
	 * @param view
	 *            Hosting wizard view
	 * @param data
	 *            Root view data
	 */
	@Override
	public void init(WizardView view, Object data) {
		super.init(view, data);
		wizardView = view;
		viewData = data;
		// Initializes the navigation
		navigation.init(data);
		// Goes to the next step
		nextStep();
	}

	/**
	 * Goes to next step.
	 * 
	 * @see WizardNavigation#next(WizardStep)
	 * @see net.sf.doolin.gui.wizard.model.Wizard#nextStep()
	 */
	public void nextStep() {
		boolean ok = true;
		// Get the current step and validates it
		WizardStep current = getCurrentStep();
		if (current != null) {
			// Validates the step
			ok = validate();
		}
		// Actually goes to the next step
		if (ok) {
			WizardStep step = navigation.next(current);
			if (step != null) {
				step.setWizardView(wizardView);
				step.init();
				step.setViewData(viewData);
				setCurrentStep(step);
				step.start();
			} else {
				throw new IllegalStateException("No further step.");
			}
		}
	}

	/**
	 * Goes to previous step.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.Wizard#previousStep()
	 */
	public void previousStep() {
		WizardStep current = getCurrentStep();
		WizardStep step = navigation.previous(current);
		if (step != null) {
			// The step has already been initialized. It needs just to be
			// displayed.
			// step.setWizardView(wizardView);
			// step.setViewData(viewData);
			setCurrentStep(step);
		} else {
			throw new IllegalStateException("No previous step.");
		}
	}

	/**
	 * @see net.sf.doolin.gui.wizard.model.Wizard#finish()
	 */
	public void finish() {
		boolean ok = true;
		// Get the current step and validates it
		WizardStep current = getCurrentStep();
		if (current != null) {
			// Validates the step
			ok = validate();
		}
		if (ok) {
			// Notifies the end
			fireFinish();
		}
	}

	/**
	 * Validates the current step.
	 * 
	 * @return Result of the validation
	 */
	protected boolean validate() {
		ValidationReport report = new ValidationReport();
		wizardView.validate(report);
		wizardView.display(report);
		return report.isOk();
	}

	/**
	 * @return Returns the navigation.
	 */
	public WizardNavigation getNavigation() {
		return navigation;
	}

	/**
	 * Sets the wizard navigation policy.
	 * 
	 * @param navigation
	 *            The navigation to set.
	 */
	public void setNavigation(WizardNavigation navigation) {
		this.navigation = navigation;
	}

	/**
	 * Delegates to the navigation policy.
	 * 
	 * @see WizardNavigation#getNavigationState(WizardStep)
	 * @see net.sf.doolin.gui.wizard.model.Wizard#getNavigationState(net.sf.doolin.gui.wizard.model.WizardStep)
	 */
	public WizardNavigationState getNavigationState(WizardStep step) {
		return navigation.getNavigationState(step);
	}

	public void suspend() {
		if (savedNavigationState != null) {
			throw new IllegalStateException("The wizard is already suspended.");
		} else {
			savedNavigationState = getNavigationState(getCurrentStep());
			fireSuspend();
		}
	}

	public void resume() {
		if (savedNavigationState != null) {
			fireResume(savedNavigationState);
			savedNavigationState = null;
		}
	}

	/**
	 * Delegates to the current step.
	 * 
	 * @see WizardStep#validate(ValidationReport)
	 * @see net.sf.doolin.gui.wizard.model.Wizard#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void validate(ValidationReport validationReport) {
		WizardStep current = getCurrentStep();
		if (current != null) {
			current.validate(validationReport);
		}
	}

	/**
	 * Delegates to the current step.
	 * 
	 * @see WizardStep#display(ValidationReport)
	 * @see net.sf.doolin.gui.wizard.model.Wizard#display(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void display(ValidationReport report) {
		WizardStep current = getCurrentStep();
		if (current != null) {
			current.display(report);
		}
	}

}
