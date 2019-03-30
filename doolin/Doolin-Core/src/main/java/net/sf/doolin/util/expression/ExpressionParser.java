/*
 * Created on 8 janv. 2005
 */
package net.sf.doolin.util.expression;

import java.text.ParseException;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

/**
 * @version $Id: ExpressionParser.java,v 1.3 2007/07/31 15:32:47 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class ExpressionParser {

	/**
	 * Parsing
	 * 
	 * @param pattern
	 *            Pattern to parse
	 * @return Parsed expression
	 * @throws ParseException
	 *             If a parsing error occurs
	 */
	public static Expression parse(String pattern) throws ParseException {
		if (StringUtils.isBlank(pattern)) {
			return new Token("");
		}
		Stack<Expression> stack = new Stack<Expression>();
		stack.push(new Sequence());
		int index = 0;
		boolean escaping = false;
		int length = pattern.length();
		while (index < length) {
			Expression current = stack.peek();
			int type = current.getType();
			char c = pattern.charAt(index);
			switch (type) {
			/*
			 * SEQUENCE
			 */
			case Expression.EXPR_SEQUENCE: {
				// Sequence sequence = (Sequence) current;
				if (c == '#' && !escaping) {
					stack.push(new Key());
					index++;
				} else if (c == '$' && !escaping) {
					stack.push(new Value());
					index++;
				} else {
					stack.push(new Token());
				}
			}
				break;
			/*
			 * TOKEN
			 */
			case Expression.EXPR_TOKEN: {
				Token token = (Token) current;
				if (c == '#' && !escaping) {
					pop(stack, index);
					stack.push(new Key());
					index++;
				} else if (c == '$' && !escaping) {
					pop(stack, index);
					stack.push(new Value());
					index++;
				} else if (c == ',' && !escaping) {
					pop(stack, index);
					index++;
				} else if (c == '\\') {
					index++;
					if (escaping) {
						escaping = false;
						token.add(c);
					} else {
						escaping = true;
					}
				} else {
					escaping = false;
					token.add(c);
					index++;
				}
			}
				break;
			/*
			 * KEY
			 */
			case Expression.EXPR_KEY: {
				Key key = (Key) current;
				/**
				 * Parameters are about to be filled
				 */
				if (key.isPreparedForParams()) {
					if (c == ')' && !escaping) {
						pop(stack, index);
						index++;
					} else if (c == '#' && !escaping) {
						stack.push(new Key());
						index++;
					} else if (c == '$' && !escaping) {
						stack.push(new Value());
						index++;
					} else if (Character.isWhitespace(c) && !escaping) {
						index++; // Just ignore it
					} else {
						escaping = false;
						stack.push(new Token());
					}
				}
				/*
				 * No parameters for the moment
				 */
				else {
					if (c == '(' && !escaping) {
						key.prepareForParams();
						index++;
					} else if (c == ')' && !escaping) {
						pop(stack, index);
						index++;
					} else if (c == ',' && !escaping) {
						pop(stack, index);
						index++;
					} else if (Character.isWhitespace(c) && !escaping) {
						pop(stack, index);
					} else if (c == '.' && !escaping) {
						key.add(c);
						index++;
					} else if (!Character.isJavaIdentifierPart(c) && !escaping) {
						pop(stack, index);
					} else {
						escaping = false;
						key.add(c);
						index++;
					}
				}
			}
				break;
			/*
			 * VALUE
			 */
			case Expression.EXPR_VALUE: {
				Value value = (Value) current;
				if (Character.isWhitespace(c)) {
					pop(stack, index);
				} else if (c == ',' && !escaping) {
					pop(stack, index);
					index++;
				} else if (c == ')' && !escaping) {
					pop(stack, index);
					stack.push(new Token());
				} else if (c == '#' && !escaping) {
					pop(stack, index);
					stack.push(new Key());
					index++;
				} else if (c == '$' && !escaping) {
					pop(stack, index);
					stack.push(new Value());
					index++;
				} else if (c == '.' && !escaping) {
					value.add(c);
					index++;
				} else if (!Character.isJavaIdentifierPart(c) && !escaping) {
					pop(stack, index);
				} else {
					escaping = false;
					value.add(c);
					index++;
				}
			}
				break;
			}
		}
		// End character, unstack everything
		while (stack.size() > 1) {
			pop(stack, length);
		}
		// Ok
		return stack.pop();
	}

	/**
	 * Pop the key from current stack
	 */
	private static void pop(Stack stack, int index) throws ParseException {
		Expression expression = (Expression) stack.pop();
		expression.lock();
		Expression current = (Expression) stack.peek();
		int type = current.getType();
		switch (type) {
		case Expression.EXPR_SEQUENCE: {
			Sequence sequence = (Sequence) current;
			sequence.add(expression);
		}
			break;
		case Expression.EXPR_KEY: {
			Key key = (Key) current;
			key.addParam(expression);
		}
			break;
		default:
			throw new ParseException("Expression " + current.getClass().getName() + " cannot accept a sub expression", index);
		}
	}
}
