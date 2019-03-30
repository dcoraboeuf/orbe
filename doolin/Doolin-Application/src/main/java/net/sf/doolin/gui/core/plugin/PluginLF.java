/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.core.plugin;

import javax.swing.UIManager;

import net.sf.doolin.gui.core.ApplicationManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This plugin sets the default L&F to use at application level.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PluginLF.java,v 1.2 2007/08/10 16:54:40 guinnessman Exp $
 */
public class PluginLF extends AbstractApplicationManagerPlugin {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(PluginLF.class);

	private String lfClassName;

	/**
	 * @return Class name of the default L&F
	 */
	public String getLfClassName() {
		return lfClassName;
	}

	/**
	 * @param lfClassName
	 *            Class name of the default L&F
	 */
	public void setLfClassName(String lfClassName) {
		this.lfClassName = lfClassName;
	}

	@Override
	protected void init(ApplicationManager application) {
		Validate.notNull(lfClassName, "lfClassName must be set for PluginLF");
		try {
			String actualLFClassName = getDefaultLookAndFeel(application);
			log.info("Default L&F is " + actualLFClassName);
			UIManager.setLookAndFeel(actualLFClassName);
		} catch (Exception ex) {
			log.error("Cannot set the Look and Feel", ex);
		}
	}

	/**
	 * Get the default L&F.
	 * 
	 * @param application
	 *            Current running application
	 * @return Default L&F
	 */
	protected String getDefaultLookAndFeel(ApplicationManager application) {
		// TODO Get the look and feel from preferences
		// Default look and feel
		if (StringUtils.isBlank(lfClassName)) {
			return UIManager.getSystemLookAndFeelClassName();
		} else {
			return lfClassName;
		}
	}

}
