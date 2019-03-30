/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.field.Field;

/**
 * Defines the action to execute for an event.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface EventAction {

	void execute(View view, Field field, ActionControler actionControler);

}
