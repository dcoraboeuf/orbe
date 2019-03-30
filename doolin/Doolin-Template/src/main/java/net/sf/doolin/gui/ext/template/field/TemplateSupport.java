/*
 * Created on 15 aoutt 07
 */
package net.sf.doolin.gui.ext.template.field;

import java.util.Map;

import net.sf.doolin.gui.ext.template.Template;
import net.sf.doolin.gui.field.support.FieldSupport;

/**
 * Support for the <code>{@link FieldTemplate}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TemplateSupport.java,v 1.1 2007/08/15 15:13:30 guinnessman Exp $
 */
public interface TemplateSupport extends FieldSupport<FieldTemplate> {

	/**
	 * Sets the execution context for the template
	 * 
	 * @param context
	 *            Map of variables forthe template
	 * @see Template#generate(java.io.OutputStream, Map)
	 */
	void setContext(Map<String, Object> context);

}
