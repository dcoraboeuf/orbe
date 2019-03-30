/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default icon manager. It loads icons using the classpath.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultIconManager.java,v 1.4 2007/07/31 15:59:32 guinnessman Exp $
 */
public class DefaultIconManager extends AbstractIconManager {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultIconManager.class);

	@Override
	protected Icon loadIconObject(String path) {
		URL url = toURL(path);
		if (url != null) {
			log.debug("Loading icon at " + url);
			ImageIcon icon = new ImageIcon(url);
			return icon;
		} else {
			log.warn("Cannot find icon path at " + path);
			return null;
		}
	}
	
	@Override
	protected URL toURL(String path) {
		URL url = getClass().getResource(path);
		return url;
	}

}
