/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

import java.util.Collection;
import java.util.Collections;

import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * This children definition implementation calculates the list of children
 * objects based on a property on the parent object.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PropertyChildren.java,v 1.3 2007/08/15 09:05:21 guinnessman Exp $
 */
public class PropertyChildren extends AbstractChildren {

	private String property;

	@SuppressWarnings("unchecked")
	public Collection getChildBeans(Object root) {
		Object value = GUIUtils.getProperty(root, property);
		if (value == null) {
			return null;
		} else if (value instanceof Collection) {
			return (Collection) value;
		} else {
			return Collections.singletonList(value);
		}
	}

	/**
	 * @return Property name to get on the parent object
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property
	 *            Property name to get on the parent object
	 */
	public void setProperty(String property) {
		this.property = property;
	}

}
