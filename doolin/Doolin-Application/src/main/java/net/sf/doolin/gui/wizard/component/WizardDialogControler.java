/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.component;

import net.sf.doolin.gui.wizard.WizardView;

/**
 * Defines the communication protocol between the wizard dialog and the wizard
 * view.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardDialogControler.java,v 1.1 2007/08/07 16:47:09
 *          guinnessman Exp $
 */
public interface WizardDialogControler {

	/**
	 * This method is called when the user has clicked on the "Cancel" button or
	 * has closed the wizard dialog.
	 * 
	 */
	void onCancel();

	/**
	 * This method is called when the user has clicked on the "Next" button.
	 * 
	 */
	void onNext();

	/**
	 * This method is called when the user has clicked on the "Previous" button.
	 * 
	 */
	void onPrevious();

	/**
	 * This method is called when the user has clicked on the "Finish" button.
	 * 
	 */
	void onFinish();

	/**
	 * Gets the running wizard view
	 * 
	 * @return Wizard view
	 */
	WizardView getWizardView();

}
