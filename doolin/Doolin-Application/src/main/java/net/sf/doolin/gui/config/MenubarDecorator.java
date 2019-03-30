/*
 * Created on Jul 23, 2007
 */
package net.sf.doolin.gui.config;

import java.util.List;

import net.sf.doolin.gui.core.view.Menubar;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MenubarDecorator implements BeanDefinitionDecorator {
	public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder bean, ParserContext parserContext) {
		RootBeanDefinition definition = new RootBeanDefinition(Menubar.class);
		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(definition, "menubar");
		bean.getBeanDefinition().getPropertyValues().addPropertyValue("menubar", definitionHolder);

		Element eMenubar = (Element) node;
		List menuList = parserContext.getDelegate().parseListElement(eMenubar, definition);
		definition.getPropertyValues().addPropertyValue("menus", menuList);

		return bean;
	}
}