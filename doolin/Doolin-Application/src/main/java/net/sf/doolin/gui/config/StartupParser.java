/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.config;

import net.sf.doolin.gui.core.support.FrameStartup;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class StartupParser extends AbstractGUIParser {

	@Override
	protected Class getBeanClass(Element e) {
		return FrameStartup.class;
	}

	@Override
	protected void doParse(Element e, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(e, parserContext, builder);
		// Frame reference
		String frame = e.getAttribute("frame");
		builder.addPropertyReference("frame", frame);
		// Frame data factory reference
		String frameDataFactory = e.getAttribute("frameDataFactory");
		if (StringUtils.isNotBlank(frameDataFactory)) {
			builder.addPropertyReference("frameDataFactory", frameDataFactory);
		}
	}

}
