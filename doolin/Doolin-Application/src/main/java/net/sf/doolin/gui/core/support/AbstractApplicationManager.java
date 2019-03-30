/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.Startup;

/**
 * Abstract root for application managers.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractApplicationManager.java,v 1.4 2007/08/10 16:54:39 guinnessman Exp $
 */
public abstract class AbstractApplicationManager implements ApplicationManager {

	/**
	 * Startup policy
	 */
	private Startup startup;

	/**
	 * @return Returns the startup.
	 */
	public Startup getStartup() {
		return startup;
	}

	/**
	 * @param startup
	 *            The startup to set.
	 */
	public void setStartup(Startup startup) {
		this.startup = startup;
	}

}
