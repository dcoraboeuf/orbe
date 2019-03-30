package net.sf.doolin.util.copy;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Utility ancestor for property copiers.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public abstract class AbstractPropertyCopier implements PropertyCopier {

	protected Object getProperty(Object bean, String name) {
		try {
			Object value = PropertyUtils.getProperty(bean, name);
			return value;
		} catch (Exception e) {
			throw new RuntimeException("Cannot get " + name + " property on " + bean, e);
		}
	}

	protected void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			throw new RuntimeException("Cannot set " + name + " property on " + bean, e);
		}
	}

}
