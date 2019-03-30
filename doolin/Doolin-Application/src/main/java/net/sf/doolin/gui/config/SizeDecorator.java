/*
 * Created on Jul 23, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.core.view.DefaultSize;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SizeDecorator implements BeanDefinitionDecorator {

	public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder bean, ParserContext parserContext) {
		Element eSize = (Element) node;

		RootBeanDefinition definition = new RootBeanDefinition(DefaultSize.class);
		setAttribute(eSize, definition, "width");
		setAttribute(eSize, definition, "height");
		setAttribute(eSize, definition, "maximized");
		setAttribute(eSize, definition, "saveInPrefs");
		setAttribute(eSize, definition, "pack");

		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(definition, "size");
		bean.getBeanDefinition().getPropertyValues().addPropertyValue("size", definitionHolder);

		return bean;
	}

	protected void setAttribute(Element eSize, RootBeanDefinition definition, String attName) {
		String attribute = eSize.getAttribute(attName);
		if (StringUtils.isNotBlank(attribute)) {
			definition.getPropertyValues().addPropertyValue(attName, attribute);
		}
	}
}