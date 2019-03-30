/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.validation;

import org.apache.commons.lang.Validate;

import net.sf.doolin.gui.core.View;

/**
 * This reporter is delegating the display to a component of the view.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DelegateValidationReporter.java,v 1.1 2007/08/10 16:54:35 guinnessman Exp $
 */
public class DelegateValidationReporter extends AbstractValidationReporter {

	public void report(View view, ValidationReport report) {
		// Checks
		Validate.isTrue(view instanceof ValidationReporterDelegator, "The view must implement the ValidationReporterDelegator interface.");
		// Conversion
		ValidationReporterDelegator delegator = (ValidationReporterDelegator) view;
		// Get the delegate
		ValidationReporter delegate = delegator.getValidationReporterDelegate();
		// Forwards the report
		delegate.report(view, report);
	}

}
