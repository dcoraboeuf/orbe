/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field.support;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.FieldValidationReport;

/**
 * Support interface for the <code>{@link FieldValidationReport}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ValidationReportFieldSupport.java,v 1.1 2007/08/17 15:06:59 guinnessman Exp $
 */
public interface ValidationReportFieldSupport extends FieldSupport<FieldValidationReport> {

	/**
	 * Sets the validation report.
	 * 
	 * @param report
	 *            Validation report to display in the field.
	 */
	void setValidationReport(ValidationReport report);

}
