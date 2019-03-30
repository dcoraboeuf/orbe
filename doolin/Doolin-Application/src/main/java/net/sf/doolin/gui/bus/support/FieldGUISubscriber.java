/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.bus.support;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.bus.GUISubscriber;
import net.sf.doolin.gui.field.Field;

/**
 * This subscriber listens to one message type and update the target field when
 * it is received.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class FieldGUISubscriber<M> implements GUISubscriber<Field> {

	private Class<M> messageType;

	public void register(final Field component) {
		Bus.get().subscribe(messageType, new Subscriber<M>() {

			public void receive(M message) {
				Object formData = component.getForm().getFormData();
				Object fieldData = component.getFieldData(formData);
				component.setFieldData(formData, fieldData);
			}

		});
	}

	public Class<M> getMessageType() {
		return messageType;
	}

	public void setMessageType(Class<M> messageType) {
		this.messageType = messageType;
	}

}
