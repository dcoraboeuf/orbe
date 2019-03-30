/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.action.NOPAction;

public class MenuAction extends MenuItem {

	private Action action = new NOPAction();

	/**
	 * @return Returns the action.
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(Action action) {
		this.action = action;
	}

}
