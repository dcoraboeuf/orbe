/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.wizard.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;
import net.sf.doolin.gui.wizard.support.AbstractWizardNavigation;

/**
 * Wizard navigation based on rules.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractRuleWizardNavigation.java,v 1.3 2007/08/15 09:05:29 guinnessman Exp $
 */
public abstract class AbstractRuleWizardNavigation extends AbstractWizardNavigation {

	private String firstStep;

	/**
	 * Indexed steps
	 */
	private Map<String, WizardStep> indexedSteps = new HashMap<String, WizardStep>();

	/**
	 * Current step stack
	 */
	private Stack<WizardStep> stepStack = new Stack<WizardStep>();

	/**
	 * Current step id
	 */
	private String currentStepName;

	@Override
	protected void init() {
		stepStack.clear();
		indexedSteps.clear();
		currentStepName = null;
		List<WizardStep> stepList = getSteps();
		// Indexation of steps
		for (WizardStep step : stepList) {
			indexedSteps.put(step.getName(), step);
		}
	}

	public WizardNavigationState getNavigationState(WizardStep step) {
		WizardNavigationState state = new WizardNavigationState();

		// Is the step already in the stack?
		int index = stepStack.indexOf(step);
		state.setPreviousPossible(index > 0);

		// Has the step a possible next step?
		state.setNextPossible(isNextAvailable(step.getName()));
		state.setFinishPossible(isFinishAvailable(step.getName()));

		// Ok
		return state;
	}

	/**
	 * @see net.sf.doolin.gui.wizard.model.WizardNavigation#next(net.sf.doolin.gui.wizard.model.WizardStep)
	 */
	public WizardStep next(WizardStep current) {
		if (currentStepName == null) {
			currentStepName = firstStep;
			WizardStep step = getStep(currentStepName);
			stepStack.push(step);
			return step;
		} else {
			String nextName = computeNextStep(currentStepName, getViewData());
			if (StringUtils.isNotBlank(nextName)) {
				currentStepName = nextName;
				WizardStep step = getStep(currentStepName);
				stepStack.push(step);
				return step;
			} else {
				return null;
			}
		}
	}

	/**
	 * Checks if the given step name can be following by another step.
	 * 
	 * @param name
	 *            Current step name
	 * @return <code>true</code> if the associated step could be followed by
	 *         another step
	 */
	protected abstract boolean isNextAvailable(String name);

	/**
	 * Checks if the given step name can be the last of the wizard.
	 * 
	 * @param name
	 *            Current step name
	 * @return <code>true</code> if the associated step could be the last of
	 *         the wizard.
	 */
	protected abstract boolean isFinishAvailable(String name);

	/**
	 * Computes the next step name from the current one.
	 * 
	 * @param name
	 *            Starting step name
	 * @param data
	 *            Wizard data
	 * @return New step name
	 */
	protected abstract String computeNextStep(String name, Object data);

	/**
	 * Gets an indexed step by its name
	 * 
	 * @param name
	 *            Step name
	 * @return Step
	 */
	protected WizardStep getStep(String name) {
		WizardStep step = indexedSteps.get(name);
		if (step != null) {
			return step;
		} else {
			throw new IllegalArgumentException("Unknown step name: " + name);
		}
	}

	/**
	 * @see net.sf.doolin.gui.wizard.model.WizardNavigation#previous(net.sf.doolin.gui.wizard.model.WizardStep)
	 */
	public WizardStep previous(WizardStep current) {
		if (stepStack.size() > 1) {
			stepStack.pop();
			WizardStep step = stepStack.peek();
			currentStepName = step.getName();
			return step;
		} else {
			return null;
		}
	}

	/**
	 * Returns the name of the first step
	 * 
	 * @return Name of the first step
	 */
	public String getFirstStep() {
		return firstStep;
	}

	/**
	 * Sets the name of the first step
	 * 
	 * @param firstStep
	 *            Name of the first step
	 */
	public void setFirstStep(String firstStep) {
		this.firstStep = firstStep;
	}

}
