/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.gui.wizard.model.WizardNavigation;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Utility root ancestor for wizard navigation policies.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractWizardNavigation.java,v 1.2 2007/08/15 09:05:24 guinnessman Exp $
 */
public abstract class AbstractWizardNavigation implements WizardNavigation {

	/**
	 * View data
	 */
	private Object viewData;

	/**
	 * List of steps
	 */
	private List<WizardStep> steps = new ArrayList<WizardStep>();

	/**
	 * 
	 * @return List of steps
	 */
	public List<WizardStep> getSteps() {
		return steps;
	}

	/**
	 * 
	 * @param steps
	 *            List of steps
	 */
	public void setSteps(List<WizardStep> steps) {
		this.steps = steps;
	}

	/**
	 * Sets the view data and performs specific initialization steps.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardNavigation#init(java.lang.Object)
	 */
	public void init(Object data) {
		viewData = data;
		init();
	}

	/**
	 * Specific initialization.
	 * 
	 */
	protected abstract void init();

	/**
	 * Returns the associated view data
	 * 
	 * @return The associated view data.
	 */
	public Object getViewData() {
		return viewData;
	}

}
