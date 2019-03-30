/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.gui.service.support;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Properties;

import javax.swing.ImageIcon;

import net.sf.doolin.util.ParameterSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default cursor factory, based on a property file. By default, no custom
 * cursor is available.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class DefaultCursorFactory extends AbstractCursorFactory {
	
	private static Log log = LogFactory.getLog(DefaultCursorFactory.class);

	private Properties properties = new Properties();
	
	private ParameterSet parameterSet;

	@Override
	protected Cursor loadCursor(String name) {
		// Image path
		String path = parameterSet.getParam(name + ".image", false, null);
		// No cursor
		if (path == null) {
			log.warn("No cursor with name " + name);
			return null;
		}
		// Get the image
		URL url = getClass().getResource(path);
		if (url == null) {
			log.warn("Cannot find cursor image at " + url);
			return null;
		}
		ImageIcon image = new ImageIcon(url);
		int x = parameterSet.getIntParam(name + ".x", true, 0);
		int y = parameterSet.getIntParam(name + ".y", true, 0);
		// Creates the cursor
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(x, y), name);
		// Ok
		return cursor;
	}

	/**
	 * Returns the cursor definition properties
	 * 
	 * @return Cursor definition properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the cursor definition properties
	 * 
	 * @param properties
	 *            Cursor definition properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
		this.parameterSet = new ParameterSet(this.properties);
	}

}
