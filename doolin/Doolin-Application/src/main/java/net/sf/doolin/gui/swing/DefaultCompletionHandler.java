package net.sf.doolin.gui.swing;

/**
 * This default implementation for the completion handler does not allow the
 * creation of new items.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultCompletionHandler.java,v 1.1 2007/08/10 16:54:40 guinnessman Exp $
 */
public class DefaultCompletionHandler implements CompletionHandler {

	/**
	 * No new creation is allowed by default
	 */
	public boolean isCompletionAllowsCreation() {
		return false;
	}

	public Object createItem(String text) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}