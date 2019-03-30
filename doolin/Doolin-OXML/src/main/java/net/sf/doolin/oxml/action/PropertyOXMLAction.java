/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import net.sf.doolin.oxml.OXML;
import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.OXMLFactory;
import net.sf.doolin.oxml.adapter.DefaultOXMLAdapter;
import net.sf.doolin.oxml.adapter.OXMLAdapter;
import net.sf.doolin.util.xml.DOMUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <code>property</code> action.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class PropertyOXMLAction extends AbstractOXMLAction {

	private String node;

	private String name;

	private OXMLAdapter adapter = new DefaultOXMLAdapter();

	public void parse(Element e) throws IOException {
		name = DOMUtils.getAttribute(e, "name", true, null);
		node = DOMUtils.getAttribute(e, "node", false, null);
		//
		if (StringUtils.isBlank(node)) {
			node = StringUtils.replace(name, ".", "/");
		}
		// Adapter
		Element eAdapter = DOMUtils.getElement(e, OXML.NS, "adapter");
		if (eAdapter != null) {
			adapter = OXMLFactory.getInstance().create(eAdapter);
		}
	}

	public void process(OXMLContext context) {
		// Get the current node
		Node currentNode = context.nodePeek();
		// Adapter
		Object propertyValue = adapter.adapt (currentNode, node, context);
		// Gets the current instance
		Object instance = context.instancePeek();
		// Sets the property
		try {
			BeanUtils.setProperty(instance, name, propertyValue);
		} catch (Exception ex) {
			throw new RuntimeException("Cannot set property " + name, ex);
		}
	}

}
