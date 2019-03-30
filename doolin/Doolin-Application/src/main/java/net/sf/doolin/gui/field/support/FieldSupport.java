/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.Field;

/**
 * Defines a supporting class for a field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldSupport.java,v 1.4 2007/08/14 11:48:52 guinnessman Exp $
 * @param <F>
 *            Type of the field which is supported
 */
public interface FieldSupport<F extends Field> {

	void init(F field);

	JComponent getComponent();

	/**
	 * Returns the component which receives the focus and which deals with
	 * events (keyboard for example)
	 * 
	 * @return Component
	 */
	JComponent getFocusableComponent();

	void setValidationError(ValidationError validationError);

	/**
	 * Enables or disables the field.
	 * 
	 * @param enabled
	 *            Enabled flag.
	 */
	void setEnabled(boolean enabled);

	/**
	 * This method is called before the form is actually displayed.
	 * 
	 * @see Field#onBeforeDisplay()
	 */
	void onBeforeDisplay();

}
