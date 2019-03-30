/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.annotation.Configurable;

/**
 * Binds an event to an event action.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractEventBinder implements EventBinder {

	private EventAction eventAction;

	public EventAction getEventAction() {
		return eventAction;
	}

	/**
	 * Sets the event action to execute.
	 * 
	 * @param eventAction
	 *            Event action
	 */
	@Configurable(mandatory = true, description = "Event action to execute")
	public void setEventAction(EventAction eventAction) {
		this.eventAction = eventAction;
	}

}
