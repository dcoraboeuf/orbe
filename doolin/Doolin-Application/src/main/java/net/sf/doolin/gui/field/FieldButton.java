/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.field.support.ButtonSupport;

public class FieldButton extends AbstractSupportField<ButtonSupport> {

	private Action action;

	private boolean defaultButton = false;

	public void setFieldData(Object formData, Object fieldData) {
		getSupport().setFieldData(formData, fieldData);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	protected void init() {
		super.init();
		setReadOnly(true);
	}

	@Override
	protected ButtonSupport createSupport() {
		return createSupport(ButtonSupport.class);
	}

	public Object getFieldData(Object formData) {
		return null;
	}

	/**
	 * @see #setDefaultButton(boolean)
	 * @return <code>true</code> if this button must be a default button in
	 *         the current window
	 */
	public boolean isDefaultButton() {
		return defaultButton;
	}

	/**
	 * Sets if this button must be a default button in the current window
	 * 
	 * @param defaultButton
	 *            <code>true</code> if this button must be a default button in
	 *            the current window
	 */
	@Configurable(mandatory = false, description = "true if this button must be a default button inthe current window", defaultsTo = "false")
	public void setDefaultButton(boolean defaultButton) {
		this.defaultButton = defaultButton;
	}

}
