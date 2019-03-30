/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.adapter;

import org.w3c.dom.Node;

import net.sf.doolin.oxml.OXMLContext;

/**
 * This interface defines an object which adapts a node (given a parent node and
 * an XPath expression) to an object suitable for the target object model.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface OXMLAdapter {

	/**
	 * Adapts a value taken from the XML to a value suitable in the object
	 * model.
	 * 
	 * @param currentNode
	 *            Current node
	 * @param path
	 *            XPath from the current node
	 * @param context
	 *            Execution context
	 * @return Object value
	 */
	Object adapt(Node currentNode, String path, OXMLContext context);

}
