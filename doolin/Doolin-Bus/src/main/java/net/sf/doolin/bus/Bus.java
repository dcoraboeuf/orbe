/*
 * Created on Nov 7, 2006
 */
package net.sf.doolin.bus;

import java.util.Collection;

/**
 * Definition for an event bus.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Bus.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 */
public abstract class Bus {

	/**
	 * Default bus
	 */
	private static Bus defaultBus = new DefaultBus();

	/**
	 * Get the default bus
	 * 
	 * @return Default bus
	 */
	public static Bus get() {
		return defaultBus;
	}
	
	/**
	 * Sets a new default implementation
	 * 
	 * @param bus
	 *            New bus implementation
	 */
	public static void setDefault(Bus bus) {
		defaultBus = bus;
	}

	/**
	 * Subscribes to a message class.
	 * 
	 * @param <Message>
	 *            Message type
	 * @param messageClass
	 *            Class of the message to subscribe to.
	 * @param subscriber
	 *            Subscriber
	 */
	public abstract <Message> void subscribe(Class<Message> messageClass, Subscriber<Message> subscriber);

	/**
	 * Subscribes to a message class.
	 * 
	 * @param <Message>
	 *            Message type
	 * @param messageClass
	 *            Class of the message to subscribe to.
	 * @param subscriber
	 *            Subscriber
	 * @param priority
	 *            Level of priority (high priorities are handled first)
	 */
	public abstract <Message> void subscribe(Class<Message> messageClass, Subscriber<Message> subscriber, Priority priority);

	/**
	 * List of subscribers for a message class.
	 * 
	 * @param <Message>
	 *            Message class.
	 * @param messageClass
	 *            Message class.
	 * @return List of subscribers, never <code>null</code> but can be empty.
	 */
	public abstract <Message> Collection<Subscriber<Message>> getSubscribers(Class<Message> messageClass);

	/**
	 * Publishes a message
	 * 
	 * @param <Message>
	 *            Message type
	 * @param message
	 *            Message.
	 */
	public abstract <Message> void publish(Message message);

	/**
	 * Unregister a subscriber.
	 * 
	 * @param subscriber
	 *            Subscriber to unregister.
	 */
	public abstract void unsubscribe(Subscriber subscriber);

}
