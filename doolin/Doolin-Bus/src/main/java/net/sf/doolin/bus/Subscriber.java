/*
 * Created on Nov 7, 2006
 */
package net.sf.doolin.bus;

/**
 * Listens to specific message classes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Subscriber.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 * @param <Message>
 *            Message type
 */
public interface Subscriber<Message> {

	/**
	 * This method called whenever a message is received.
	 * 
	 * @param message
	 *            Message to handle
	 */
	void receive(Message message);

}
