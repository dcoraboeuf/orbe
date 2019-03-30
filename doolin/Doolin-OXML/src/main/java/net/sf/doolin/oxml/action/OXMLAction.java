/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import org.w3c.dom.Element;

import net.sf.doolin.oxml.OXMLContext;

/**
 * Action to be executed against a context.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface OXMLAction {

	/**
	 * Processes the action against a context
	 * 
	 * @param context
	 *            Execution context
	 */
	void process(OXMLContext context);

	/**
	 * Parses the action from an XML configuration.
	 * 
	 * @param e
	 *            Configuration element for this action.
	 * @throws IOException
	 *             If the element cannot be parsed
	 */
	void parse(Element e) throws IOException;

}
