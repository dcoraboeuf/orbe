/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.adapter;

import net.sf.doolin.oxml.OXMLContext;

import org.w3c.dom.Node;

/**
 * Adapter that converts the string value to an object.
 * 
 * @param <T>
 *            Expected returned type
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractSimpleOXMLAdapter<T> implements OXMLAdapter {

	public Object adapt(Node currentNode, String path, OXMLContext context) {
		String value = context.getXpath().evaluateString(currentNode, path);
		return adapt(value, context);
	}

	/**
	 * Adapts a simple string to an object.
	 * 
	 * @param value
	 *            String to adapt
	 * @param context
	 *            Execution context
	 * @return Created instance
	 */
	protected abstract T adapt(String value, OXMLContext context);

}
