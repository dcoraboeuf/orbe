/*
 * Created on Aug 8, 2007
 */
package net.sf.doolin.util.task;

/**
 * This interface defines a task which is runnable.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Task.java,v 1.3 2007/08/10 16:55:40 guinnessman Exp $
 */
public interface Task extends Runnable {

	/**
	 * Gets the task name
	 * 
	 * @return Displayable task name
	 */
	String getName();

	/**
	 * Sets the associated task listener.
	 * 
	 * @param taskListener
	 *            Associated task listener.
	 */
	void setTaskListener(TaskListener taskListener);

}
