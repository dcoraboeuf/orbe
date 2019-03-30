/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.service.PropertyService;

public class DefaultPropertyService implements PropertyService {

	public Object getProperty(Object bean, String property) {
		try {
			if (StringUtils.isBlank(property)) {
				return null;
			} else if (StringUtils.equals(property, "this")) {
				return bean;
			} else {
				return PropertyUtils.getProperty(bean, property);
			}
		} catch (Exception ex) {
			throw new RuntimeException("Cannot get property " + property + " on bean " + bean, ex);
		}
	}

	public void setProperty(Object bean, String property, Object value) {
		try {
			PropertyUtils.setProperty(bean, property, value);
		} catch (Exception ex) {
			throw new RuntimeException("Cannot set property " + property + " on bean " + bean + " with value " + value, ex);
		}
	}

}
