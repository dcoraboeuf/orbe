/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

/**
 * Message with a parameter.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeSimpleMessage.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public abstract class OrbeSimpleMessage<T> extends OrbeMessage {

	private T value;

	public OrbeSimpleMessage(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
