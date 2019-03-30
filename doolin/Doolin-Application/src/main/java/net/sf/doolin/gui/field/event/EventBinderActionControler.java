/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.FieldAction;

public class EventBinderActionControler implements ActionControler {
	
	private Field field;

	/**
	 * @param field
	 */
	public EventBinderActionControler(Field field) {
		this.field = field;
	}

	public void control(Action action) {
		if (action instanceof FieldAction) {
			((FieldAction)action).setField(field);
		}
	}

}
