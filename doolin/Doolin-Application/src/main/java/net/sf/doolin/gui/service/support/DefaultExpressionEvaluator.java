/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import java.text.ParseException;
import java.util.Map;

import net.sf.doolin.util.expression.Expression;
import net.sf.doolin.util.expression.ExpressionParser;

import net.sf.doolin.gui.service.GUIStrings;

public class DefaultExpressionEvaluator extends AbstractExpressionEvaluator {

	public String evaluate(String expression, Map<String, Object> context) {
		try {
			Expression exp = ExpressionParser.parse(expression);
			String value = exp.evaluate(GUIStrings.getLocale(), context);
			return value;
		} catch (ParseException ex) {
			throw new RuntimeException("Cannot evaluate expression " + expression + " with " + context, ex);
		}
	}

}
