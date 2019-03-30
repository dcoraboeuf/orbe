/*
 * Created on Nov 7, 2006
 */
package net.sf.doolin.bus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default implementation for a bus.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultBus.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 */
public class DefaultBus extends Bus {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultBus.class);

	/**
	 * Registered processors
	 */
	private TreeSet<Subscription> subscriptions = new TreeSet<Subscription>();

	/**
	 * Queue of messages
	 */
	private LinkedList<Object> messageQueue = new LinkedList<Object>();

	@Override
	public synchronized <Message> void subscribe(Class<Message> messageClass, Subscriber<Message> subscriber) {
		subscribe(messageClass, subscriber, Priority.NORMAL);
	}

	@Override
	public synchronized <Message> void subscribe(Class<Message> messageClass, Subscriber<Message> subscriber, Priority priority) {
		Subscription<Message> subscription = new Subscription<Message>(messageClass, subscriber, priority);
		boolean ok = subscriptions.add(subscription);
		if (ok) {
			log.debug("Registration of " + subscriber + " for message " + messageClass.getName() + " with priority=" + priority);
		} else {
			log.warn("Cannot register " + subscriber + " for message " + messageClass.getName() + " with priority=" + priority);
		}
	}

	@Override
	public <Message> Collection<Subscriber<Message>> getSubscribers(Class<Message> messageClass) {
		ArrayList<Subscriber<Message>> subscribers = new ArrayList<Subscriber<Message>>();
		for (Subscription<?> subscription : subscriptions) {
			if (subscription.accept(messageClass)) {
				@SuppressWarnings("unchecked")
				Subscriber<Message> subscriber = (Subscriber<Message>) subscription.getSubscriber();
				subscribers.add(subscriber);
			}
		}
		return subscribers;
	}

	@Override
	public <Message> void publish(Message message) {
		log.debug("Message published : " + getTrace(message));
		if (messageQueue.isEmpty()) {
			process(message);
		} else {
			messageQueue.addLast(message);
			processContinue();
		}
	}

	/**
	 * Get a string representation of a message
	 * 
	 * @param message
	 *            Message to display
	 * @return String representation
	 */
	public String getTrace(Object message) {
		return message.getClass().getName() + " (" + message + ")";
	}

	/**
	 * Continue the processing
	 */
	protected void processContinue() {
		if (!messageQueue.isEmpty()) {
			Object message = messageQueue.removeFirst();
			process(message);
		}
	}

	/**
	 * Process a message
	 * 
	 * @param message
	 *            Message to process
	 * @param <Message>
	 *            Message type
	 */
	protected <Message> void process(Message message) {
		log.debug("Message processed : " + getTrace(message));
		// Class of the message
		Class<? extends Object> messageClass = message.getClass();
		// Loop over registered processors
		Object[] processors = subscriptions.toArray();
		if (processors.length == 0) {
			log.warn("There is no registered subscripter for " + getTrace(message));
		} else {
			boolean ok = true;
			for (int i = 0; ok && i < processors.length; i++) {
				Subscription<?> subscription = (Subscription<?>) processors[i];
				if (subscription.accept(messageClass)) {
					@SuppressWarnings("unchecked")
					Subscriber<Message> subscriber = (Subscriber<Message>) subscription.getSubscriber();
					log.debug("Message " + getTrace(message) + " processed by " + subscriber);
					try {
						subscriber.receive(message);
					} catch (Exception ex) {
						log.error("Unhandled exception", ex);
						ok = false;
					}
				}
			}
		}
		// End of the message
		log.debug("Message " + getTrace(message) + " finished.");
		// Continue with other messages
		processContinue();
	}

	@Override
	public synchronized void unsubscribe(Subscriber subscriber) {
		Iterator<Subscription> iterator = subscriptions.iterator();
		while (iterator.hasNext()) {
			Subscription subscription = iterator.next();
			if (subscription.getSubscriber() == subscriber) {
				iterator.remove();
			}
		}
	}

}
