/*
 * Created on Aug 8, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.util.task.AbstractTask;

import net.sf.doolin.gui.core.View;

/**
 * Abstract implementation for a runnable task that runs in the GUI.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractGUITask.java,v 1.1 2007/08/10 16:54:39 guinnessman Exp $
 */
public abstract class AbstractGUITask extends AbstractTask implements GUITask {

	private View view;

	/**
	 * Returns the view
	 * 
	 * @return View
	 */
	public View getView() {
		return view;
	}

	/**
	 * Sets the view
	 * 
	 * @param view
	 *            View
	 */
	public void setView(View view) {
		this.view = view;
	}

}
