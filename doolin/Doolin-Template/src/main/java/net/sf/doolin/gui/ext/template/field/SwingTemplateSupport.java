/*
 * Created on 15 aoutt 07
 */
package net.sf.doolin.gui.ext.template.field;

import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.action.ParameterizableAction;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.ext.template.component.ActionCallback;
import net.sf.doolin.gui.ext.template.component.JTemplate;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.swing.AbstractSwingFieldSupport;

/**
 * Default support for the <code>{@link TemplateSupport}</code> support.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingTemplateSupport.java,v 1.1 2007/08/15 15:13:30 guinnessman
 *          Exp $
 */
public class SwingTemplateSupport extends AbstractSwingFieldSupport<FieldTemplate> implements TemplateSupport, ActionCallback {

	private JTemplate jtemplate;

	private JScrollPane scrollPane;

	@Override
	protected void build() {
		// Creates the instance
		jtemplate = new JTemplate();
		// Setup
		jtemplate.setFormat(getField().getFormat());
		jtemplate.setType(getField().getType());
		jtemplate.setPath(getField().getPath());
		// Action callback
		jtemplate.setActionCallback(this);
		// Ok
		scrollPane = new JScrollPane(jtemplate);
	}

	/**
	 * Delegates to the template component
	 * 
	 * @see JTemplate#setContext(Map)
	 * @see net.sf.doolin.gui.ext.template.field.TemplateSupport#setContext(java.util.Map)
	 */
	public void setContext(Map<String, Object> context) {
		jtemplate.setContext(context);
	}

	/**
	 * @see JScrollPane
	 * @see net.sf.doolin.gui.field.support.FieldSupport#getComponent()
	 */
	public JComponent getComponent() {
		return scrollPane;
	}

	/**
	 * @see JTemplate
	 * @see net.sf.doolin.gui.field.support.AbstractFieldSupport#getFocusableComponent()
	 */
	@Override
	public JComponent getFocusableComponent() {
		return jtemplate;
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.event.EditEventBoundable#bindEditEvent(net.sf.doolin.gui.field.event.EventAction)
	 */
	public void bindEditEvent(EventAction eventAction) {
	}

	public void doAction(String actionId, Map<String, String> params) {
		Action action = (Action) Application.getApplication().getBean(actionId);
		if (action instanceof ParameterizableAction) {
			((ParameterizableAction) action).setParameters(params);
		}
		getView().execute(action);
	}

}
