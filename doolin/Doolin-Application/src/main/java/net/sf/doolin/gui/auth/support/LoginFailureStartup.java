/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.auth.support;

import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.Startup;

/**
 * Default startup when a login has failed.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LoginFailureStartup.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 */
public class LoginFailureStartup implements Startup {

	/**
	 * Exits the application.
	 * 
	 * @see ApplicationManager#exit(int)
	 * @see net.sf.doolin.gui.core.Startup#start(net.sf.doolin.gui.core.ApplicationManager)
	 */
	public void start(ApplicationManager applicationManager) {
		applicationManager.exit(0);
	}

}
