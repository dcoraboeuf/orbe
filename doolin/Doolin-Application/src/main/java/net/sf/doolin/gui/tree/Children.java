/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

import java.util.Collection;

/**
 * Children definition for a node.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Children.java,v 1.2 2007/08/01 16:07:10 guinnessman Exp $
 */
public interface Children {

	/**
	 * @return Node id for the children
	 */
	String getId();

	/**
	 * Computes the children objects from a parent
	 * 
	 * @param root
	 *            Parent object
	 * @return List of children objects
	 */
	Collection getChildBeans(Object root);

}
