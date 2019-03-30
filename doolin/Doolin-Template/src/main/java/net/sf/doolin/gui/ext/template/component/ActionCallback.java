/*
 * Created on 6 janv. 2006
 */
package net.sf.doolin.gui.ext.template.component;

import java.util.Map;

/**
 * Callback method for dealing with pseudo protocol "action:"
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionCallback.java,v 1.1 2007/08/15 15:13:31 guinnessman Exp $
 */
public interface ActionCallback {

	/**
	 * Executes a command.
	 * 
	 * @param actionId
	 *            ID of the action to execute.
	 * @param params
	 *            Parameters for the execution context
	 */
	void doAction(String actionId, Map<String, String> params);
	
}
