/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanNameAware;

import net.sf.doolin.gui.auth.AuthorizableItem;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.ActionListener;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.service.AlertManager;

/**
 * Utility implementation for an action.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractAction.java,v 1.6 2007/08/17 15:07:03 guinnessman Exp $
 */
public abstract class AbstractAction implements Action, BeanNameAware {

	private View view;

	private String name;

	private boolean enabled = true;

	private List<ActionListener> actionListeners = new ArrayList<ActionListener>(1);

	/**
	 * Gets the associated view
	 * 
	 * @return View the action runs in
	 */
	public View getView() {
		return view;
	}

	/**
	 * Sets the view this action must run in.
	 * 
	 * @see net.sf.doolin.gui.core.Action#setView(net.sf.doolin.gui.core.View)
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Executes the action by delegating the execution to the
	 * <code>{@link #process()}</code> method and by handling the errors.
	 * 
	 * @see #process()
	 * @see GUIUtils#getAlertManager()
	 * @see AlertManager#displayException(View, Throwable)
	 * @see net.sf.doolin.gui.core.Action#execute()
	 */
	public void execute() {
		try {
			process();
		} catch (Throwable th) {
			GUIUtils.getAlertManager().displayException(view, th);
		}
	}

	/**
	 * Main method of the action. Any exception may be thrown. They are handled
	 * by the <code>{@link #execute()}</code> mehod.
	 */
	protected abstract void process();

	public String getName() {
		return name;
	}

	/**
	 * Sets the key for the action name
	 * 
	 * @param name
	 *            Key of the action name
	 */
	public void setName(String name) {
		this.name = name;
		fireChange();
	}

	public void setBeanName(String beanName) {
		if (StringUtils.isBlank(name)) {
			name = beanName;
		}
	}

	/**
	 * Utility method to get the data associated to the view
	 * 
	 * @return View data
	 */
	protected Object getViewData() {
		return view.getViewData();
	}

	/**
	 * Utility method to validate the view content. In case of validation
	 * errors, they are displayed.
	 * 
	 * @return Result of the validation
	 * @see View#validate(ValidationReport)
	 * @see View#display(ValidationReport)
	 */
	protected boolean validateView() {
		ValidationReport report = new ValidationReport();
		view.validate(report);
		// Displays the validation report result
		view.display(report);
		// Result
		return report.isOk();
	}

	/**
	 * @see #getName()
	 * @see net.sf.doolin.gui.auth.AuthorizableItem#getAuthorizationIdentifier()
	 */
	public String getAuthorizationIdentifier() {
		return getName();
	}

	/**
	 * @see AuthorizableItem.Predefined#ACTION
	 * @see net.sf.doolin.gui.auth.AuthorizableItem#getAuthorizableItemType()
	 */
	public String getAuthorizableItemType() {
		return AuthorizableItem.Predefined.ACTION.getType();
	}

	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Initial state of the action.
	 * 
	 * @see net.sf.doolin.gui.core.Action#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		fireChange();
	}

	/**
	 * Fires a change in the action.
	 * 
	 * @see #setName(String)
	 * @see #setEnabled(boolean)
	 */
	protected void fireChange() {
		ActionListener[] actionListenerArray = actionListeners.toArray(new ActionListener[0]);
		for (int i = 0; i < actionListenerArray.length; i++) {
			ActionListener listener = actionListenerArray[i];
			listener.onActionChanged(this);
		}
	}

	public synchronized void addActionListener(ActionListener listener) {
		actionListeners.add(listener);
	}

	public synchronized void removeActionListener(ActionListener listener) {
		actionListeners.remove(listener);
	}

}
