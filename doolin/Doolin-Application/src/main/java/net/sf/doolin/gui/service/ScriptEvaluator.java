/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.service;

import java.util.Map;

/**
 * This service is responsible for the execution of scripts.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ScriptEvaluator.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface ScriptEvaluator {

	/**
	 * Executes a script.
	 * 
	 * @param scriptName
	 *            Language for the script
	 * @param script
	 *            Script to execute
	 * @param context
	 *            Context of the script. This map is updated with all generated
	 *            global variables at the end of the script.
	 */
	void evaluate(String scriptName, String script, Map<String, Object> context);

}
