/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.model;

/**
 * Defines a listener for a wizard life-cycle.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardListener.java,v 1.2 2007/08/10 16:54:43 guinnessman Exp $
 */
public interface WizardListener {

	/**
	 * Notification that the step has changed on the wizard.
	 * 
	 * @param wizard
	 *            Wizard which has been updated
	 * @param step
	 *            New step
	 */
	void wizardNewStep(Wizard wizard, WizardStep step);

	/**
	 * Notification that the wizard is finished.
	 * 
	 * @param wizard
	 *            Wizard which is finished.
	 */
	void wizardFinished(Wizard wizard);

	/**
	 * Notification that the wizard navigation is suspended.
	 * 
	 * @param wizard
	 *            Wizard which is suspended.
	 */
	void wizardSuspended(Wizard wizard);

	/**
	 * Fires that the wizard navigation is resumed.
	 * 
	 * @param wizard
	 *            Wizard which is resumed.
	 * @param restoredNavigationState
	 *            Navigation state to be restored
	 */
	void wizardResumed(Wizard wizard, WizardNavigationState restoredNavigationState);

}
