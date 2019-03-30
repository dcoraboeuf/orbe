/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml;

import java.io.IOException;
import java.util.List;

import net.sf.doolin.util.Utils;
import net.sf.doolin.util.xml.DOMUtils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

/**
 * Creates objects from instance definitions.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class OXMLFactory {

	/**
	 * Unique instance
	 */
	private static OXMLFactory instance = null;

	/**
	 * Get the instance
	 * 
	 * @return Factory to be used
	 */
	public static OXMLFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OXMLFactory createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OXMLFactory temp = new OXMLFactory();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Initialization
	 */
	private OXMLFactory() {

	}

	/**
	 * Creates an instance from a definition, using the <code>class</code>
	 * attribute and a list of <code>param</code> children as properties.
	 * 
	 * @param <T>
	 *            Expected type
	 * @param e
	 *            Definition
	 * @return Instance
	 * @throws IOException
	 *             If the element cannot be created
	 */
	public <T> T create(Element e) throws IOException {
		// Class name
		Class clazz = DOMUtils.getClassAttribute(e, "class", null);
		if (clazz == null) {
			throw new IOException("Missing 'class' attribute on " + DOMUtils.getXPath(e));
		}
		// Instance
		@SuppressWarnings("unchecked")
		T t = (T) Utils.newInstance(clazz);
		// Params
		List<Element> paramList = DOMUtils.getElements(e, OXML.NS, "param");
		for (Element eParam : paramList) {
			String name = DOMUtils.getAttribute(eParam, "name", true, null);
			String value = DOMUtils.getAttribute(eParam, "value", false, null);
			if (StringUtils.isNotBlank(value)) {
				try {
					PropertyUtils.setProperty(t, name, value);
				} catch (Exception ex) {
					throw new RuntimeException("Cannot set property " + name, ex);
				}
			}
		}
		// Ok
		return t;
	}

}
