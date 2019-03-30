/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.oxml.adapter;

import net.sf.doolin.oxml.OXMLContext;

/**
 * Factory for instances.
 * 
 * @param <T>
 *            Type of the created instance
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface OXMLInstanceFactory<T> {

	/**
	 * Creates an object from the context
	 * 
	 * @param context
	 *            Execution context
	 * @return Created instance
	 */
	T createInstance(OXMLContext context);

}
