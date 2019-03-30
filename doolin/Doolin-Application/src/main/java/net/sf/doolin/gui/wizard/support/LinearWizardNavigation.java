/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import java.util.List;

import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * This navigation policy just navigates from one step to another, following the
 * order which they have declared in.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LinearWizardNavigation.java,v 1.1 2007/08/07 16:47:07 guinnessman Exp $
 */
public class LinearWizardNavigation extends AbstractWizardNavigation {

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.support.AbstractWizardNavigation#init()
	 */
	@Override
	protected void init() {
	}

	public WizardStep next(WizardStep current) {
		List<WizardStep> steps = getSteps();
		// Get the index of the current step
		int index = getIndex(current);
		// Get the next index
		index++;
		// Get the next step
		if (index < steps.size()) {
			return steps.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Looks for the index of a step
	 * 
	 * @param current
	 *            Step to look index for
	 * @return Index of the step or -1 if <code>current</code> is
	 *         <code>null</code>.
	 */
	protected int getIndex(WizardStep current) {
		List<WizardStep> steps = getSteps();
		// Get the index of the current step
		int index;
		if (current != null) {
			index = steps.indexOf(current);
			if (index < 0) {
				throw new IllegalStateException("Cannot find current step in navigation list.");
			} else {
				return index;
			}
		} else {
			// Looks for the first step
			return -1;
		}
	}

	public WizardStep previous(WizardStep current) {
		List<WizardStep> steps = getSteps();
		// Get the index of the current step
		int index = getIndex(current);
		// Get the previous index
		index--;
		// Get the next step
		if (index >= 0) {
			return steps.get(index);
		} else {
			return null;
		}
	}

	/**
	 * @see net.sf.doolin.gui.wizard.model.WizardNavigation#getNavigationState(net.sf.doolin.gui.wizard.model.WizardStep)
	 */
	public WizardNavigationState getNavigationState(WizardStep step) {
		WizardNavigationState state = new WizardNavigationState();

		List<WizardStep> steps = getSteps();
		int count = steps.size();
		int index = steps.indexOf(step);
		if (index < 0) {
			throw new IllegalStateException("Cannot find current step in navigation list.");
		}

		boolean allOtherAreOptional = true;
		for (int i = index + 1; i < count && allOtherAreOptional; i++) {
			WizardStep ws = steps.get(i);
			allOtherAreOptional = allOtherAreOptional && ws.isOptional();
		}

		state.setPreviousPossible(index > 0);
		state.setNextPossible(index < count - 1);
		state.setFinishPossible(allOtherAreOptional);

		return state;
	}

}
