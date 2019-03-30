/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.event;

/**
 * Interface implemented by the fields that can be bound to an edit event.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface EditEventBoundable {

	/**
	 * Binds this object to the event action
	 * 
	 * @param eventAction
	 *            Event action to execute
	 */
	void bindEditEvent(EventAction eventAction);

}
