/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.util.Locale;
import java.util.Map;

/**
 * Basic expression in the pattern
 * 
 * @version $Id: Expression.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public abstract class Expression {
	/**
	 * Sequence of expressions
	 */
	public final static int EXPR_SEQUENCE = 0;

	/**
	 * Token
	 */
	public final static int EXPR_TOKEN = 1;

	/**
	 * Value computed from a variable and a property
	 */
	public final static int EXPR_VALUE = 2;

	/**
	 * Value get from a bundle
	 */
	public final static int EXPR_KEY = 3;

	/**
	 * Locked
	 */
	private boolean locked = false;

	/**
	 * Type
	 */
	private int type;

	/**
	 * Constructor
	 * 
	 * @param aType
	 *            Expression type
	 */
	public Expression(int aType) {
		type = aType;
	}

	/**
	 * Locks the expression
	 */
	public void lock() {
		locked = true;
	}

	/**
	 * Lock state
	 * 
	 * @return Lock state
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Checks the lock state
	 */
	public void checkLock() {
		if (locked) {
			throw new IllegalStateException("The expression is locked");
		}
	}

	/**
	 * Type
	 * 
	 * @return Expression type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Evaluates the expression
	 * 
	 * @param locale
	 *            Locale to evaluate the expression for
	 * @param context
	 *            Variables context
	 * @return Expression result
	 */
	public abstract String evaluate(Locale locale, Map context);
}
