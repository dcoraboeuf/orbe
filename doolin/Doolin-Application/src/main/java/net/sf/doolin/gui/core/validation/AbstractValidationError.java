/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.validation;


public abstract class AbstractValidationError implements ValidationError {
	
	public String getShortMessage() {
		return getDetailedMessage();
	}

}
