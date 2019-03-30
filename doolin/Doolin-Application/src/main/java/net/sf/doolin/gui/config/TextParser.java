/*
 * Created on Jul 21, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.field.FieldText;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class TextParser extends AbstractGUIParser {

	@Override
	protected Class getBeanClass(Element eText) {
		return FieldText.class;
	}

	@Override
	protected void doParse(Element eText, ParserContext parserContext, BeanDefinitionBuilder bean) {
		super.doParse(eText, parserContext, bean);
		// Validators
		readAsNestedListProperty(eText, parserContext, bean, "validators");
	}

}
