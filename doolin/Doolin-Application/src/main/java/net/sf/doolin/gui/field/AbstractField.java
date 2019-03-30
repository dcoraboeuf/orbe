/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import net.sf.doolin.gui.bus.GUISubscriber;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.event.EventBinder;
import net.sf.doolin.gui.form.Form;
import net.sf.doolin.gui.service.GUIStrings;

import org.apache.commons.lang.StringUtils;

/**
 * Utility implementation for a field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractField.java,v 1.6 2007/08/18 17:19:47 guinnessman Exp $
 */
public abstract class AbstractField implements Field {

	private String name;

	private String property;

	private boolean readOnly;

	private Object constraint;

	private boolean initialized = false;

	private List<Validator> validators = new ArrayList<Validator>();

	private Form form;

	private List<GUISubscriber> subscribers = new ArrayList<GUISubscriber>();

	private List<EventBinder> eventBinders = new ArrayList<EventBinder>();

	public final void init(Form form) {
		this.form = form;
		if (!initialized) {
			init();
			initSubscribers();
			initEventBinders();
			initialized = true;
		}
	}

	protected void initEventBinders() {
		for (EventBinder eventBinder : eventBinders) {
			eventBinder.bind(this);
		}
	}

	@SuppressWarnings("unchecked")
	protected void initSubscribers() {
		for (GUISubscriber subscriber : subscribers) {
			subscriber.register(this);
		}
	}

	protected abstract void init();

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Form getForm() {
		return form;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void validate(ValidationReport validationReport) {
		for (Validator validator : validators) {
			validator.validate(this, validationReport);
		}
	}

	public List<Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	/**
	 * @see net.sf.doolin.gui.field.Field#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		if (StringUtils.isNotBlank(name)) {
			return GUIStrings.get(name);
		} else {
			return property;
		}
	}

	public List<GUISubscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<GUISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public List<EventBinder> getEventBinders() {
		return eventBinders;
	}

	public void setEventBinders(List<EventBinder> eventBinders) {
		this.eventBinders = eventBinders;
	}

	/**
	 * By default, returns the field component.
	 * 
	 * @see Field#getComponent()
	 * @see net.sf.doolin.gui.field.Field#getFocusableComponent()
	 */
	public JComponent getFocusableComponent() {
		return getComponent();
	}

	/**
	 * Returns the constraint
	 * 
	 * @return Associated constraint
	 */
	public Object getConstraint() {
		return constraint;
	}

	/**
	 * Sets the constraint
	 * 
	 * @param constraint
	 *            Associated constraint
	 */
	public void setConstraint(Object constraint) {
		this.constraint = constraint;
	}

	/**
	 * By default, does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.Field#onBeforeDisplay()
	 */
	public void onBeforeDisplay() {
	}

}
