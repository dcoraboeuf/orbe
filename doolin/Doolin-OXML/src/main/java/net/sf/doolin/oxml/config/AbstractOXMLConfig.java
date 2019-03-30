/*
 * Created on Sep 16, 2007
 */
package net.sf.doolin.oxml.config;

import net.sf.doolin.oxml.OXMLConfig;
import net.sf.doolin.oxml.action.OXMLAction;

/**
 * Abstract utility ancestor.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractOXMLConfig implements OXMLConfig {

	private OXMLAction rootAction;

	public OXMLAction getRootAction() {
		return rootAction;
	}

	/**
	 * Defines the root action of this configuration
	 * 
	 * @param rootAction
	 *            Root action
	 */
	protected void setRootAction(OXMLAction rootAction) {
		this.rootAction = rootAction;
	}

}
