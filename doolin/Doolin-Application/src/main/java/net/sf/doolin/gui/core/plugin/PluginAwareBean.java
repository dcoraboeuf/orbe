/*
 * Created on Sep 21, 2007
 */
package net.sf.doolin.gui.core.plugin;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.gui.core.Plugin;

/**
 * Defines a class that can contains plugins.
 * 
 * @param <C>
 *            Type of the plugin context
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class PluginAwareBean<C> {

	private List<Plugin<C>> plugins = new ArrayList<Plugin<C>>(0);

	public List<Plugin<C>> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Plugin<C>> plugins) {
		this.plugins = plugins;
	}

}
