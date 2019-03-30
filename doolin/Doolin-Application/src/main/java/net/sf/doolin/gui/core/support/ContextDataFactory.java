/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.gui.Application;
import net.sf.doolin.util.Utils;

import org.apache.commons.lang.StringUtils;

public class ContextDataFactory {

	private Class type;

	private boolean context;

	private String contextId;

	public boolean isContext() {
		return context;
	}

	public void setContext(boolean context) {
		this.context = context;
	}

	public String getContextId() {
		return contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}
	
	protected String getActualContextId () {
		if (StringUtils.isNotBlank(contextId)) {
			return contextId;
		} else {
			return type.getName();
		}
	}
	
	public Object createInstance () {
		String key = getActualContextId();
		// Checks in context first
		if (context) {
			Object instance = Application.getContext().getAttribute(key);
			if (instance != null) {
				return instance;
			}
		}
		// Creates the instance
		@SuppressWarnings("unchecked")
		Object instance = Utils.newInstance(type);
		// Stores into context
		if (context) {
			Application.getContext().setAttribute(key, instance);
		}
		// Ok
		return instance;
	}

}
