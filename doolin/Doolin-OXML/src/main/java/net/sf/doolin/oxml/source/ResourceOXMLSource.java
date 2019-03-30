/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml.source;

import net.sf.doolin.util.Utils;

/**
 * Builds a source from a resource path.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ResourceOXMLSource extends URLOXMLSource {

	/**
	 * Constructor
	 * 
	 * @param referenceClass
	 *            Reference for the resource path
	 * @param path
	 *            Resource path from the reference class
	 */
	public ResourceOXMLSource(Class referenceClass, String path) {
		super(Utils.getResource(referenceClass, path));
	}

}
