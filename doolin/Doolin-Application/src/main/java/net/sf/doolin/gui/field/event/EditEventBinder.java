/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.field.Field;

/**
 * Binds edit events to actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id: KeyEventBinder.java,v 1.3 2007/08/10 16:54:41 guinnessman Exp $
 */
public class EditEventBinder extends AbstractEventBinder {

	public void bind(final Field field) {
		if (field instanceof EditEventBoundable) {
			((EditEventBoundable) field).bindEditEvent(getEventAction());
		}
	}

}
