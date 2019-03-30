/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.service.PropertyService;

public class PropertyViewDataFactory implements ViewDataFactory {
	
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getViewData(Object viewData) {
		PropertyService propertyService = Application.getApplication().getService(PropertyService.class);
		return propertyService.getProperty(viewData, property);
	}

}
