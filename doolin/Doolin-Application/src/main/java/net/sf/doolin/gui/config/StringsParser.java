/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.config;

import java.util.List;

import net.sf.doolin.gui.core.support.StringBundles;
import net.sf.doolin.util.xml.DOMUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class StringsParser extends AbstractGUIParser {

	@Override
	protected Class getBeanClass(Element e) {
		return StringBundles.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doParse(Element eStrings, ParserContext parserContext, BeanDefinitionBuilder bean) {
		super.doParse(eStrings, parserContext, bean);
		// List of paths
		ManagedList pathList = new ManagedList();
		List<Element> ePathList = DOMUtils.getElements(eStrings, "path");
		for (Element ePath : ePathList) {
			String path = DOMUtils.getText(ePath);
			pathList.add(path);
		}
		// Set the property
		bean.addPropertyValue("bundleList", pathList);
	}

	@Override
	protected String resolveId(Element eString, AbstractBeanDefinition bean, ParserContext parserContext) throws BeanDefinitionStoreException {
		String id = super.resolveId(eString, bean, parserContext);
		if (StringUtils.isBlank(id)) {
			id = StringBundles.class.getName();
		}
		return id;
	}

}
