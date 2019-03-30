/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

/**
 * Service factoru for label information.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MnemonicInfoFactory.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface MnemonicInfoFactory {

	/**
	 * Get the label information from a string.
	 */
	MnemonicInfo getLabelInfo(String name);

}
