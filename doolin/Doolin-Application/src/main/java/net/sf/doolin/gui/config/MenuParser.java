/*
 * Created on Jul 23, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.core.view.Menu;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class MenuParser extends AbstractGUIParser {
	@Override
	protected Class getBeanClass(Element e) {
		return Menu.class;
	}

	@Override
	protected void doParse(Element eMenu, ParserContext parserContext, BeanDefinitionBuilder bean) {
		super.doParse(eMenu, parserContext, bean);
		readAsListProperty(eMenu, parserContext, bean, "items");
	}
}