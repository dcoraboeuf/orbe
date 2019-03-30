/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.field.Field;

/**
 * Abstract ancestor for event actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractEventAction implements EventAction {

	public void execute(View view, Field field, ActionControler actionControler) {
		if (view.isDisplayed()) {
			process(view, field, actionControler);
		}
	}

	protected abstract void process(View view, Field field, ActionControler actionControler);

}
