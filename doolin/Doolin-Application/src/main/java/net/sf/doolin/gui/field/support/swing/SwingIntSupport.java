/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.doolin.gui.field.FieldInt;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.IntSupport;

/**
 * Support for the text field, based on a text field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingTextSupport.java,v 1.2 2007/08/07 16:47:05 guinnessman Exp $
 */
public class SwingIntSupport extends AbstractSwingInfoFieldSupport<FieldInt, JSpinner> implements IntSupport {

	/**
	 * Field
	 */
	private JSpinner field;

	@Override
	protected JSpinner createComponent() {
		// Model
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setMinimum(getField().getMin());
		model.setMaximum(getField().getMax());
		model.setStepSize(1);
		// Field
		field = new JSpinner(model);
		// TODO Generates an event in case of change
		// Ok
		return field;
	}

	public int getValue() {
		return (Integer) field.getValue();
	}

	public void setValue(int value) {
		field.setValue(value);
	}
	
	public void bindEditEvent(final EventAction eventAction) {
		field.addChangeListener(new ChangeListener() {
		
			public void stateChanged(ChangeEvent e) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
