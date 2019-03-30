/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.field.support;

import java.math.BigDecimal;

import net.sf.doolin.gui.field.FieldDecimal;

/**
 * Support interface for the <code>{@link DecimalSupport}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface DecimalSupport extends FieldSupport<FieldDecimal> {

	/**
	 * Sets the field value
	 * 
	 * @param value
	 *            Field value
	 */
	void setValue(BigDecimal value);

	/**
	 * Gets the field value
	 * 
	 * @return Field value
	 */
	BigDecimal getValue();

}
