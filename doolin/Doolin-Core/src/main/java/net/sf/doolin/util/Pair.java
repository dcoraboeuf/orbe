/*
 * Created on 27 avr. 2006
 */
package net.sf.doolin.util;

import org.apache.commons.lang.ObjectUtils;

/**
 * Association of two objects.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Pair.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 * @param <A>
 *            Type of the first element of the pair
 * @param <B>
 *            Type of the second element of the pair
 */
public class Pair<A, B> {
	private A firstValue;

	private B secondValue;

	/**
	 * Constructor with no values.
	 * 
	 */
	public Pair() {
	}

	/**
	 * Constructor with two values.
	 * 
	 * @param a
	 *            First value
	 * @param b
	 *            Second value
	 */
	public Pair(A a, B b) {
		firstValue = a;
		secondValue = b;
	}

	/**
	 * @return Returns the firstValue.
	 */
	public A getFirstValue() {
		return firstValue;
	}

	/**
	 * @param firstValue
	 *            The firstValue to set.
	 */
	public void setFirstValue(A firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return Returns the secondValue.
	 */
	public B getSecondValue() {
		return secondValue;
	}

	/**
	 * @param secondValue
	 *            The secondValue to set.
	 */
	public void setSecondValue(B secondValue) {
		this.secondValue = secondValue;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.hashCode(firstValue) * ObjectUtils.hashCode(secondValue);
	}

	@Override
	public String toString() {
		return firstValue + "," + secondValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Pair) {
			Pair item = (Pair) obj;
			return ObjectUtils.equals(this.firstValue, item.firstValue) && ObjectUtils.equals(this.secondValue, item.secondValue);
		} else {
			return false;
		}
	}
}
