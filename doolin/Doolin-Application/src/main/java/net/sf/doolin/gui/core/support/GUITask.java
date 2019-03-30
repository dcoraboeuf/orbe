/*
 * Created on Aug 8, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.util.task.Task;

import net.sf.doolin.gui.core.View;

/**
 * Runnable task that interacts with the GUI.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUITask.java,v 1.1 2007/08/10 16:54:39 guinnessman Exp $
 */
public interface GUITask extends Task {

	/**
	 * Sets the associated view
	 * 
	 * @param view
	 *            View the task is running in.
	 */
	void setView(View view);

}
