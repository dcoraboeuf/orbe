/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Sequence of expressions
 * 
 * @version $Id: Sequence.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class Sequence extends Expression {
	/**
	 * List of expressions
	 */
	private LinkedList<Expression> expressions = new LinkedList<Expression>();

	/**
	 * Constructor
	 */
	public Sequence() {
		super(EXPR_SEQUENCE);
	}

	/**
	 * Adds an expression to the sequence
	 * 
	 * @param expression
	 *            Expression to add
	 */
	public void add(Expression expression) {
		checkLock();
		if (!expressions.isEmpty()) {
			Expression last = expressions.getLast();
			last.lock();
		}
		expressions.add(expression);
		expression.lock();
	}

	/**
	 * String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = expressions.iterator(); i.hasNext();) {
			Expression ex = (Expression) i.next();
			buffer.append(ex);
		}
		return buffer.toString();
	}

	/**
	 * @see com.mandragor.utils.pattern.Expression#evaluate(java.util.Locale,
	 *      java.util.Map)
	 */
	public String evaluate(Locale locale, Map context) {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = expressions.iterator(); i.hasNext();) {
			Expression ex = (Expression) i.next();
			String value = ex.evaluate(locale, context);
			if (StringUtils.isNotBlank(value)) {
				buffer.append(value);
			}
		}
		return buffer.toString();
	}

}
