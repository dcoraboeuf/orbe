/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Associates a list of authorizable items and a script.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ScriptedAuthorizationGroup.java,v 1.1 2007/08/17 15:06:56 guinnessman Exp $
 */
public class ScriptedAuthorizationGroup {

	private List<ScriptedAuthorizableItem> items = new ArrayList<ScriptedAuthorizableItem>();

	private String script;

	/**
	 * Returns the <code>items</code> property.
	 * 
	 * @return <code>items</code> property.
	 */
	public List<ScriptedAuthorizableItem> getItems() {
		return items;
	}

	/**
	 * Sets the <code>items</code> property.
	 * 
	 * @param items
	 *            <code>items</code> property.
	 */
	public void setItems(List<ScriptedAuthorizableItem> items) {
		this.items = items;
	}

	/**
	 * Returns the <code>script</code> property.
	 * 
	 * @return <code>script</code> property.
	 */
	public String getScript() {
		return script;
	}

	/**
	 * Sets the <code>script</code> property.
	 * 
	 * @param script
	 *            <code>script</code> property.
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * Adds an item to the list.
	 * 
	 * @param item
	 *            Item to add.
	 */
	public void addItem(ScriptedAuthorizableItem item) {
		items.add(item);
	}

}
