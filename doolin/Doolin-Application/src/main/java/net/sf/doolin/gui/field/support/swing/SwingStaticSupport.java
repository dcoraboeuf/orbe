/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.FieldStatic;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.StaticSupport;
import net.sf.doolin.gui.service.MnemonicInfo;
import net.sf.doolin.gui.service.MnemonicInfoFactory;

/**
 * Support for a static field, based on a label.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingStaticSupport.java,v 1.4 2007/08/10 16:54:38 guinnessman
 *          Exp $
 */
public class SwingStaticSupport extends AbstractSwingFieldSupport<FieldStatic> implements StaticSupport {

	private JLabel label;

	protected void build() {
		label = new JLabel();
	}

	public void setText(String value) {
		// Mnemonic info
		MnemonicInfo labelInfo = GUIUtils.getService(MnemonicInfoFactory.class).getLabelInfo(value);
		labelInfo.configureLabel(label);
	}

	public JComponent getComponent() {
		return label;
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
	}

	/**
	 * Does nothing
	 * 
	 * @see net.sf.doolin.gui.field.event.EditEventBoundable#bindEditEvent(net.sf.doolin.gui.field.event.EventAction)
	 */
	public void bindEditEvent(EventAction eventAction) {
	}

}
