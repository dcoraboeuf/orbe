/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.swing;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.icon.Icons;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;

/**
 * This component hosts another component and is able to display a piece of
 * information (icon + tip) associated to it.
 * 
 * @param <C>
 *            Type of the hosted component
 * @author Damien Coraboeuf
 * @version $Id: InfoComponent.java,v 1.6 2007/08/14 11:49:00 guinnessman Exp $
 */
public class InfoComponent<C extends JComponent> extends JPanel {

	/**
	 * Hosted component
	 */
	private C component;

	/**
	 * Info label
	 */
	private JLabel labelInfo;

	/**
	 * Constructor
	 */
	public InfoComponent() {
		super(new BorderLayout());
		// Label at the trailing side of the panel
		labelInfo = new JLabel();
		labelInfo.setVerticalAlignment(SwingConstants.TOP);
		add(labelInfo, BorderLayout.LINE_END);
	}

	/**
	 * Constructor with a predefined component
	 * 
	 * @param c
	 *            Hosted component
	 */
	public InfoComponent(C c) {
		this();
		setComponent(c);
	}

	/**
	 * Gets the hosted component
	 * 
	 * @return Hosted component
	 */
	public C getComponent() {
		return component;
	}

	/**
	 * Sets the hosted component
	 * 
	 * @param c
	 *            Hosted component
	 */
	public void setComponent(C c) {
		if (c != component) {
			if (component != null) {
				remove(component);
			}
			component = c;
			add(component, BorderLayout.CENTER);
		}
	}

	@Override
	public void setToolTipText(String text) {
		labelInfo.setToolTipText(text);
		if (component != null) {
			component.setToolTipText(text);
		}
	}

	/**
	 * Sets the validation error state on this component.
	 * 
	 * @param validationError
	 *            Error to display
	 */
	public void setValidationError(ValidationError validationError) {
		if (validationError != null) {
			// Message
			String message = validationError.getShortMessage();
			// Tip
			setToolTipText(message);
			// Icon
			Icon iconObject = GUIUtils.getIcon(Icons.ERROR, IconSize.MINI);
			labelInfo.setIcon(iconObject);
		} else {
			setToolTipText(null);
			labelInfo.setIcon(null);
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		component.setEnabled(enabled);
		labelInfo.setEnabled(enabled);
	}

}
