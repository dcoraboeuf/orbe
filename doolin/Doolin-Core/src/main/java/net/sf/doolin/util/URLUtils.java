/*
 * Created on 21 janv. 2006
 */
package net.sf.doolin.util;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;

/**
 * Utilities for URLs
 * 
 * @author Damien Coraboeuf
 * @version $Id: URLUtils.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public class URLUtils {

	/**
	 * Global stream handler factory
	 */
	protected static class GlobalURLStreamHandlerFactory implements URLStreamHandlerFactory {

		/**
		 * Stream handlers indexed by protocols
		 */
		private HashMap<String, URLStreamHandler> streamHandlers = new HashMap<String, URLStreamHandler>();

		/**
		 * @see java.net.URLStreamHandlerFactory#createURLStreamHandler(java.lang.String)
		 */
		public URLStreamHandler createURLStreamHandler(String protocol) {
			URLStreamHandler streamHandler = streamHandlers.get(protocol);
			return streamHandler;
		}

		/**
		 * Registers a new stream handler
		 * 
		 * @param protocol
		 *            Protocol to register the handler for
		 * @param streamHandler
		 *            Handler associated with this protocol
		 */
		public void registerStreamHandler(String protocol, URLStreamHandler streamHandler) {
			streamHandlers.put(protocol, streamHandler);
		}

	}

	private static GlobalURLStreamHandlerFactory globalURLStreamHandlerFactory;

	/**
	 * Registers a new stream handler for a given protocol.
	 * 
	 * @param protocol
	 *            Protocol to register the handler for
	 * @param handler
	 *            Handler associated with this protocol
	 */
	public static void registerURLStreamHandler(String protocol, URLStreamHandler handler) {
		init();
		globalURLStreamHandlerFactory.registerStreamHandler(protocol, handler);
	}

	/**
	 * Initialization of the utility class. It registers a global URL stream
	 * handler, which in turn is able to manage the registration of
	 * supplementary handlers for different protocols.
	 */
	protected static synchronized void init() {
		if (globalURLStreamHandlerFactory == null) {
			GlobalURLStreamHandlerFactory g = new GlobalURLStreamHandlerFactory();
			URL.setURLStreamHandlerFactory(g);
			globalURLStreamHandlerFactory = g;
		}
	}

}
