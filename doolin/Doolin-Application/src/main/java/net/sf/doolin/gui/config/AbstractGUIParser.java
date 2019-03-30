/*
 * Created on Jul 22, 2007
 */
package net.sf.doolin.gui.config;

import java.util.List;

import net.sf.doolin.util.xml.DOMUtils;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Root ancestor of parsers for the Doolin GUI configuration objects.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractGUIParser.java,v 1.3 2007/07/31 15:33:09 guinnessman Exp $
 */
public abstract class AbstractGUIParser extends AbstractSimpleBeanDefinitionParser {

	/**
	 * Reads underlying properties of a bean.
	 * 
	 * @param eBean
	 *            Bean element
	 * @param parserContext
	 *            Parser context
	 * @param bean
	 *            Bean definition
	 * @see BeanDefinitionParserDelegate#parsePropertyElements(Element,
	 *      org.springframework.beans.factory.config.BeanDefinition)
	 */
	protected void readProperties(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean) {
		parserContext.getDelegate().parsePropertyElements(eBean, bean.getBeanDefinition());
	}

	/**
	 * Reads a property of a bean using a custom property element.
	 * 
	 * @param eBean
	 *            Bean element
	 * @param parserContext
	 *            Parser context
	 * @param bean
	 *            Bean definition
	 * @param propertyName
	 *            Name of the property element
	 */
	protected void readProperty(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean, String propertyName) {
		Element eProperty = DOMUtils.getElement(eBean, propertyName);
		if (eProperty != null) {
			Object propertyValue = parserContext.getDelegate().parsePropertySubElement(eProperty, bean.getRawBeanDefinition());
			bean.addPropertyValue(propertyName, propertyValue);
		}
	}

	/**
	 * Reads a property of a bean using a custom property element whose first
	 * child defines the property value.
	 * 
	 * @param eBean
	 *            Bean element
	 * @param parserContext
	 *            Parser context
	 * @param bean
	 *            Bean definition
	 * @param propertyName
	 *            Name of the property element
	 */
	protected void readNestedProperty(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean, String propertyName) {
		Element eProperty = DOMUtils.getElement(eBean, propertyName);
		if (eProperty != null) {
			readFirstChildAsProperty(eProperty, parserContext, bean, propertyName);
		}
	}

	/**
	 * Reads a property of a bean using the first child element as the property
	 * value.
	 * 
	 * @param eBean
	 *            Bean element
	 * @param parserContext
	 *            Parser context
	 * @param bean
	 *            Bean definition
	 * @param propertyName
	 *            Name of the property element
	 */
	protected void readFirstChildAsProperty(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean, String propertyName) {
		Element ePropertyValue = DOMUtils.getFirstChildElement(eBean);
		if (ePropertyValue != null) {
			Object action = parserContext.getDelegate().parsePropertySubElement(ePropertyValue, bean.getRawBeanDefinition());
			bean.addPropertyValue(propertyName, action);
		}
	}

	protected void readAsListProperty(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean, String propertyName) {
		List list = parserContext.getDelegate().parseListElement(eBean, bean.getRawBeanDefinition());
		bean.addPropertyValue(propertyName, list);
	}

	protected void readAsNestedListProperty(Element eBean, ParserContext parserContext, BeanDefinitionBuilder bean, String propertyName) {
		Element eList = DOMUtils.getElement(eBean, propertyName);
		if (eList != null) {
			List list = parserContext.getDelegate().parseListElement(eList, bean.getRawBeanDefinition());
			bean.addPropertyValue(propertyName, list);
		}
	}

}
