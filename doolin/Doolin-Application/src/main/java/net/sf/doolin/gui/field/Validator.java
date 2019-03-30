/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.core.validation.ValidationReport;

public interface Validator {
	
	void validate (Field field, ValidationReport validationReport);

}
