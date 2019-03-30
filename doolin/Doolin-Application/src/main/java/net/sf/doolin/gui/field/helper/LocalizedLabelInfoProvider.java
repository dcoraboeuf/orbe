/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.helper;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.service.PropertyService;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class LocalizedLabelInfoProvider implements LabelInfoProvider {
	
	private String property;
	
	private String prefix;
	
	private String suffix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public LabelInfo getLabelIcon(Object item) {
		// Property
		if (StringUtils.isNotBlank(property)) {
			item = GUIUtils.getService(PropertyService.class).getProperty(item, property);
		}
		// Key
		String key = ObjectUtils.toString(item, "null");
		if (prefix != null) {
			key = prefix + key;
		}
		if (suffix != null) {
			key = key + suffix;
		}
		// Value
		String text = GUIStrings.get(key);
		// Ok
		return new LabelInfo(text, null);
	}

}
