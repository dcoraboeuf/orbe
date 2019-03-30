/*
 * Created on 28 avr. 2006
 */
package net.sf.doolin.util;

/**
 * Handles a single value.
 * 
 * @param <T>
 *            Type of the handled value.
 * @author coraboeuf
 * @version $Id: ValueHandler.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public class ValueHandler<T> {
	/**
	 * Value
	 */
	private T value;

	/**
	 * @return Handled value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value
	 *            Handled value
	 */
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
