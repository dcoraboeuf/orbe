/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.bus.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.bus.GUISubscriber;

/**
 * This subscriber delegates subscriptions to a target object, associating
 * messages to methods on the target object.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DelegateGUISubscriber.java,v 1.1 2007/08/14 11:49:00
 *          guinnessman Exp $
 * @param <T>
 *            Type of component that is registered.
 */
public class DelegateGUISubscriber<T> implements GUISubscriber<T> {

	private GUISubscriber<T> target;

	private List<MessageHandler> handlers = new ArrayList<MessageHandler>();

	/**
	 * Delegates the registration to the target object and registers each
	 * message handler to the bus.
	 * 
	 * @see #getTarget()
	 * @see #registerHandler(T, MessageHandler)
	 * @see net.sf.doolin.gui.bus.GUISubscriber#register(java.lang.Object)
	 */
	public void register(T component) {
		target.register(component);
		for (MessageHandler handler : handlers) {
			registerHandler(component, handler);
		}
	}

	@SuppressWarnings("unchecked")
	private void registerHandler(final T component, final MessageHandler handler) {
		final Class message = handler.getMessage();
		Bus.get().subscribe(message, new Subscriber() {

			public void receive(Object message) {
				handler.delegate(target, message);
			}

		});
	}

	/**
	 * Returns the target object.
	 * 
	 * @return Target object.
	 */
	public GUISubscriber<T> getTarget() {
		return target;
	}

	/**
	 * Sets the target object
	 * 
	 * @param target
	 *            Target object
	 */
	@Configurable(mandatory = true, description = "Target for the methods")
	public void setTarget(GUISubscriber<T> target) {
		this.target = target;
	}

	/**
	 * Returns the message handlers
	 * 
	 * @return Message handlers
	 */
	public List<MessageHandler> getHandlers() {
		return handlers;
	}

	/**
	 * Sets the message handlers
	 * 
	 * @param handlers
	 *            Message handlers
	 */
	@Configurable(mandatory = false, description = "Associations between messages and methods to call on the target")
	public void setHandlers(List<MessageHandler> handlers) {
		this.handlers = handlers;
	}

}
