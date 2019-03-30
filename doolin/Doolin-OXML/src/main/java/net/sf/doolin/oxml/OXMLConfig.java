/*
 * Created on Sep 16, 2007
 */
package net.sf.doolin.oxml;

import net.sf.doolin.oxml.action.OXMLAction;

/**
 * OXML configuration definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface OXMLConfig {

	/**
	 * Returns the root action for this configuration.
	 * 
	 * @return Action
	 */
	OXMLAction getRootAction();

}
