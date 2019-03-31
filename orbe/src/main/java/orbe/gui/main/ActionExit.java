/*
 * Created on Oct 3, 2006
 */
package orbe.gui.main;

import net.sf.doolin.gui.Application;

/**
 * Exits the application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionExit.java,v 1.2 2006/10/27 10:52:33 guinnessman Exp $
 */
public class ActionExit extends AbstractActionContext {

	@Override
	protected void process() {
		if (closeCurrent()) {
			if (getView() != null) {
				getView().close();
			}
			Application.getApplication().getApplicationManager().exit(0);
		}
	}

}
