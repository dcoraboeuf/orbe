/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.helper;

import java.util.List;

/**
 * This interface defines a class that returns a list of items.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ItemProvider.java,v 1.2 2007/08/07 16:47:12 guinnessman Exp $
 */
public interface ItemProvider {

	/**
	 * Returns a list of items.
	 * 
	 * @return List of items.
	 */
	List getItems();

}
