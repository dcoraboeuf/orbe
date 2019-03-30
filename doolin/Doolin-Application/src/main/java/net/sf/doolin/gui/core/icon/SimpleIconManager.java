/*
 * Created on Sep 10, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.gui.annotation.Configurable;

import org.springframework.beans.factory.InitializingBean;

/**
 * This icon manager is using a property file that maps the icon ids to a
 * resource path. No size is taken into account.
 * 
 * @author BE05735
 * @version $Id$
 */
public class SimpleIconManager extends DefaultIconManager implements InitializingBean {

	private Map<String, String> properties = new HashMap<String, String>();

	private String prefix;

	public String getPrefix() {
		return prefix;
	}

	@Configurable(mandatory = true, description = "Resource path prefix")
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	@Configurable(mandatory = true, description = "Index of icon paths per icon id")
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void afterPropertiesSet() throws Exception {
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			String id = entry.getKey();
			String suffix = entry.getValue();
			IconDefinition definition = new IconDefinition(prefix, suffix, suffix, suffix, suffix, suffix);
			getIconDefinitions().put(id, definition);
		}
	}

}
