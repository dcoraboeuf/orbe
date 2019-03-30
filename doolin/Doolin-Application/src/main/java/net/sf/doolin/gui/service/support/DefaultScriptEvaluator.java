/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.service.support;

import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import net.sf.doolin.gui.service.ScriptEvaluator;

/**
 * Default implementation for the script evaluator service.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultScriptEvaluator.java,v 1.1 2007/08/07 17:36:20
 *          guinnessman Exp $
 */
public class DefaultScriptEvaluator implements ScriptEvaluator {

	private ScriptEngineManager scriptEngineManager;

	/**
	 * Initialization
	 */
	public DefaultScriptEvaluator() {
		scriptEngineManager = new ScriptEngineManager();
	}

	/**
	 * @see ScriptEngineManager#getEngineByName(String)
	 * @see ScriptEngine#eval(String, ScriptContext)
	 * @see net.sf.doolin.gui.service.ScriptEvaluator#evaluate(String,
	 *      String, Map)
	 */
	public void evaluate(String scriptName, String script, Map<String, Object> context) {
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName(scriptName);
		SimpleScriptContext scriptContext = new SimpleScriptContext();
		// Creates the script context
		for (Map.Entry<String, Object> entry : context.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			scriptContext.setAttribute(name, value, ScriptContext.ENGINE_SCOPE);
		}
		try {
			// Evaluates the script
			scriptEngine.eval(script, scriptContext);
			// Converts back the context
			Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
			for (Map.Entry<String, Object> entry : bindings.entrySet()) {
				String name = entry.getKey();
				Object value = entry.getValue();
				context.put(name, value);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Cannot evaluate script " + script + " [" + scriptName + "] for context " + context, ex);
		}
	}

}
