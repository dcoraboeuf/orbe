/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.oxml.adapter;

import org.w3c.dom.Element;

import net.sf.doolin.oxml.OXMLContext;

/**
 * Creates an instance from the current node.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * @param <T>
 *            Type of the instance to create
 */
public abstract class AbstractOXMLInstanceFactory<T> implements OXMLInstanceFactory<T> {

	public T createInstance(OXMLContext context) {
		// Gets the current node as an element
		Element element = (Element) context.nodePeek();
		// Conversion
		return createInstance(element, context);
	}

	/**
	 * Creates the instance from the current element
	 * 
	 * @param element
	 *            Current element
	 * @param context
	 *            Execution context
	 * @return Instance
	 */
	protected abstract T createInstance(Element element, OXMLContext context);

}
