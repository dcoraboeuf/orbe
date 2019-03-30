/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.script.config;

/**
 * Association of a type and an item.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ScriptedAuthorizableItem.java,v 1.1 2007/08/17 15:06:56 guinnessman Exp $
 */
public class ScriptedAuthorizableItem {

	private String type;

	private String id;

	/**
	 * Returns the <code>id</code> property.
	 * 
	 * @return <code>id</code> property.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the <code>id</code> property.
	 * 
	 * @param id
	 *            <code>id</code> property.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the <code>type</code> property.
	 * 
	 * @return <code>type</code> property.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the <code>type</code> property.
	 * 
	 * @param type
	 *            <code>type</code> property.
	 */
	public void setType(String type) {
		this.type = type;
	}

}
