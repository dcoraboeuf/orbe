/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.plugin;

import java.util.List;

import net.sf.doolin.gui.core.Plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper class for the execution of plugins.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PluginHelper.java,v 1.2 2007/08/10 16:54:40 guinnessman Exp $
 */
public class PluginHelper {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(PluginHelper.class);

	/**
	 * Executes a list of plugins for a given context.
	 * 
	 * @param <C>
	 *            Type of context
	 * @param pluginList
	 *            List of plugins to execute
	 * @param context
	 *            Context to execute the plugins for
	 * @param scope
	 *            Scope for the execution of the plugin (depends on the context
	 *            type)
	 */
	public static <C> void activatePlugins(List<Plugin<C>> pluginList, C context, String scope) {
		for (Plugin<C> plugin : pluginList) {
			log.info("Activate plugin " + plugin + " for " + context + " at scope " + scope);
			try {
				plugin.activate(context, scope);
			} catch (RuntimeException th) {
				if (plugin.isStopsOnError()) {
					throw th;
				} else {
					log.error("Error while activating plugin " + plugin + " for " + context + " at scope " + scope, th);
				}
			}
		}
	}

}
