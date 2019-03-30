/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.validation;

import net.sf.doolin.gui.service.GUIStrings;

public class DefaultValidationError extends AbstractValidationError {
	
	private String code;
	
	private Object[] params;

	public DefaultValidationError(String code, Object... params) {
		this.code = code;
		this.params = params;
	}

	public String getDetailedMessage() {
		return GUIStrings.get(code, params);
	}

}
