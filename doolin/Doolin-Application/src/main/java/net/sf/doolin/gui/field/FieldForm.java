/*
 * Created on 15 août 07
 */
package net.sf.doolin.gui.field;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.form.FieldContainer;
import net.sf.doolin.gui.form.Form;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.style.Style;

/**
 * This field declares a subform as a field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldForm.java,v 1.2 2007/08/18 17:19:47 guinnessman Exp $
 */
public class FieldForm extends AbstractField implements FieldContainer {

	private Form subForm;

	private JPanel container;

	private Style style;

	private String title;

	private boolean border;

	@Override
	protected void init() {
		// Creates the container
		container = new JPanel(new BorderLayout());

		// Initializes the form
		subForm.setView(getForm().getView());
		subForm.init();

		// Title & border
		if (border) {
			Border panelBorder = BorderFactory.createEtchedBorder();
			if (StringUtils.isNotBlank(title)) {
				String titleValue = GUIStrings.get(title);
				container.setBorder(BorderFactory.createTitledBorder(panelBorder, titleValue));
			} else {
				container.setBorder(panelBorder);
			}
		}

		// Applies any style
		if (style != null) {
			style.apply(container);
		}

		// Ok
		container.add(subForm.getComponent(), BorderLayout.CENTER);
	}

	public JComponent getComponent() {
		return container;
	}

	public Object getFieldData(Object formData) {
		return subForm.getFormData();
	}

	public void setEnabled(boolean enabled) {
		subForm.setEnabled(enabled);
	}

	public void setFieldData(Object formData, Object fieldData) {
		subForm.setFormData(fieldData);
	}

	/**
	 * Cleans underlying fields when the error is <code>null</code>.
	 * 
	 * @see net.sf.doolin.gui.field.Field#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
		if (validationError == null) {
			subForm.display(new ValidationReport());
		}
	}

	/**
	 * Delegates to the subform.
	 * 
	 * @see net.sf.doolin.gui.field.AbstractField#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	@Override
	public void validate(ValidationReport validationReport) {
		subForm.validate(validationReport);
	}

	/**
	 * Returns the underlying form
	 * 
	 * @return Form
	 */
	public Form getSubForm() {
		return subForm;
	}

	/**
	 * Sets the underlying form.
	 * 
	 * @param form
	 *            Form
	 */
	@Configurable(mandatory = true, description = "Underlying form")
	public void setSubForm(Form form) {
		this.subForm = form;
	}

	/**
	 * Returns the style for this subform
	 * 
	 * @return Style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets the style for this subform
	 * 
	 * @param style
	 *            Style
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public boolean isBorder() {
		return border;
	}

	@Configurable(mandatory = false, description = "Defines if the section has a border", defaultsTo = "false")
	public void setBorder(boolean border) {
		this.border = border;
	}

	public String getTitle() {
		return title;
	}

	@Configurable(mandatory = false, description = "Title key for the section")
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Delegates to the form.
	 * 
	 * @see Form#getFieldByName(String)
	 * @see net.sf.doolin.gui.form.FieldContainer#getFieldByName(java.lang.String)
	 */
	public Field getFieldByName(String fieldName) {
		return subForm.getFieldByName(fieldName);
	}

}
