/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service;

public interface PropertyService {

	Object getProperty(Object bean, String property);
	
	void setProperty(Object bean, String property, Object value);

}
