/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.xml.DOMUtils;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <code>check</code> action. This action checks if a given value is equal to
 * the expected one.
 * <p>
 * Parameters of this action are:
 * <ul>
 * <li><code>node</code> - (required) XPath to the node to evaluate, relative
 * to the current node
 * <li><code>value</code> - (required) Expected value
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class CheckOXMLAction extends AbstractOXMLAction {

	private String node;

	private String value;

	public void parse(Element e) throws IOException {
		node = DOMUtils.getAttribute(e, "node", true, null);
		value = DOMUtils.getAttribute(e, "value", true, null);
	}

	public void process(OXMLContext context) {
		// Current node
		Node currentNode = context.nodePeek();
		// Evaluates the string
		String string = context.getXpath().evaluateString(currentNode, node);
		// Comparizon
		if (!StringUtils.equals(string, value)) {
			throw new RuntimeException(DOMUtils.getXPath(currentNode) + "/" + node + " should have value " + value + " but has " + string);
		}
	}

}
