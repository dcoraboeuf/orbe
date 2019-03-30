/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JToolBar;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * Defines a toolbar item which is associated with an action.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ToolbarAction implements ToolbarItem {

	private Action action;

	/**
	 * Returns the associated action
	 * 
	 * @return Action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Sets the associated action
	 * 
	 * @param action
	 *            Action
	 */
	@Configurable(mandatory = true, description = "Associated action")
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Creates and installs a Swing action.
	 * 
	 * @see net.sf.doolin.gui.core.view.ToolbarItem#createToolbarItem(javax.swing.JToolBar,
	 *      net.sf.doolin.gui.core.View)
	 */
	public void createToolbarItem(JToolBar j, View view) {
		// Creates the Swing action
		javax.swing.Action swingAction = GUIUtils.getSwingFactory().createSwingAction(action, view, null, IconSize.MEDIUM);
		// Installs the swing action
		/* JButton button = */j.add(swingAction);
		// TODO Does something with the button
	}

}
