package net.sf.doolin.util.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract implementation for a runnable task.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractTask.java,v 1.2 2007/08/15 14:02:56 guinnessman Exp $
 */
public abstract class AbstractTask implements Task {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractTask.class);

	/**
	 * Name
	 */
	private String name;

	/**
	 * Task listener
	 */
	private TaskListener taskListener;

	/**
	 * Default constructor.
	 */
	public AbstractTask() {
	}

	/**
	 * Constructor with a predefined name
	 * 
	 * @param name
	 *            Task name
	 */
	public AbstractTask(String name) {
		this.name = name;
	}

	/**
	 * Sets the name of the task
	 * 
	 * @param name
	 *            Task name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Runs the task
	 */
	public final void run() {
		// Coding method run
		try {
			log.debug("Starting task " + name);
			// Runs the task
			execute();
			log.debug("Ending task " + name);
			// Ok
			fireFinish();
			log.debug("Task " + name + " is finished.");
		} catch (Throwable ex) {
			log.debug("Error in task " + name);
			fireException(ex);
		}
	}

	/**
	 * The task is finished
	 */
	protected void fireFinish() {
		if (taskListener != null) {
			taskListener.onFinish();
		}
	}

	/**
	 * The task notifies some progress.
	 * 
	 * @param progress
	 *            Step
	 * @param progressName
	 *            Step name
	 */
	protected void fireProgress(int progress, String progressName) {
		if (taskListener != null) {
			taskListener.onProgress(progress, progressName);
		}
	}

	/**
	 * An exception has occured
	 * 
	 * @param th
	 *            Exception to fire to the listener
	 * @see TaskListener#onException(Throwable)
	 */
	protected void fireException(Throwable th) {
		if (taskListener != null) {
			taskListener.onException(th);
		}
	}

	/**
	 * Executes the task
	 * 
	 * @throws Exception
	 *             If an exception occurs during the execution of the task
	 */
	protected abstract void execute() throws Exception;

	/**
	 * @return Returns the taskListener.
	 */
	public TaskListener getTaskListener() {
		return taskListener;
	}

	/**
	 * @param taskListener
	 *            The taskListener to set.
	 */
	public void setTaskListener(TaskListener taskListener) {
		this.taskListener = taskListener;
	}
}
