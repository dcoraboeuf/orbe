/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.form.Form;

/**
 * Abstract support for fields.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractFieldSupport.java,v 1.3 2007/08/07 16:47:10 guinnessman
 *          Exp $
 * @param <F>
 *            Type of field
 */
public abstract class AbstractFieldSupport<F extends Field> implements FieldSupport<F> {

	private F field;

	public void init(F field) {
		this.field = field;
		build();
	}

	/**
	 * Builds the field.
	 */
	protected abstract void build();

	/**
	 * Returns the associated field.
	 * 
	 * @return Associated field
	 */
	public F getField() {
		return field;
	}

	/**
	 * Returns the associated form
	 * 
	 * @return Associated form
	 */
	protected Form getForm() {
		return field.getForm();
	}

	/**
	 * Returns the associated view
	 * 
	 * @return Associated view
	 */
	protected View getView() {
		return getForm().getView();
	}

	/**
	 * Returns the component.
	 * 
	 * @see FieldSupport#getComponent()
	 * @see net.sf.doolin.gui.field.support.FieldSupport#getFocusableComponent()
	 */
	public JComponent getFocusableComponent() {
		return getComponent();
	}

}
