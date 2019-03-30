/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import net.sf.doolin.gui.core.view.AbstractView;
import net.sf.doolin.gui.wizard.WizardView;
import net.sf.doolin.gui.wizard.model.Wizard;

/**
 * Root ancestor for wizard implementations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractWizardView.java,v 1.2 2007/08/15 09:05:24 guinnessman Exp $
 */
public abstract class AbstractWizardView extends AbstractView implements WizardView {

	private Wizard wizard;

	public Wizard getWizard() {
		return wizard;
	}

	/**
	 * Sets the associated wizard definition
	 * 
	 * @param wizard
	 *            Wizard definition
	 */
	public void setWizard(Wizard wizard) {
		this.wizard = wizard;
	}

}
