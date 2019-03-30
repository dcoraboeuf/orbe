/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.auth.AuthorizableItem;
import net.sf.doolin.gui.auth.support.AbstractAuthManager;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.ScriptEvaluator;

/**
 * Defines a script-based authorization manager.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractScriptAuthManager.java,v 1.1 2007/08/17 15:07:02 guinnessman Exp $
 * @param <T>
 *            Type of user managed by this authorization manager
 */
public abstract class AbstractScriptAuthManager<T extends Principal> extends AbstractAuthManager<T> {

	private boolean defaultAuthorization = true;

	private String scriptName = "JavaScript";

	/**
	 * @see net.sf.doolin.gui.auth.support.AbstractAuthManager#isAuthorized(java.security.Principal,
	 *      net.sf.doolin.gui.core.View,
	 *      net.sf.doolin.gui.auth.AuthorizableItem)
	 */
	@Override
	protected boolean isAuthorized(T user, View view, AuthorizableItem item) {
		// Gets the item type
		String type = item.getAuthorizableItemType();
		// Gets the item identifier
		String identifier = item.getAuthorizationIdentifier();
		// Gets the script for this item
		String script = getScript(type, identifier);
		// If no script, returns default authorization
		if (StringUtils.isBlank(script)) {
			return true;
		}
		// Executes the script
		else {
			Map<String, Object> context = createScriptContext(user, view);
			// Script service
			ScriptEvaluator scriptEvaluator = GUIUtils.getService(ScriptEvaluator.class);
			// Execution
			scriptEvaluator.evaluate(scriptName, script, context);
			// Gets the result
			Boolean authorized = (Boolean) context.get("authorized");
			// Ok
			return authorized != null && authorized;
		}
	}

	/**
	 * Returns the script for an authorizable item.
	 * 
	 * The script engine is defined by the
	 * <code>{@link #setScriptName(String) scriptName}</code> property.
	 * 
	 * The script receives two inputs:
	 * <ul>
	 * <li><code>user</code> - instance of <code>{@link Principal}</code>.
	 * <li><code>viewData</code> - data for the current view
	 * </ul>
	 * 
	 * Both of those inputs could be <code>null</code>.
	 * <p>
	 * The expected result of the authorization must be set by the script into:
	 * <ul>
	 * <li><code>authorized</code> - expected type is <code>boolean</code>.
	 * </ul>
	 * 
	 * @param type
	 *            Category
	 * @param identifier
	 *            Identifier
	 * @return Script or <code>null</code> if no authorization is needed.
	 */
	protected abstract String getScript(String type, String identifier);

	/**
	 * Creates the execution context for a script.
	 * 
	 * @param user
	 *            Authenticated user or <code>null</code>
	 * @param view
	 *            Current view or <code>null</code>
	 * @return Context
	 */
	protected Map<String, Object> createScriptContext(T user, View view) {
		// Gets the different actors
		HashMap<String, Object> context = new HashMap<String, Object>();
		// User
		if (user != null) {
			context.put("user", user);
		}
		// View data
		if (view != null && view.getViewData() != null) {
			context.put("viewData", view.getViewData());
		}
		return context;
	}

	/**
	 * Returns the <code>scriptName</code> property.
	 * 
	 * @return <code>scriptName</code> property.
	 */
	public String getScriptName() {
		return scriptName;
	}

	/**
	 * Sets the <code>scriptName</code> property.
	 * 
	 * @param scriptName
	 *            <code>scriptName</code> property.
	 */
	@Configurable(mandatory = false, description = "Script engine name", defaultsTo = "JavaScript")
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	/**
	 * Returns the <code>defaultAuthorization</code> property.
	 * 
	 * @return <code>defaultAuthorization</code> property.
	 */
	public boolean isDefaultAuthorization() {
		return defaultAuthorization;
	}

	/**
	 * Sets the <code>defaultAuthorization</code> property.
	 * 
	 * @param defaultAuthorization
	 *            <code>defaultAuthorization</code> property.
	 */
	@Configurable(mandatory = false, description = "Default authorization when a script is not found", defaultsTo = "true")
	public void setDefaultAuthorization(boolean defaultAuthorization) {
		this.defaultAuthorization = defaultAuthorization;
	}

}
