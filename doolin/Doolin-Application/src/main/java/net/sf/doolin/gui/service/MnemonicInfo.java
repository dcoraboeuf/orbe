/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JLabel;

/**
 * Contains information about the decoration of labels.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MnemonicInfo.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public interface MnemonicInfo {
	
	void configureButton (AbstractButton button);
	
	void configureAction (Action action);

	void configureLabel(JLabel label);

}
