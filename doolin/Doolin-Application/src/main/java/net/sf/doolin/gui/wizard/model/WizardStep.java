/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.model;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.wizard.WizardView;

/**
 * Definition for a wizard step.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardStep.java,v 1.2 2007/08/10 16:54:43 guinnessman Exp $
 */
public interface WizardStep {

	/**
	 * Returns the step name
	 * 
	 * @return Step name
	 */
	String getName();

	/**
	 * This method is executed when the wizard is closed
	 * 
	 * @return <code>true</code> if the wizard can actually be closed
	 */
	boolean onClose();

	/**
	 * Gets the component for this step.
	 * 
	 * @return Displayable component.
	 */
	JComponent getComponent();

	/**
	 * Sets the view data
	 * 
	 * @param viewData
	 *            View data
	 */
	void setViewData(Object viewData);

	/**
	 * Sets the wizard view.
	 * 
	 * @param wizardView
	 *            Wizard view
	 */
	void setWizardView(WizardView wizardView);

	/**
	 * Is this step optional in the wizard process?
	 * 
	 * @return <code>true</code> if the step can be omitted.
	 */
	boolean isOptional();

	/**
	 * Initializes the step before it is displayed.
	 */
	void init();

	/**
	 * Starts any task after the step has been displayed and setup in the
	 * wizard.
	 * 
	 */
	void start();

	/**
	 * Validates this step.
	 * 
	 * @param validationReport
	 *            Report to fill
	 */
	void validate(ValidationReport validationReport);

	/**
	 * Displays a validation report
	 * 
	 * @param report
	 *            Report to display
	 */
	void display(ValidationReport report);

}
