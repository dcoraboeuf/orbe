/*
 * Created on Jul 21, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.field.FieldButton;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ButtonParser extends AbstractGUIParser {

	@Override
	protected Class getBeanClass(Element eText) {
		return FieldButton.class;
	}

	@Override
	protected void doParse(Element eText, ParserContext parserContext, BeanDefinitionBuilder bean) {
		super.doParse(eText, parserContext, bean);
		// Action
		readNestedProperty(eText, parserContext, bean, "action");
	}

}
