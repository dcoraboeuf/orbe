/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.adapter;

import net.sf.doolin.oxml.OXMLContext;

/**
 * Takes the node value as a String.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class DefaultOXMLAdapter extends AbstractSimpleOXMLAdapter<String> {

	@Override
	protected String adapt(String value, OXMLContext context) {
		return value;
	}

}
