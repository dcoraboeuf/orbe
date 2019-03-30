/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.event.EditEventBoundable;
import net.sf.doolin.gui.field.support.AbstractFieldSupport;

/**
 * Abstract support for a field, based on Swing components.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractSwingFieldSupport.java,v 1.3 2007/08/07 16:47:05
 *          guinnessman Exp $
 * @param <F>
 *            Type of supported field
 */
public abstract class AbstractSwingFieldSupport<F extends Field> extends AbstractFieldSupport<F> implements EditEventBoundable {

	/**
	 * Delegates to the component.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		getComponent().setEnabled(enabled);
	}

	/**
	 * By default, does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#onBeforeDisplay()
	 */
	public void onBeforeDisplay() {
	}

}
