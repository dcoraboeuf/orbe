/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.util.unit;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.ObjectUtils;

/**
 * Bean editor for the <code>ValueUnit</code>.
 * 
 * @see ValueUnit
 * @author Damien Coraboeuf
 * @version $Id: ValueUnitEditor.java,v 1.1 2007/07/31 15:32:49 guinnessman Exp $
 */
public class ValueUnitEditor extends PropertyEditorSupport {

	/**
	 * @see ValueUnit#toString()
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		return ObjectUtils.toString(getValue(), "");
	}

	/**
	 * @see ValueUnit#valueOf(String)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ValueUnit valueUnit = ValueUnit.valueOf(text);
		setValue(valueUnit);
	}

}
