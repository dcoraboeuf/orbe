/*
 * Created on Aug 10, 2007
 */
package net.sf.doolin.gui.core.validation;

/**
 * This interface defines a component that provides a validation reporter.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ValidationReporterDelegator.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface ValidationReporterDelegator {

	/**
	 * Get the validation reporter delegate
	 * 
	 * @return Validation reporter to use
	 */
	ValidationReporter getValidationReporterDelegate();

}
