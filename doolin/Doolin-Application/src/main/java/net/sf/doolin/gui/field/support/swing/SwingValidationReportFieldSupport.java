/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.collections.IteratorUtils;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.icon.Icons;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.FieldValidationReport;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.ValidationReportFieldSupport;

/**
 * Support for the <code>{@link FieldValidationReport}</code>.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingValidationReportFieldSupport.java,v 1.1 2007/08/17 15:06:52 guinnessman Exp $
 */
public class SwingValidationReportFieldSupport extends AbstractSwingFieldSupport<FieldValidationReport> implements ValidationReportFieldSupport {

	/**
	 * Container
	 */
	private JPanel container;

	@Override
	protected void build() {
		// Initialization
		container = new JPanel();
		container.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
		container.setBackground(SystemColor.window);
	}

	/**
	 * If the given report is <code>null</code>, the whole component is
	 * hidden.
	 * 
	 * @see net.sf.doolin.gui.field.support.ValidationReportFieldSupport#setValidationReport(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void setValidationReport(ValidationReport report) {
		// Null
		if (report == null) {
			container.setVisible(false);
		}
		// Not null
		else {
			container.setVisible(true);
			// Clear
			container.removeAll();
			// List of errors
			Iterable<ValidationError> errors = report.getValidationErrors();
			@SuppressWarnings("unchecked")
			List<ValidationError> errorList = IteratorUtils.toList(errors.iterator());
			// New layout
			GridLayout gridLayout = new GridLayout(errorList.size(), 1);
			container.setLayout(gridLayout);
			// For each error
			for (ValidationError error : errorList) {
				JLabel errorLabel = createErrorLabel(error);
				container.add(errorLabel);
			}
		}
	}

	/**
	 * Creates a component for an error.
	 * 
	 * @param error
	 *            Error to display
	 * @return Associated component
	 */
	protected JLabel createErrorLabel(ValidationError error) {
		JLabel label = new JLabel();
		// Icon
		label.setIcon(GUIUtils.getIcon(Icons.ERROR, IconSize.MINI));
		// Text
		String text = error.getDetailedMessage();
		label.setText(text);
		// Ok
		return label;
	}

	public JComponent getComponent() {
		return container;
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
