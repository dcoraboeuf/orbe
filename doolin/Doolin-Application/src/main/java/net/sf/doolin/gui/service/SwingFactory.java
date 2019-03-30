/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

import java.awt.Component;

import javax.swing.Action;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.swing.BarContainer;

/**
 * Factory for Swing objects from Doolin GUI objects.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingFactory.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface SwingFactory {

	/**
	 * Creates a Swing action from a Doolin GUI action.
	 * 
	 * @param action
	 *            Encapsulated action
	 * @param view
	 *            Hosting view
	 * @param controler
	 *            Controler for the action before execution
	 * @param iconSize
	 *            Requested icon size for the any icon.
	 * @return Swing action
	 */
	Action createSwingAction(net.sf.doolin.gui.core.Action action, View view, ActionControler controler, IconSize iconSize);

	/**
	 * Sets the menubar on a component
	 * 
	 * @param view
	 *            View definition that mayb contain a menubar definition
	 * @param component
	 *            Component to host to menubar
	 */
	void setMenubar(View view, Component component);

	/**
	 * Sets the toolbars on a component.
	 * 
	 * @param view
	 *            Hosting view
	 * @param container
	 *            Container to set
	 */
	void setToolbars(View view, BarContainer container);

}
