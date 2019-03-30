/*
 * Created on 9 avr. 2005
 */
package net.sf.doolin.gui.swing.select;

import java.util.EventListener;

/**
 * An item has been selected
 * 
 * @author Damien Coraboeuf
 * @version $Id: SelectedEventListener.java,v 1.1 2007/07/25 18:23:37
 *          guinnessman Exp $
 */
public interface SelectedEventListener extends EventListener {

	/**
	 * This method is called when an item is selected or unselected.
	 * 
	 * @param event
	 *            Selection event
	 */
	void itemSelected(SelectedEvent event);
}
