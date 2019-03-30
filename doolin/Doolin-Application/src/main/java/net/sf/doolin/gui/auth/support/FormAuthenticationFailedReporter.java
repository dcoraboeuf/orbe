/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.support;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;

/**
 * This reporter puts the validation report into a property of the login form.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormAuthenticationFailedReporter.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 * @param <T>
 *            Type of login form
 */
public class FormAuthenticationFailedReporter<T> implements AuthenticationFailedReporter<T> {

	private String formProperty;

	/**
	 * Returns the <code>formProperty</code> property.
	 * 
	 * @return <code>formProperty</code> property.
	 */
	public String getFormProperty() {
		return formProperty;
	}

	/**
	 * Sets the <code>formProperty</code> property.
	 * 
	 * @param formProperty
	 *            <code>formProperty</code> property.
	 */
	@Configurable(mandatory = true, description = "Property in the login form to fill with the validation report")
	public void setFormProperty(String formProperty) {
		this.formProperty = formProperty;
	}

	/**
	 * @see GUIUtils#setProperty(Object, String, Object)
	 * @see net.sf.doolin.gui.auth.support.AuthenticationFailedReporter#report(net.sf.doolin.gui.core.validation.ValidationReport,
	 *      java.lang.Object)
	 */
	public void report(ValidationReport report, T form) {
		GUIUtils.setProperty(form, formProperty, report);
	}

}
