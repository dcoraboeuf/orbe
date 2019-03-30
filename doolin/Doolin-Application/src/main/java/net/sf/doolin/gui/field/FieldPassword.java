/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.field.support.PasswordSupport;

/**
 * Password field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldPassword.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 */
public class FieldPassword extends AbstractSupportField<PasswordSupport> {

	private int columns = 8;

	/**
	 * @see PasswordSupport
	 * @see #createSupport(Class)
	 * @see net.sf.doolin.gui.field.AbstractSupportField#createSupport()
	 */
	@Override
	protected PasswordSupport createSupport() {
		return createSupport(PasswordSupport.class);
	}

	/**
	 * @see PasswordSupport#getPassword()
	 * @see net.sf.doolin.gui.field.Field#getFieldData(java.lang.Object)
	 */
	public Object getFieldData(Object formData) {
		return getSupport().getPassword();
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.Field#setFieldData(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setFieldData(Object formData, Object fieldData) {
		getSupport().clearPassword();
	}

	/**
	 * Returns the <code>columns</code> property.
	 * 
	 * @return <code>columns</code> property.
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Sets the <code>columns</code> property.
	 * 
	 * @param columns
	 *            <code>columns</code> property.
	 */
	@Configurable(mandatory = true, description = "Number of characters", defaultsTo = "8")
	public void setColumns(int columns) {
		this.columns = columns;
	}

}
