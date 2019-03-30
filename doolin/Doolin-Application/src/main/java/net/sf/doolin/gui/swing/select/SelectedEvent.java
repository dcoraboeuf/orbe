/*
 * Created on 9 avr. 2005
 */
package net.sf.doolin.gui.swing.select;

import java.util.EventObject;

/**
 * An item has been selected
 * 
 * @author Damien Coraboeuf
 * @version $Id: SelectedEvent.java,v 1.1 2007/08/07 16:47:08 guinnessman Exp $
 */
public class SelectedEvent extends EventObject {
	/**
	 * Constructor
	 * 
	 * @param source
	 *            Source of the event
	 * @see EventObject#EventObject(Object)
	 */
	public SelectedEvent(Object source) {
		super(source);
	}
}
