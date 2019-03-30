/*
 * Created on Jul 21, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.field.FieldCombo;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ComboParser extends AbstractGUIParser {

	@Override
	protected Class getBeanClass(Element eText) {
		return FieldCombo.class;
	}

	@Override
	protected void doParse(Element eText, ParserContext parserContext, BeanDefinitionBuilder bean) {
		super.doParse(eText, parserContext, bean);
		// Properties
		readNestedProperty(eText, parserContext, bean, "itemProvider");
		readNestedProperty(eText, parserContext, bean, "labelProvider");
	}

}
