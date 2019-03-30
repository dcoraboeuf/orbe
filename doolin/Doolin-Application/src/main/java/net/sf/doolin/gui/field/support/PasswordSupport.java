/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.field.support;

import net.sf.doolin.gui.field.FieldPassword;

/**
 * Support interface for the <code>{@link FieldPassword}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PasswordSupport.java,v 1.1 2007/08/17 15:06:59 guinnessman Exp $
 */
public interface PasswordSupport extends FieldSupport<FieldPassword> {

	/**
	 * Returns the entered password
	 * 
	 * @return Password
	 */
	char[] getPassword();

	/**
	 * Clears the password.
	 */
	void clearPassword();

}
