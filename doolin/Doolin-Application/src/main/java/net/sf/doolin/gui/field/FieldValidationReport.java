/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.support.ValidationReportFieldSupport;

/**
 * This read-only field displays a {@link ValidationReport validation report} in
 * the form.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldValidationReport.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 */
public class FieldValidationReport extends AbstractSupportField<ValidationReportFieldSupport> {

	/**
	 * Sets the field as read-only
	 * 
	 * @see #setReadOnly(boolean)
	 * @see net.sf.doolin.gui.field.AbstractSupportField#init()
	 */
	@Override
	protected void init() {
		super.init();
		setReadOnly(true);
	}

	/**
	 * @see ValidationReportFieldSupport
	 * @see #createSupport(Class)
	 * @see net.sf.doolin.gui.field.AbstractSupportField#createSupport()
	 */
	@Override
	protected ValidationReportFieldSupport createSupport() {
		return createSupport(ValidationReportFieldSupport.class);
	}

	public Object getFieldData(Object formData) {
		return null;
	}

	/**
	 * @see ValidationReportFieldSupport#setValidationReport(ValidationReport)
	 * @see net.sf.doolin.gui.field.Field#setFieldData(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setFieldData(Object formData, Object fieldData) {
		ValidationReport report = (ValidationReport) fieldData;
		getSupport().setValidationReport(report);
	}

}
