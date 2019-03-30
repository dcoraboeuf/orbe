/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.gui.MessageCodes;
import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.Plugin;
import net.sf.doolin.gui.core.plugin.PluginHelper;
import net.sf.doolin.util.CodeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default implementation for an application manager.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultApplicationManager.java,v 1.1 2007/07/18 17:51:02
 *          guinnessman Exp $
 */
public class DefaultApplicationManager extends AbstractApplicationManager {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultApplicationManager.class);

	/**
	 * Plugins
	 */
	private List<Plugin<ApplicationManager>> plugins = new ArrayList<Plugin<ApplicationManager>>();

	/**
	 * Started flag
	 */
	private boolean started = false;

	/**
	 * Starting flag
	 */
	private boolean starting = false;

	/**
	 * Shutdown hook
	 */
	private ShutdownHook shutdownHook;

	/**
	 * Shutdown hook
	 */
	protected class ShutdownHook extends Thread {

		public ShutdownHook() {
			super("Doolin GUI ApplicationManager ShutdownHook");
		}

		/**
		 * Runs
		 */
		public void run() {
			PluginHelper.activatePlugins(plugins, DefaultApplicationManager.this, "shutdown");
		}

	}

	/**
	 * Starts the application manager. It can be started at most once.
	 * 
	 * @see net.sf.doolin.gui.core.ApplicationManager#start()
	 */
	public synchronized void run() {
		// Check
		if (starting) {
			throw new CodeException(MessageCodes.APPLICATION_ALREADY_LAUNCHED);
		}
		// Starting
		starting = true;
		// Already started
		if (!started) {
			log.info("Application manager starting...");
			// Register plugins for shutdown
			shutdownHook = new ShutdownHook();
			// Registers the shutdown hook
			Runtime.getRuntime().addShutdownHook(shutdownHook);
			// Plugins at initialization
			PluginHelper.activatePlugins(plugins, this, "init");
		}
		// Startup
		if (getStartup() == null) {
			log.warn("No startup has been defined for the application manager.");
		} else {
			getStartup().start(this);
		}
		// Plugins at startup
		PluginHelper.activatePlugins(plugins, this, "start");
		// Ok
		log.info("Application manager started.");
		starting = false;
		started = true;
	}

	/**
	 * @return Returns the started state.
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @return Returns the starting state
	 */
	public boolean isStarting() {
		return starting;
	}

	public void exit(int code) {
		// Plugins at exit
		PluginHelper.activatePlugins(plugins, this, "exit");
		// Ok
		System.exit(0);
	}

	public List<Plugin<ApplicationManager>> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Plugin<ApplicationManager>> plugins) {
		this.plugins = plugins;
	}

}
