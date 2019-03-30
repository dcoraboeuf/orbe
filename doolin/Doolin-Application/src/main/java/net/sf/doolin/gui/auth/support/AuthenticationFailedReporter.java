/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.support;

import net.sf.doolin.gui.core.validation.ValidationReport;

/**
 * This interface defines a reporter for authentication errors.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AuthenticationFailedReporter.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 * @param <T>
 *            Type of the login form
 */
public interface AuthenticationFailedReporter<T> {

	/**
	 * Reports the error
	 * 
	 * @param report
	 *            Validation report
	 * @param form
	 *            Login form
	 */
	void report(ValidationReport report, T form);

}
