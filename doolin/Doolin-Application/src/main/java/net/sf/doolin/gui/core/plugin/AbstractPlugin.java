/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.plugin;

import net.sf.doolin.gui.core.Plugin;

/**
 * Abstract ancestor for plugins.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractPlugin.java,v 1.2 2007/08/10 16:54:40 guinnessman Exp $
 * @param <C>
 *            Type of context for the plugin
 */
public abstract class AbstractPlugin<C> implements Plugin<C> {

	/**
	 * Error handling
	 */
	private boolean stopsOnError = true;

	public boolean isStopsOnError() {
		return stopsOnError;
	}

	/**
	 * Sets the behaviour of the plugin when there is an error.
	 * 
	 * @param stopsOnError
	 *            <code>true</code> if the application must stop when an error
	 *            occurs during the execution of this plugin.
	 */
	public void setStopsOnError(boolean stopsOnError) {
		this.stopsOnError = stopsOnError;
	}

}
