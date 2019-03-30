/*
 * Created on Jul 23, 2007
 */
package net.sf.doolin.gui.config;

import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;

public interface NamespaceHandlerAccessor {

	void registerDecorator(String elementName, BeanDefinitionDecorator decorator);

	void registerDecoratorForAttribute(String attributeName, BeanDefinitionDecorator decorator);

	void registerParser(String elementName, BeanDefinitionParser parser);

}
