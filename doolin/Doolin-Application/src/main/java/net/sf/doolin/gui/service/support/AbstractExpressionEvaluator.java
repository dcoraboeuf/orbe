/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.util.Pair;

import net.sf.doolin.gui.service.ExpressionEvaluator;

public abstract class AbstractExpressionEvaluator implements ExpressionEvaluator {

	public String evaluate(String expression, Pair<String, Object>... variables) {
		HashMap<String, Object> context = new HashMap<String, Object>();
		for (Pair<String, Object> variable : variables) {
			context.put(variable.getFirstValue(), variable.getSecondValue());
		}
		return evaluate(expression, context);
	}

	public String evaluate(String expression, String variableName, Object variableValue) {
		Map<String, Object> context = Collections.singletonMap(variableName, variableValue);
		return evaluate(expression, context);
	}

}
