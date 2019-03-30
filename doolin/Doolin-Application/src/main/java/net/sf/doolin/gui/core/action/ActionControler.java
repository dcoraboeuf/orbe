/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.core.action;

import net.sf.doolin.gui.core.Action;

/**
 * This interface is called by the components which are using actions. It allows
 * them to configure the actions before actually executing them.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionControler.java,v 1.2 2007/08/10 16:54:42 guinnessman Exp $
 */
public interface ActionControler {

	/**
	 * Adapts the action to the controler context.
	 * 
	 * @param action
	 *            Action to adapt.
	 */
	void control(Action action);

}
