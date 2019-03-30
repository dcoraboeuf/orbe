/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import javax.swing.JButton;
import javax.swing.JComponent;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.FieldButton;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.ButtonSupport;
import net.sf.doolin.gui.service.SwingFactory;
import net.sf.doolin.gui.swing.SwingUtils;

/**
 * Support for a {@link FieldButton button field}, based on a
 * <code>{@link JButton}</code>.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingButtonSupport.java,v 1.2 2007/08/07 16:47:05 guinnessman
 *          Exp $
 */
public class SwingButtonSupport extends AbstractSwingFieldSupport<FieldButton> implements ButtonSupport {

	private JButton button;

	@Override
	protected void build() {
		Action action = getField().getAction();
		SwingFactory swingFactory = GUIUtils.getService(SwingFactory.class);
		javax.swing.Action swingAction = swingFactory.createSwingAction(action, getView(), null, IconSize.SMALL);
		button = new JButton(swingAction);
	}

	public JComponent getComponent() {
		return button;
	}

	public void setFieldData(Object formData, Object fieldData) {
		// TODO Code of SwingButtonSupport.setFieldData method
	}

	public void setValidationError(ValidationError validationError) {
	}

	/**
	 * Does nothing
	 * 
	 * @see net.sf.doolin.gui.field.event.EditEventBoundable#bindEditEvent(net.sf.doolin.gui.field.event.EventAction)
	 */
	public void bindEditEvent(EventAction eventAction) {
	}

	/**
	 * If needed, sets this button as the default button.
	 * 
	 * @see SwingUtils#setDefaultButton(java.awt.Component, JButton)
	 * @see net.sf.doolin.gui.field.support.swing.AbstractSwingFieldSupport#onBeforeDisplay()
	 */
	@Override
	public void onBeforeDisplay() {
		SwingUtils.setDefaultButton(getView().getComponent(), button);
	}

}
