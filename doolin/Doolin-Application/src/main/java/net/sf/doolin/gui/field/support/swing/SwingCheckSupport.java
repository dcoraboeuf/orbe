/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import net.sf.doolin.gui.field.FieldCheck;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.CheckSupport;
import net.sf.doolin.gui.service.GUIStrings;

/**
 * Support implementation for <code>{@link FieldCheck}</code> based on a
 * <code>{@link JCheckBox}</code> component.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingCheckSupport.java,v 1.1 2007/08/10 16:54:38 guinnessman Exp $
 */
public class SwingCheckSupport extends AbstractSwingInfoFieldSupport<FieldCheck, JCheckBox> implements CheckSupport {

	private JCheckBox box;

	@Override
	protected JCheckBox createComponent() {
		// Creates the component
		box = new JCheckBox();
		// Name
		box.setText(GUIStrings.get(getField().getName()));
		// Ok
		return box;
	}

	/**
	 * @see JCheckBox#isSelected()
	 * @see net.sf.doolin.gui.field.support.CheckSupport#isSelected()
	 */
	public boolean isSelected() {
		return box.isSelected();
	}

	/**
	 * @see JCheckBox#setSelected(boolean)
	 * @see net.sf.doolin.gui.field.support.CheckSupport#setSelected(boolean)
	 */
	public void setSelected(boolean selected) {
		box.setSelected(selected);
	}
	
	public void bindEditEvent(final EventAction eventAction) {
		box.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
