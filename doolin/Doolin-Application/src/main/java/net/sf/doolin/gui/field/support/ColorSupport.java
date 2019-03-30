/*
 * Created on Sep 19, 2007
 */
package net.sf.doolin.gui.field.support;

import java.awt.Color;

import net.sf.doolin.gui.field.FieldColor;

/**
 * Support interface for the <code>{@link FieldColor}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface ColorSupport extends FieldSupport<FieldColor> {

	/**
	 * Sets the color
	 * 
	 * @param c
	 *            Color
	 */
	void setValue(Color c);

	/**
	 * Returns the selected color
	 * 
	 * @return Color
	 */
	Color getValue();

}
