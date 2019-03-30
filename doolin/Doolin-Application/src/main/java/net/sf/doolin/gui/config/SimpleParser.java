/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.config;

import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SimpleParser extends AbstractSimpleBeanDefinitionParser {
	
	private Class type;
	
	public SimpleParser(Class aType) {
		type = aType;
	}

	@Override
	protected Class getBeanClass(Element e) {
		return type;
	}
	
}
