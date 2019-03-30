/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.field.event;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.swing.SwingUtils;

/**
 * Binds key events to actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id: KeyEventBinder.java,v 1.3 2007/08/10 16:54:41 guinnessman Exp $
 */
public class KeyEventBinder extends AbstractEventBinder {

	private String key;

	public void bind(final Field field) {
		JComponent component = field.getFocusableComponent();
		final View view = field.getForm().getView();

		SwingUtils.associateKey(component, JComponent.WHEN_FOCUSED, key, new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				getEventAction().execute(view, field, null);
			}

		});
	}

	/**
	 * @return Associated shortcut
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            Associated shortcut
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
