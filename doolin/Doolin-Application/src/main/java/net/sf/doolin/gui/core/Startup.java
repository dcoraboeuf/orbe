/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core;

/**
 * Startup policy for an application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Startup.java,v 1.1 2007/07/18 17:51:04 guinnessman Exp $
 */
public interface Startup {

	/**
	 * Starts the application.
	 * 
	 * @param applicationManager
	 *            Application manager to start.
	 */
	void start(ApplicationManager applicationManager);

}
