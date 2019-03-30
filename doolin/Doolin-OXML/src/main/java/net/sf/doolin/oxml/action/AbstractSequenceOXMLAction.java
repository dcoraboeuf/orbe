/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.oxml.OXML;
import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.xml.DOMUtils;

import org.w3c.dom.Element;

/**
 * Action that contains a sequence of OXML actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractSequenceOXMLAction extends AbstractOXMLAction {

	private List<OXMLAction> actionList = new ArrayList<OXMLAction>();

	/**
	 * Processes each sub action.
	 * 
	 * @see net.sf.doolin.oxml.action.OXMLAction#process(net.sf.doolin.oxml.OXMLContext)
	 */
	public void process(OXMLContext context) {
		for (OXMLAction action : actionList) {
			action.process(context);
		}
	}

	/**
	 * Parses each child.
	 * 
	 * @see net.sf.doolin.oxml.action.OXMLAction#parse(org.w3c.dom.Element)
	 */
	public void parse(Element e) throws IOException {
		List<Element> childList = DOMUtils.getElements(e);
		for (Element eChild : childList) {
			String childNS = eChild.getNamespaceURI();
			if (OXML.NS.equals(childNS)) {
				// Get the node name
				String childName = eChild.getLocalName();
				// Creates the action
				OXMLAction action = OXMLActionFactory.getInstance().createAction(childName);
				// Configures the action
				action.parse(eChild);
				// Registers the action
				actionList.add(action);
			} else {
				throw new IOException("Unexpected element in the configuration at " + DOMUtils.getXPath(eChild));
			}
		}
	}
}
