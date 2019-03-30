/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.field.Field;

/**
 * Event action that executes a standard action.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ActionEventAction extends AbstractEventAction {

	private Action action;

	/**
	 * Returns the action to execute.
	 * 
	 * @return Action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Sets the action to execute.
	 * 
	 * @param action
	 *            Action
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	protected void process(View view, Field field, ActionControler controler) {
		// Default controler
		new EventBinderActionControler(field).control(action);
		// Additional controler
		if (controler != null) {
			controler.control(action);
		}
		// Execution
		view.execute(action);
	}

}
