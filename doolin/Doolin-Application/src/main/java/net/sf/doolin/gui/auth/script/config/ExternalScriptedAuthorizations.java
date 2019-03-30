/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script.config;

import java.net.URL;

import net.sf.doolin.util.xml.DigesterUtils;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.support.DataFactory;

/**
 * Factory for scripted authorizations, based on an XML file.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ExternalScriptedAuthorizations.java,v 1.1 2007/08/17 15:06:56 guinnessman Exp $
 */
public class ExternalScriptedAuthorizations implements DataFactory<ScriptedAuthorizations> {

	private String path;

	/**
	 * Returns the <code>path</code> property.
	 * 
	 * @return <code>path</code> property.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the <code>path</code> property.
	 * 
	 * @param path
	 *            <code>path</code> property.
	 */
	@Configurable(mandatory = true, description = "Resource path to the XML file")
	public void setPath(String path) {
		this.path = path;
	}

	public ScriptedAuthorizations createData() {
		// Schema for the Auth
		URL schemaURL = getClass().getResource("Auth.xsd");
		// Creates a validating digester
		DigesterUtils digesterUtils = DigesterUtils.createValidatingDigester(schemaURL);

		// Setup
		digesterUtils.ruleObject("authgroup", ScriptedAuthorizationGroup.class, "addGroup");
		digesterUtils.ruleObject("auth", ScriptedAuthorizableItem.class, "addItem");
		digesterUtils.ruleProperty("script", "setScript");

		// Root
		ScriptedAuthorizations authorizations = new ScriptedAuthorizations();

		// Parsing
		digesterUtils.parse(getClass().getResource(path), authorizations);

		// Ok
		return authorizations;
	}

}
