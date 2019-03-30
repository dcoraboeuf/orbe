/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.xml.DOMUtils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * <code>for</code> action. This actions loops over a list of node and
 * evaluates all the inner actions for each of the nodes. For the inner actions,
 * the current node becomes the current node in the loop. The <code>for</code>
 * action is actually the only way to change the current node.
 * <p>
 * Parameters for this action are:
 * <ul>
 * <li><code>node</code> - (required) XPath evaluated from the current node,
 * which gives a list of nodes
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ForOXMLAction extends AbstractSequenceOXMLAction {

	private String node;

	@Override
	public void process(OXMLContext context) {
		// Evaluates the XPath
		NodeList nodes = context.getNodeList(node);
		int nodeCount = nodes.getLength();
		for (int i = 0; i < nodeCount; i++) {
			Node forNode = nodes.item(i);
			context.nodePush(forNode);
			try {
				super.process(context);
			} finally {
				context.nodePop();
			}
		}
	}

	@Override
	public void parse(Element e) throws IOException {
		super.parse(e);
		node = DOMUtils.getAttribute(e, "node", true, null);
	}

}
