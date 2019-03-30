/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

import java.util.Map;

import net.sf.doolin.util.Pair;

public interface ExpressionEvaluator {

	String evaluate(String expression, String variableName, Object variableValue);

	String evaluate(String expression, Pair<String, Object>... variables);
	
	String evaluate(String expression, Map<String, Object> context);

}
