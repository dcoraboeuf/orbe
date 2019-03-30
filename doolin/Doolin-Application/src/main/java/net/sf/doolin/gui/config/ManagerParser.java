/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.support.DefaultApplicationManager;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ManagerParser extends AbstractGUIParser {

	@Override
	protected void doParse(Element e, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(e, parserContext, builder);
		builder.setLazyInit(true);
		// Startup
		readProperty(e, parserContext, builder, "startup");
		// Plugins
		readAsNestedListProperty(e, parserContext, builder, "plugins");
		// Other properties
		readProperties(e, parserContext, builder);
	}

	@Override
	protected Class getBeanClass(Element e) {
		return DefaultApplicationManager.class;
	}

	@Override
	protected String resolveId(Element e, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
		return ApplicationManager.class.getName();
	}

}
