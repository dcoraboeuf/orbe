/*
 * Created on Jul 23, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.core.view.MenuAction;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class MenuActionParser extends AbstractGUIParser {
	@Override
	protected Class getBeanClass(Element e) {
		return MenuAction.class;
	}

	@Override
	protected void doParse(Element eMenuAction, ParserContext parserContext, BeanDefinitionBuilder bean) {
		readFirstChildAsProperty(eMenuAction, parserContext, bean, "action");
	}
}