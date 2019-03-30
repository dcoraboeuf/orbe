/*
 * Created on 16 oct. 2005
 */
package net.sf.doolin.gui.core.action;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Placeholder for the <code>action</code> protocol.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ActionURLStreamHandler extends URLStreamHandler {

	/**
	 * Action protocol
	 */
	public static final String PROTOCOL_ACTION = "action";

	/**
	 * @see java.net.URLStreamHandler#openConnection(java.net.URL)
	 */
	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return null;
	}

}
