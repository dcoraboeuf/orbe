/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Allows the computing of a string from a combination of patterns
 * 
 * @version $Id: ExpressionString.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class ExpressionString {
	/**
	 * Pattern
	 */
	private String pattern;

	/**
	 * Context
	 */
	private HashMap<String, Object> context = new HashMap<String, Object>();

	/**
	 * Root expression
	 */
	private Expression expression;

	/**
	 * Parsing
	 * 
	 * @param p
	 *            Pattern to be used
	 * @throws ParseException
	 *             If the pattern cannot be parsed
	 */
	public ExpressionString(String p) throws ParseException {
		pattern = p;
		parse();
	}

	/**
	 * Parse the pattern
	 * 
	 * @throws ParseException
	 *             If a parsing error occurs
	 */
	protected void parse() throws ParseException {
		expression = ExpressionParser.parse(pattern);
	}

	/**
	 * Adds an element into the context
	 * 
	 * @param key
	 *            Key for the variable
	 * @param data
	 *            Variable value
	 */
	public void putContext(String key, Object data) {
		context.put(key, data);
	}

	/**
	 * Adds another context
	 * 
	 * @param data
	 *            Context to add
	 */
	public void putContext(Map<String, ? extends Object> data) {
		context.putAll(data);
	}

	/**
	 * Computes the string
	 * 
	 * @param locale
	 *            Locale to evaluate the expression for
	 * @return Expression result
	 */
	public String compute(Locale locale) {
		return expression.evaluate(locale, context);
	}
}
