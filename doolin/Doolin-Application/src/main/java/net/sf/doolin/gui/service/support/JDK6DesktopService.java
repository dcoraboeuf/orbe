/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.service.support;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.sf.doolin.gui.service.DesktopService;

/**
 * Implementation of the desktop service based on the JDK6
 * <code>{@link Desktop}</code> component.
 * 
 * @author Damien Coraboeuf
 * @version $Id: JDK6DesktopService.java,v 1.1 2007/08/10 16:54:38 guinnessman
 *          Exp $
 */
public class JDK6DesktopService implements DesktopService {

	/**
	 * @see Desktop#open(File)
	 * @see net.sf.doolin.gui.service.DesktopService#openFile(java.io.File)
	 */
	public void openFile(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException ex) {
			throw new RuntimeException("Cannot open file " + file, ex);
		}
	}

	/**
	 * @see Desktop#browse(java.net.URI)
	 * @see net.sf.doolin.gui.service.DesktopService#browse(java.net.URL)
	 */
	public void browse(URL url) {
		try {
			Desktop.getDesktop().browse(url.toURI());
		} catch (Exception ex) {
			throw new RuntimeException("Cannot browser URI" + url, ex);
		}
	}

	/**
	 * @see Desktop#edit(File)
	 * @see net.sf.doolin.gui.service.DesktopService#editFile(java.io.File)
	 */
	public void editFile(File file) {
		try {
			Desktop.getDesktop().edit(file);
		} catch (IOException ex) {
			throw new RuntimeException("Cannot edit file " + file, ex);
		}
	}

	/**
	 * @see Desktop#mail()
	 * @see net.sf.doolin.gui.service.DesktopService#mail(java.lang.String)
	 */
	public void mail(String mail) {
		try {
			Desktop.getDesktop().mail(new URI(mail));
		} catch (IOException ex) {
			throw new RuntimeException("Cannot open mail for " + mail, ex);
		} catch (URISyntaxException ex) {
			throw new RuntimeException("Cannot open mail for " + mail, ex);
		}
	}

}
