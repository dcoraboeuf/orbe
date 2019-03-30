/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.oxml.adapter;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.util.xml.DOMUtils;

import org.w3c.dom.Element;

/**
 * Creates an instance from the text of the current element.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * @param <T>
 *            Type of the instance to create
 */
public abstract class AbstractTextOXMLInstanceFactory<T> extends AbstractOXMLInstanceFactory<T> {

	@Override
	protected T createInstance(Element element, OXMLContext context) {
		// Gets the element text
		String text = DOMUtils.getText(element);
		// Conversion
		return createInstance(text, context);
	}

	/**
	 * Creates the instance from the current text
	 * 
	 * @param text
	 *            Current element text
	 * @param context
	 *            Execution context
	 * @return Instance
	 */
	protected abstract T createInstance(String text, OXMLContext context);

}
