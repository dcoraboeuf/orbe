/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.tree;

import net.sf.doolin.gui.core.Action;

/**
 * Action associated with a node definition. If no <code>action</code> is
 * defined, it is assumed that this node action defines a separator.
 * 
 * @author Damien Coraboeuf
 * @version $Id: NodeAction.java,v 1.3 2007/08/15 09:05:21 guinnessman Exp $
 */
public class NodeAction {

	private boolean defaultAction;

	private Action action;

	/**
	 * @return Is this action the default action for the node?
	 */
	public boolean isDefault() {
		return defaultAction;
	}

	/**
	 * @param defaultAction
	 *            Is this action the default action for the node?
	 */
	public void setDefault(boolean defaultAction) {
		this.defaultAction = defaultAction;
	}

	/**
	 * @return Associated action or <code>null</code> it this object defines a
	 *         separator
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action
	 *            Associated action or <code>null</code> it this object
	 *            defines a separator
	 */
	public void setAction(Action action) {
		this.action = action;
	}

}
