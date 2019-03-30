/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.service;

import java.io.File;
import java.net.URL;

/**
 * This service is used to interact with the user's desktop.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DesktopService.java,v 1.2 2007/08/14 11:48:59 guinnessman Exp $
 */
public interface DesktopService {

	/**
	 * Opens the file using the desktop.
	 * 
	 * @param file
	 *            File to open
	 */
	void openFile(File file);

	/**
	 * Opens the file for edition.
	 * 
	 * @param file
	 *            File to edit
	 */
	void editFile(File file);

	/**
	 * Browses the URL using the desktop browser
	 * 
	 * @param url
	 *            URL to browse
	 */
	void browse(URL url);

	/**
	 * Opens the mail system of the user
	 * 
	 * @param mail
	 *            Mail address to write to
	 */
	void mail(String mail);

}
