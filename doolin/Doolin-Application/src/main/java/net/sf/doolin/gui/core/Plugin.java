/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core;

/**
 * Defines a plugin for a Doolin GUI application component.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Plugin.java,v 1.2 2007/08/02 19:31:11 guinnessman Exp $
 * @param <C>
 *            Type of context the plugin is used for.
 */
public interface Plugin<C> {

	/**
	 * Activates this plugin for the given context and scope.
	 * 
	 * @param context
	 *            Context of plugin execution
	 * @param scope
	 *            Scope of plugin execution.
	 */
	void activate(C context, String scope);

	/**
	 * Defines if the whole application must stop if the plugin raises an error
	 * 
	 * @return <code>true</code> if the whole application must stop if the
	 *         plugin raises an error
	 */
	boolean isStopsOnError();

}
