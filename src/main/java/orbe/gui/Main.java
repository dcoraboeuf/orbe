/*
 * Created on Tue Oct 03 14:42:35 CEST 2006
 */
package orbe.gui;

import net.sf.doolin.gui.Application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Starts the application.
 * 
 * @version $Id: Main.java,v 1.2 2006/11/24 15:20:35 guinnessman Exp $
 */
public class Main {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Main.class);

	/**
	 * Entry point.
	 * 
	 * @param args
	 *            Not used.
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			String fileName = args[0];
			log.info("Opening with file " + fileName);
			Application.getContext().setAttribute(IAttributes.APPLICATION_STARTUP_FILE, fileName);
		}
		Application.launch(IResources.APPLICATION);
	}

}
