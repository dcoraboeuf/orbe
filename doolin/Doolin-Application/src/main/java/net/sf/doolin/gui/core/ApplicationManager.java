/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core;


/**
 * Application manager definition. This is the definition for the core component
 * of a Doolin GUI application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ApplicationManager.java,v 1.6 2007/08/10 16:54:43 guinnessman Exp $
 */
public interface ApplicationManager extends Runnable {

	/**
	 * Starting state flag
	 * 
	 * @return <code>true</code> if the application is currently starting.
	 */
	boolean isStarting();

	/**
	 * Started state flag
	 * 
	 * @return <code>true</code> if the application is started (initialization
	 *         is finished)
	 */
	boolean isStarted();

	/**
	 * Exits the application and stops the JVM.
	 * 
	 * @param code
	 *            Exit code for the application
	 * @see System#exit(int)
	 */
	void exit(int code);

}
