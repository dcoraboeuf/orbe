/*
 * Created on Mar 29, 2007
 */
package net.sf.doolin.gui.swing;

/**
 * This interface is used by the
 * {@link AutoCompletionDocument auto completion handler} to map entered text to
 * entries in a combo-box.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CompletionHandler.java,v 1.1 2007/08/10 16:54:40 guinnessman Exp $
 */
public interface CompletionHandler {

	/**
	 * @return Does this handle authorize the creation of new items?
	 */
	boolean isCompletionAllowsCreation();

	/**
	 * Creates a new item from a text
	 * 
	 * @param text
	 *            Text for the created item
	 * @return Created item
	 * @throws UnsupportedOperationException
	 *             If this handler does not support creation of new items.
	 */
	Object createItem(String text) throws UnsupportedOperationException;

}
