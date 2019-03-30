package net.sf.doolin.bus;

import java.lang.ref.WeakReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Message processor registration
 * 
 * @param <Message>
 *            Message type
 */
public class Subscription<Message> extends WeakReference<Subscriber<Message>> implements Comparable<Subscription<Message>> {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Subscription.class);

	/**
	 * Class of message
	 */
	private Class<Message> messageClass;

	/**
	 * Associated message processor
	 */
	private Subscriber<Message> subscriber;

	/**
	 * Priority
	 */
	private Priority priority;

	/**
	 * Constructor
	 * 
	 * @param messageClass
	 *            Message type
	 * @param subscriber
	 *            Associated subscriber
	 */
	public Subscription(Class<Message> messageClass, Subscriber<Message> subscriber) {
		this(messageClass, subscriber, Priority.NORMAL);
	}

	/**
	 * Constructor
	 * 
	 * @param messageClass
	 *            Message type
	 * @param subscriber
	 *            Associated subscriber
	 * @param priority
	 *            Priority for the message
	 */
	public Subscription(Class<Message> messageClass, Subscriber<Message> subscriber, Priority priority) {
		super(subscriber);
		this.messageClass = messageClass;
		this.subscriber = subscriber;
		this.priority = priority;
	}

	/**
	 * Comparison is based on the priority. But two priorities but not been
	 * equals.
	 */
	public int compareTo(Subscription<Message> o) {
		if (o.priority == this.priority) {
			return 1;
		} else {
			/*
			 * THIS has more priority than O if its priority is hight
			 */
			return (o.priority.compareTo(this.priority));
		}
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {
		Subscription registration = (Subscription) o;
		boolean ok = this.subscriber == registration.subscriber && this.messageClass.getName().equals(registration.messageClass.getName());
		if (log.isTraceEnabled()) {
			log.trace("equals " + ok + " for " + o + " and " + this);
		}
		return ok;
	}

	/**
	 * Returns the priority.
	 * 
	 * @return int
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return subscriber.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return subscriber.hashCode();
	}

	/**
	 * @return Message type
	 */
	public Class<Message> getMessageClass() {
		return messageClass;
	}

	/**
	 * @return Subscriber
	 */
	public Subscriber<Message> getSubscriber() {
		return subscriber;
	}

	/**
	 * Checks if this subscription accepts a message
	 * 
	 * @param msgClass
	 *            Message class to test
	 * @return Result of the test
	 */
	public boolean accept(Class<?> msgClass) {
		return (this.messageClass.isAssignableFrom(msgClass));
	}

}