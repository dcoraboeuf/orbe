/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.core.Action;

/**
 * This interface defines an action that can react to a selection.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SelectionAction.java,v 1.1 2007/08/14 11:48:51 guinnessman Exp $
 * @param <T>
 *            Type of the selection
 */
public interface SelectionAction<T> extends Action {

	/**
	 * Sets the selection before the execution of this action.
	 * 
	 * @param selection
	 *            Selected object or <code>null</code> if there is no
	 *            selection.
	 */
	void setSelection(T selection);

}
