/*
 * Created on 8 janv. 2005
 * $Id: Buffer.java,v 1.2 2007/07/31 15:32:47 guinnessman Exp $
 */
package net.sf.doolin.util.expression;

/**
 * Expression subclass that contains a growing sequence of characters.
 * 
 * @version $Revision: 1.2 $
 * @author Damien Coraboeuf
 */
public abstract class Buffer extends Expression {
	/**
	 * Text
	 */
	private StringBuffer buffer = new StringBuffer();

	/**
	 * Constructor
	 * 
	 * @param type
	 *            Expression type
	 */
	public Buffer(int type) {
		super(type);
	}

	/**
	 * Adds a character
	 * 
	 * @param c
	 *            Character to add
	 */
	public void add(char c) {
		checkLock();
		buffer.append(c);
	}

	/**
	 * Adds a string
	 * 
	 * @param s
	 *            String to add
	 */
	public void add(String s) {
		checkLock();
		buffer.append(s);
	}

	/**
	 * Get the value
	 * 
	 * @return Value of the buffer
	 */
	public String getValue() {
		return buffer.toString();
	}
}
