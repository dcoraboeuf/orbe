/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.support;

import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Root ancestor for wizard steps.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractWizardStep.java,v 1.1 2007/08/07 16:47:07 guinnessman
 *          Exp $
 */
public abstract class AbstractWizardStep implements WizardStep {

	private boolean optional;

	private String name;

	/**
	 * @return Returns the optional property.
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * @param optional
	 *            The optional property to set.
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	/**
	 * Returns the name
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            Step name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
