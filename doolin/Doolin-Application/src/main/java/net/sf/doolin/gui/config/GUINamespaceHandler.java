/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.config;

import java.util.Map;
import java.util.Properties;

import net.sf.doolin.util.IOUtils;
import net.sf.doolin.util.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class GUINamespaceHandler extends NamespaceHandlerSupport implements NamespaceHandlerAccessor {
	
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(GUINamespaceHandler.class);

	public void init() {
		// Simple mappings
		Properties properties = IOUtils.readProperties("/net/sf/doolin/gui/config/Mapping.properties");
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String name = (String) entry.getKey();
			String typeName = (String) entry.getValue();
			log.debug("Mapping <" + name + "> to " + typeName);
			Class type = Utils.forClass(typeName);
			SimpleParser parser = new SimpleParser(type);
			registerBeanDefinitionParser(name, parser);
		}

		// <manager>
		registerBeanDefinitionParser("manager", new ManagerParser());
		// <startup>
		registerBeanDefinitionParser("startup", new StartupParser());
		
		// <strings>
		registerBeanDefinitionParser("strings", new StringsParser());
		
		// <text>
		registerBeanDefinitionParser("text", new TextParser());
		// <button>
		registerBeanDefinitionParser("button", new ButtonParser());
		// <combo>
		registerBeanDefinitionParser("combo", new ComboParser());
		
		// <size>
		registerBeanDefinitionDecorator("size", new SizeDecorator());
		
		// <menubar>
		registerBeanDefinitionDecorator("menubar", new MenubarDecorator());
		
		// <menu>
		registerBeanDefinitionParser("menu", new MenuParser());
		
		// <menuAction>
		registerBeanDefinitionParser("menuAction", new MenuActionParser());
	}
	
	public void registerDecorator(String elementName, BeanDefinitionDecorator decorator) {
		registerBeanDefinitionDecorator(elementName, decorator);
	};

	public void registerDecoratorForAttribute(String attributeName, BeanDefinitionDecorator decorator) {
		registerBeanDefinitionDecoratorForAttribute(attributeName, decorator);
	}

	public void registerParser(String elementName, BeanDefinitionParser parser) {
		registerBeanDefinitionParser(elementName, parser);
	}

}
