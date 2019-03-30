/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

/**
 * This Spring initializing bean allows the declaration at configuration level
 * of predefined context attributes.
 * 
 * @see Context#init(ContextAttributes)
 * @author Damien Coraboeuf
 * @version $Id: ContextAttributes.java,v 1.2 2007/07/31 15:33:08 guinnessman Exp $
 */
public class ContextAttributes implements InitializingBean {

	private Properties properties = new Properties();

	/**
	 * @return Returns the properties.
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            The properties to set.
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void afterPropertiesSet() throws Exception {
		Application.getContext().init(this);
	}

}
