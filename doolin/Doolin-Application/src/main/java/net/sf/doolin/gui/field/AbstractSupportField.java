/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field;

import javax.swing.JComponent;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.event.EditEventBoundable;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.FieldSupport;
import net.sf.doolin.gui.field.support.FieldSupportFactory;

public abstract class AbstractSupportField<S extends FieldSupport> extends AbstractField implements EditEventBoundable {

	private S support;

	@Override
	protected void init() {
		if (support == null) {
			support = createSupport();
		}
	}

	protected abstract S createSupport();

	@SuppressWarnings("unchecked")
	protected <F extends FieldSupport> F createSupport(Class<F> supportClass) {
		FieldSupportFactory fieldSupportFactory = Application.getApplication().getService(FieldSupportFactory.class);
		F fieldSupport = fieldSupportFactory.getFieldSupport(supportClass);
		fieldSupport.init(this);
		return fieldSupport;
	}

	/**
	 * Delegates to the support.
	 * 
	 * @see FieldSupport#getComponent()
	 * @see net.sf.doolin.gui.field.Field#getComponent()
	 */
	public JComponent getComponent() {
		return support.getComponent();
	}

	/**
	 * Delegates to the support.
	 * 
	 * @see FieldSupport#getFocusableComponent()
	 * 
	 * @see net.sf.doolin.gui.field.AbstractField#getFocusableComponent()
	 */
	@Override
	public JComponent getFocusableComponent() {
		return support.getFocusableComponent();
	}

	/**
	 * @see net.sf.doolin.gui.field.Field#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
		support.setValidationError(validationError);
	}

	/**
	 * @return Returns the support.
	 */
	public S getSupport() {
		return support;
	}

	/**
	 * @param support
	 *            The support to set.
	 */
	public void setSupport(S support) {
		this.support = support;
	}

	/**
	 * Delegates to the support.
	 * 
	 * @see FieldSupport#setEnabled(boolean)
	 * @see net.sf.doolin.gui.field.Field#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		getSupport().setEnabled(enabled);
	}

	public void bindEditEvent(EventAction eventAction) {
		if (support instanceof EditEventBoundable) {
			((EditEventBoundable) support).bindEditEvent(eventAction);
		}
	}

	/**
	 * Delegates to the support.
	 * 
	 * @see FieldSupport#onBeforeDisplay()
	 * @see net.sf.doolin.gui.field.AbstractField#onBeforeDisplay()
	 */
	@Override
	public void onBeforeDisplay() {
		getSupport().onBeforeDisplay();
	}

}
