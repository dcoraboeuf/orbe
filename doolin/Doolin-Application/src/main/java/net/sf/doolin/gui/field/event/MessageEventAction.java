/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.event;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.util.Utils;

/**
 * Sends a message.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class MessageEventAction extends AbstractEventAction {

	private boolean validate = true;

	private Class<?> messageType;

	protected void process(View view, Field field, ActionControler actionControler) {
		boolean ok = true;
		if (validate) {
			ValidationReport validationReport = new ValidationReport();
			view.validate(validationReport);
			view.display(validationReport);
			ok = validationReport.isOk();
		}
		if (ok) {
			Object message = Utils.newInstance(messageType);
			Bus.get().publish(message);
		}
	}

	public Class<?> getMessageType() {
		return messageType;
	}

	public void setMessageType(Class<?> messageType) {
		this.messageType = messageType;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

}
