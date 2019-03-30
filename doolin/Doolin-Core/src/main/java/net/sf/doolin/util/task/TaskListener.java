package net.sf.doolin.util.task;

/**
 * Listens on events for the task
 * 
 * @author Damien Coraboeuf
 * @version $Id: TaskListener.java,v 1.1 2007/08/10 16:55:40 guinnessman Exp $
 */
public interface TaskListener {
	/**
	 * This method is called when the underlying task is finished.
	 */
	void onFinish();

	/**
	 * This method is called when the underlying task is raising an exception.
	 * 
	 * @param th
	 *            Exception to signal
	 */
	void onException(Throwable th);

	/**
	 * The task notifies some progress.
	 * 
	 * @param progress
	 *            Step
	 * @param progressName
	 *            Step name
	 */
	void onProgress(int progress, String progressName);

	/**
	 * Adjust the maximum of progress steps
	 * 
	 * @param newMaximum
	 *            New maximum value
	 */
	void setMaximum(int newMaximum);
}
