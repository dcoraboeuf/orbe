/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.action;

import net.sf.doolin.gui.Application;

/**
 * This action exists the application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionExit.java,v 1.1 2007/07/18 17:51:10 guinnessman Exp $
 */
public class ActionExit extends ActionClose {
	
	@Override
	protected void close() {
		super.close();
		Application.getApplication().getApplicationManager().exit(0);
	}

}
