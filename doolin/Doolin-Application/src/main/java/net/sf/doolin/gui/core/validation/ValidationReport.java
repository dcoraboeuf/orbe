/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {
	
	private List<ValidationError> validationErrorList = new ArrayList<ValidationError>();

	public boolean isOk() {
		return validationErrorList.isEmpty();
	}
	
	public void add (ValidationError validationError) {
		validationErrorList.add(validationError);
	}

	public Iterable<ValidationError> getValidationErrors() {
		return validationErrorList;
	}

}
