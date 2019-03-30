/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.model;

/**
 * This interfaces controls the navigation in a wizard.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardNavigation.java,v 1.1 2007/08/07 16:47:11 guinnessman Exp $
 */
public interface WizardNavigation {

	/**
	 * Initializes the navigation.
	 * 
	 * @param data
	 *            View data
	 */
	void init(Object data);

	/**
	 * Computes the next step.
	 * 
	 * @param current
	 *            Current step
	 * @return Next step or <code>null</code> if none is defined.
	 */
	WizardStep next(WizardStep current);

	/**
	 * Gets the navigation step associated with the given step.
	 * 
	 * @param step
	 *            Step to calculate the navigation state from
	 * @return Navigation state
	 */
	WizardNavigationState getNavigationState(WizardStep step);

	/**
	 * Computes the previous step.
	 * 
	 * @param current
	 *            Current step
	 * @return Previous step or <code>null</code> if none is defined.
	 */
	WizardStep previous(WizardStep current);

}
