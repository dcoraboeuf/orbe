/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import net.sf.doolin.util.Strings;

/**
 * Key to a string, with some parameters
 * 
 * @version $Id: Key.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class Key extends Buffer {
	/**
	 * Parameters
	 */
	private ArrayList<Expression> params = null;

	/**
	 * Constructor
	 */
	public Key() {
		super(EXPR_KEY);
	}

	/**
	 * Prepares for parameters
	 */
	public void prepareForParams() {
		params = new ArrayList<Expression>();
	}

	/**
	 * Is it prepared for parameters
	 * 
	 * @return <code>true</code> if the key expression contains some
	 *         parameters
	 */
	public boolean isPreparedForParams() {
		return params != null;
	}

	/**
	 * Adds a parameter
	 * 
	 * @param ex
	 *            Parameter expression
	 */
	public void addParam(Expression ex) {
		params.add(ex);
	}

	/**
	 * String representation
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("#").append(getValue());
		if (params != null) {
			buffer.append("(");
			int index = 0;
			for (Iterator i = params.iterator(); i.hasNext();) {
				Expression ex = (Expression) i.next();
				if (index > 0) {
					buffer.append(",");
				}
				buffer.append(ex);
				index++;
			}
			buffer.append(")");
		}
		return buffer.toString();
	}

	/**
	 * @see com.mandragor.utils.pattern.Expression#evaluate(java.util.Map)
	 */
	public String evaluate(Locale locale, Map context) {
		String code = getValue();
		// Parameters
		Object[] paramValues = null;
		if (params != null) {
			paramValues = new Object[params.size()];
			for (int i = 0; i < paramValues.length; i++) {
				Expression ex = params.get(i);
				paramValues[i] = ex.evaluate(locale, context);
			}
		}
		// Ok
		return Strings.get(locale, code, paramValues);
	}
}
