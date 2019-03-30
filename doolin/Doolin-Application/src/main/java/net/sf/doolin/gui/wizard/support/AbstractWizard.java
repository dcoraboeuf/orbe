/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import java.util.HashSet;
import java.util.Set;

import net.sf.doolin.gui.wizard.WizardView;
import net.sf.doolin.gui.wizard.model.Wizard;
import net.sf.doolin.gui.wizard.model.WizardListener;
import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Root ancestor for wizard definitions.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractWizard.java,v 1.2 2007/08/10 16:54:43 guinnessman Exp $
 */
public abstract class AbstractWizard implements Wizard {

	private WizardStep currentStep;

	private Set<WizardListener> wizardListeners = new HashSet<WizardListener>();

	/**
	 * @see net.sf.doolin.gui.wizard.model.Wizard#addWizardListener(net.sf.doolin.gui.wizard.model.WizardListener)
	 */
	public void addWizardListener(WizardListener listener) {
		wizardListeners.add(listener);
	}

	/**
	 * @see net.sf.doolin.gui.wizard.model.Wizard#removeWizardListener(net.sf.doolin.gui.wizard.model.WizardListener)
	 */
	public void removeWizardListener(WizardListener listener) {
		wizardListeners.remove(listener);
	}

	/**
	 * Fires that a new step is to be displayed.
	 * 
	 */
	protected void fireNewStep() {
		WizardListener[] lArray = wizardListeners.toArray(new WizardListener[0]);
		for (WizardListener l : lArray) {
			l.wizardNewStep(this, currentStep);
		}
	}

	/**
	 * Fires that the wizard is over.
	 * 
	 */
	protected void fireFinish() {
		WizardListener[] lArray = wizardListeners.toArray(new WizardListener[0]);
		for (WizardListener l : lArray) {
			l.wizardFinished(this);
		}
	}

	/**
	 * Fires that the wizard navigation is suspended.
	 * 
	 */
	protected void fireSuspend() {
		WizardListener[] lArray = wizardListeners.toArray(new WizardListener[0]);
		for (WizardListener l : lArray) {
			l.wizardSuspended(this);
		}
	}

	/**
	 * Fires that the wizard navigation is resumed.
	 * 
	 * @param restoredNavigationState
	 *            Navigation state to be restored
	 */
	protected void fireResume(WizardNavigationState restoredNavigationState) {
		WizardListener[] lArray = wizardListeners.toArray(new WizardListener[0]);
		for (WizardListener l : lArray) {
			l.wizardResumed(this, restoredNavigationState);
		}
	}

	/**
	 * Checks the current step and delegates the onClose() call.
	 * 
	 * @see WizardStep#onClose()
	 * @see net.sf.doolin.gui.wizard.model.Wizard#onClose()
	 */
	public boolean onClose() {
		return currentStep == null || currentStep.onClose();
	}

	/**
	 * @return Returns the currentStep.
	 */
	public WizardStep getCurrentStep() {
		return currentStep;
	}

	/**
	 * @param currentStep
	 *            The currentStep to set.
	 */
	protected void setCurrentStep(WizardStep currentStep) {
		this.currentStep = currentStep;
		fireNewStep();
	}

	/**
	 * Initializes this wizard.
	 * 
	 * @param view
	 *            Hosting wizard view
	 * @param data
	 *            Root view data
	 */
	public void init(WizardView view, Object data) {
		currentStep = null;
	}

}
