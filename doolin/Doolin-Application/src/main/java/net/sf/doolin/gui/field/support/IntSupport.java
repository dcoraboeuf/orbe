/*
 * Created on Sep 10, 2007
 */
package net.sf.doolin.gui.field.support;

import net.sf.doolin.gui.field.FieldInt;

/**
 * Support for the <code>{@link FieldInt}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface IntSupport extends FieldSupport<FieldInt> {

	/**
	 * Sets the value
	 * 
	 * @param value
	 *            Integer value
	 */
	void setValue(int value);

	/**
	 * Gets the value
	 * 
	 * @return Integer value
	 */
	int getValue();

}
