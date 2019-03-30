/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.bus.support;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class associates a Bus message type to a method name to be called on a
 * target object. It is used by the
 * <code>{@linkg DelegateGUISubscriber}</code> Bus subscriber.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MessageHandler.java,v 1.1 2007/08/14 11:49:00 guinnessman Exp $
 */
public class MessageHandler {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(MessageHandler.class);

	private Class messageType;

	private String method;

	/**
	 * @return Type of message to subscribe to.
	 */
	public Class getMessage() {
		return messageType;
	}

	/**
	 * @param message
	 *            Type of message to subscribe to.
	 */
	public void setMessage(Class message) {
		this.messageType = message;
	}

	/**
	 * @return Method to call on the target object.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 
	 * @param method
	 *            Method to call on the target object.
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Calls the method on the given target object.
	 * 
	 * @param target
	 *            Target object to call.
	 * @param message
	 *            Message to pass as a parameter.
	 */
	public void delegate(Object target, Object message) {
		try {
			MethodUtils.invokeMethod(target, method, new Object[] { message });
		} catch (Exception ex) {
			log.error("Cannot invoke method " + method + " on " + target.getClass().getName(), ex);
		}
	}

}
