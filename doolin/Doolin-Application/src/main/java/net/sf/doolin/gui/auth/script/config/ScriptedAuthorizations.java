/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script.config;

import java.util.ArrayList;
import java.util.List;

/**
 * List of authorization groups.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ScriptedAuthorizations.java,v 1.1 2007/08/17 15:06:56 guinnessman Exp $
 */
public class ScriptedAuthorizations {

	private List<ScriptedAuthorizationGroup> authorizations = new ArrayList<ScriptedAuthorizationGroup>();

	/**
	 * Returns the <code>authorizations</code> property.
	 * 
	 * @return <code>authorizations</code> property.
	 */
	public List<ScriptedAuthorizationGroup> getAuthorizations() {
		return authorizations;
	}

	/**
	 * Sets the <code>authorizations</code> property.
	 * 
	 * @param authorizations
	 *            <code>authorizations</code> property.
	 */
	public void setAuthorizations(List<ScriptedAuthorizationGroup> authorizations) {
		this.authorizations = authorizations;
	}

	/**
	 * Adds a group to the list
	 * 
	 * @param group
	 *            Group to add
	 */
	public void addGroup(ScriptedAuthorizationGroup group) {
		authorizations.add(group);
	}

}
