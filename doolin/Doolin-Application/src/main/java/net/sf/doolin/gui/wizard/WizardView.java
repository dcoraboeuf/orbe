/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.wizard.model.Wizard;

/**
 * Defines a wizard.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardView.java,v 1.1 2007/08/07 16:47:11 guinnessman Exp $
 */
public interface WizardView extends View {

	/**
	 * Gets the associated wizard
	 * 
	 * @return Wizard
	 */
	Wizard getWizard();

	/**
	 * Starts the wizard.
	 * 
	 */
	void startWizard();

}
