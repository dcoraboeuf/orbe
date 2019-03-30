/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.oxml.adapter;

import java.lang.reflect.Method;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.Utils;

/**
 * Adapter towards an enumeration.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class EnumOXMLAdapter extends AbstractSimpleOXMLAdapter<Enum<?>> {

	private String type;

	@Override
	protected Enum<?> adapt(String value, OXMLContext context) {
		try {
			@SuppressWarnings("unchecked")
			Class<Enum> ec = Utils.forClass(type);
			Method methodValueOf = ec.getMethod("valueOf", new Class[] { String.class });
			Object e = methodValueOf.invoke(null, new Object[] { value });
			return (Enum<?>) e;
		} catch (Exception ex) {
			throw new RuntimeException("Cannot convert " + value + " to enumeration " + type, ex);
		}
	}

	/**
	 * Returns the enumeration class name
	 * 
	 * @return Class name
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the enumeration class name
	 * 
	 * @param type
	 *            Class name
	 */
	public void setType(String type) {
		this.type = type;
	}

}
