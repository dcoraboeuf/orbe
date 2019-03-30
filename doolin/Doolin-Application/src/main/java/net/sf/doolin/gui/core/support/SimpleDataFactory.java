/*
 * Created on Aug 15, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.util.Utils;

import net.sf.doolin.gui.annotation.Configurable;

/**
 * Creates an instance from a class name.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SimpleDataFactory.java,v 1.1 2007/08/17 15:07:04 guinnessman Exp $
 * @param <T>
 *            Type of instance to create
 */
public class SimpleDataFactory<T> implements DataFactory<T> {

	private Class<T> type;

	public T createData() {
		return Utils.newInstance(type);
	}

	/**
	 * Returns the <code>type</code> property.
	 * 
	 * @return <code>type</code> property.
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * Sets the <code>type</code> property.
	 * 
	 * @param type
	 *            <code>type</code> property.
	 */
	@Configurable(mandatory = true, description = "Type of instance to create")
	public void setType(Class<T> type) {
		this.type = type;
	}

}
