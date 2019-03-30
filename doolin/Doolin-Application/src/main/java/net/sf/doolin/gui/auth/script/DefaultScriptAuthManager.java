/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.doolin.gui.auth.script.config.ScriptedAuthorizableItem;
import net.sf.doolin.gui.auth.script.config.ScriptedAuthorizationGroup;
import net.sf.doolin.gui.auth.script.config.ScriptedAuthorizations;

/**
 * Default implementation for a script-based auth manager.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultScriptAuthManager.java,v 1.1 2007/08/17 15:07:01 guinnessman Exp $
 * 
 * @param <T>
 *            Type of user
 */
public class DefaultScriptAuthManager<T extends Principal> extends AbstractScriptAuthManager<T> {

	private ScriptedAuthorizations scriptedAuthorizations;

	private Map<String, Map<String, String>> indexedScripts;

	/**
	 * Indexes all the authorizations
	 */
	public synchronized void init() {
		indexedScripts = new HashMap<String, Map<String, String>>();
		// For all authorization groups
		for (ScriptedAuthorizationGroup group : scriptedAuthorizations.getAuthorizations()) {
			index(group);
		}
	}

	/**
	 * Indexes an authorization group.
	 * 
	 * @param group
	 *            Group to index
	 */
	protected void index(ScriptedAuthorizationGroup group) {
		String script = group.getScript();
		List<ScriptedAuthorizableItem> items = group.getItems();
		for (ScriptedAuthorizableItem item : items) {
			index(item, script);
		}
	}

	/**
	 * Indexes an authorizable item with its script.
	 * 
	 * @param item
	 *            Item to index
	 * @param script
	 *            Associated script
	 */
	protected void index(ScriptedAuthorizableItem item, String script) {
		String type = item.getType();
		String id = item.getId();
		Map<String, String> idMap = indexedScripts.get(type);
		if (idMap == null) {
			idMap = new HashMap<String, String>();
			indexedScripts.put(type, idMap);
		}
		idMap.put(id, script);
	}

	@Override
	protected String getScript(String type, String identifier) {
		if (indexedScripts == null) {
			init();
		}
		Map<String, String> idMap = indexedScripts.get(type);
		if (idMap != null) {
			return idMap.get(identifier);
		} else {
			return null;
		}
	}

	/**
	 * Returns the <code>scriptedAuthorizations</code> property.
	 * 
	 * @return <code>scriptedAuthorizations</code> property.
	 */
	public ScriptedAuthorizations getScriptedAuthorizations() {
		return scriptedAuthorizations;
	}

	/**
	 * Sets the <code>scriptedAuthorizations</code> property.
	 * 
	 * @param scriptedAuthorizations
	 *            <code>scriptedAuthorizations</code> property.
	 */
	public void setScriptedAuthorizations(ScriptedAuthorizations scriptedAuthorizations) {
		this.scriptedAuthorizations = scriptedAuthorizations;
	}

}
