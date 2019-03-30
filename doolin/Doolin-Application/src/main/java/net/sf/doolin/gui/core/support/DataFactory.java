/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.support;

/**
 * Defines an object that creates instances of other objects.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DataFactory.java,v 1.4 2007/08/10 16:54:39 guinnessman Exp $
 * @param <T>
 *            Type of the object to be created.
 */
public interface DataFactory<T> {

	/**
	 * Creates an object.
	 * 
	 * @return Object
	 */
	T createData();

}
