/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.util.Locale;
import java.util.Map;

/**
 * Fixed text
 * 
 * @version $Id: Token.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class Token extends Buffer {
	/**
	 * Constructor
	 */
	public Token() {
		super(EXPR_TOKEN);
	}

	/**
	 * Constructor from a predefined value. The token is considered locked and
	 * unmodifiable after that.
	 * 
	 * @param value
	 *            Initial value of the token
	 */
	public Token(String value) {
		super(EXPR_TOKEN);
		add(value);
		lock();
	}

	/**
	 * String
	 */
	public String toString() {
		return getValue();
	}

	/**
	 * @see com.mandragor.utils.pattern.Expression#evaluate(java.util.Map)
	 */
	public String evaluate(Locale locale, Map context) {
		return getValue();
	}
}
