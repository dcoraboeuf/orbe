/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.gui.core.action;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;

import net.sf.doolin.gui.annotation.Configurable;

/**
 * This action displays a Java Help window.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ActionJavaHelp extends AbstractAction {

	/**
	 * Path to the Help definition file.
	 */
	private String path;

	private CSH.DisplayHelpFromSource presentation;

	/**
	 * Returns the path to the Help definition file.
	 * 
	 * @return Path to the Help definition file.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path to the Help definition file.
	 * 
	 * @param path
	 *            Path to the Help definition file.
	 */
	@Configurable(mandatory = true, description = "Path to the Help definition file")
	public void setPath(String path) {
		this.path = path;
	}

	protected void init() {
		try {
			URL hsURL = getClass().getResource(path);
			if (hsURL == null) {
				throw new RuntimeException("URL to the help set is not correct: " + path);
			}
			HelpSet helpSet = new HelpSet(null, hsURL);
			HelpBroker helpBroker = helpSet.createHelpBroker();
			presentation = new CSH.DisplayHelpFromSource(helpBroker);
		} catch (HelpSetException ex) {
			throw new RuntimeException("Cannot set the help", ex);
		}
	}

	@Override
	protected void process() {
		if (presentation == null) {
			init();
		}
		ActionEvent e = new ActionEvent(getView().getComponent(), 0, null);
		presentation.actionPerformed(e);
	}

}
