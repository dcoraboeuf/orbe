/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.xml.DOMUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <code>if</code> action. This action evaluates an XPath expression as a
 * boolean in order to execute or not all the inner actions.
 * <p>
 * Parameters of this action are:
 * <ul>
 * <li><code>test</code> - (required) XPath expression evaluated as a
 * boolean. It is relative to the current node.
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class IfOXMLAction extends AbstractSequenceOXMLAction {

	private String test;

	@Override
	public void process(OXMLContext context) {
		// Gets the current node
		Node node = context.nodePeek();
		// Evaluates the test expression
		boolean ok = context.getXpath().evaluateBoolean(node, test);
		// If ok, goes on
		if (ok) {
			super.process(context);
		}
	}

	@Override
	public void parse(Element e) throws IOException {
		super.parse(e);
		test = DOMUtils.getAttribute(e, "test", true, null);
	}

}
