/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui;

/**
 * List of error and message codes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MessageCodes.java,v 1.3 2007/08/07 16:47:13 guinnessman Exp $
 */
public interface MessageCodes {

	/**
	 * An application has already been launched.
	 */
	String APPLICATION_ALREADY_LAUNCHED = "net.sf.doolin.gui.ApplicationAlreadyLaunched";

	/**
	 * Ok button.
	 */
	String BUTTON_OK = "net.sf.doolin.gui.ButtonOK";

	/**
	 * Cancel button.
	 */
	String BUTTON_CANCEL = "net.sf.doolin.gui.ButtonCancel";

	/**
	 * Previous button.
	 */
	String BUTTON_PREVIOUS = "net.sf.doolin.gui.ButtonPrevious";

	/**
	 * Next button.
	 */
	String BUTTON_NEXT = "net.sf.doolin.gui.ButtonNext";

	/**
	 * Finish button.
	 */
	String BUTTON_FINISH = "net.sf.doolin.gui.ButtonFinish";

	/**
	 * This file already exists, do you want to replace it?
	 * <ul>
	 * <li>0 - file name
	 * </ul>
	 */
	String PROMPT_FILE_EXISTS = "net.sf.doolin.gui.PromptFileExists";

}
