/*
 * Created on Sep 21, 2007
 */
package net.sf.doolin.gui.core.action;

import java.util.Map;

import net.sf.doolin.gui.core.Action;

/**
 * Defines an action which accepts parameters.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface ParameterizableAction extends Action {

	/**
	 * Sets a set of parameters to the action
	 * 
	 * @param params
	 *            Set of parameters
	 */
	void setParameters(Map<String, String> params);

}
