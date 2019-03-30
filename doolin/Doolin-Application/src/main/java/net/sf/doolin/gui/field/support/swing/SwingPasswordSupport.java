/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.doolin.gui.field.FieldPassword;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.PasswordSupport;

/**
 * 
 * Support for the <code>{@link FieldPassword}</code> based on a
 * <code>{@link JPasswordField}</code>.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingPasswordSupport.java,v 1.1 2007/08/17 15:06:52 guinnessman
 *          Exp $
 */
public class SwingPasswordSupport extends AbstractSwingInfoFieldSupport<FieldPassword, JPasswordField> implements PasswordSupport {

	private JPasswordField field;

	@Override
	protected JPasswordField createComponent() {
		field = new JPasswordField();
		field.setColumns(getField().getColumns());
		return field;
	}

	/**
	 * @see JPasswordField#getPassword()
	 * @see net.sf.doolin.gui.field.support.PasswordSupport#getPassword()
	 */
	public char[] getPassword() {
		return field.getPassword();
	}

	public void bindEditEvent(final EventAction eventAction) {
		field.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				edit();
			}

			public void insertUpdate(DocumentEvent e) {
				edit();
			}

			public void changedUpdate(DocumentEvent e) {
				edit();
			}

			protected void edit() {
				eventAction.execute(getView(), getField(), null);
			}

		});
	}

	/**
	 * Clears the password
	 * 
	 * @see JPasswordField#setText(String)
	 * @see net.sf.doolin.gui.field.support.PasswordSupport#clearPassword()
	 */
	@Override
	public void clearPassword() {
		field.setText(null);
	}

}
