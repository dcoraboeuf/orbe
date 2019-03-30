/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.plugin;

import net.sf.doolin.gui.core.ApplicationManager;

/**
 * Plugin implementation for a plugin at application manager level.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractApplicationManagerPlugin.java,v 1.2 2007/08/10 16:54:40 guinnessman Exp $
 */
public abstract class AbstractApplicationManagerPlugin extends AbstractPlugin<ApplicationManager> {

	public void activate(ApplicationManager applicationManager, String scope) {
		if ("start".equals(scope)) {
			start(applicationManager);
		} else if ("init".equals(scope)) {
			init(applicationManager);
		} else if ("exit".equals(scope)) {
			exit(applicationManager);
		} else if ("shutdown".equals(scope)) {
			shutdown(applicationManager);
		}
	}

	/**
	 * Executed at the startup
	 * 
	 * @param application
	 *            Application that is started
	 */
	protected void start(ApplicationManager application) {
	}

	/**
	 * Executed during initialization
	 * 
	 * @param application
	 *            Application that is initialized
	 */
	protected void init(ApplicationManager application) {
	}

	/**
	 * Executed during the exit process
	 * 
	 * @param application
	 *            Application that is exited
	 */
	protected void exit(ApplicationManager application) {
	}

	/**
	 * Executed at the shutdown of the JVM
	 * 
	 * @param application
	 *            Application that is exited
	 */
	protected void shutdown(ApplicationManager application) {
	}

}
