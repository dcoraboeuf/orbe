/*
 * Created on Nov 7, 2006
 */
package net.sf.doolin.bus;

/**
 * Just a message with a parameter.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SimpleMessage.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 * @param <T>
 *            Message parameter type
 */
public class SimpleMessage<T> {

	private T parameter;

	/**
	 * Constructor with a parameter
	 * 
	 * @param parameter
	 *            Message parameter
	 */
	public SimpleMessage(T parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return Message parameter
	 */
	public T getParameter() {
		return parameter;
	}

	@Override
	public String toString() {
		return String.valueOf(parameter);
	}

}
