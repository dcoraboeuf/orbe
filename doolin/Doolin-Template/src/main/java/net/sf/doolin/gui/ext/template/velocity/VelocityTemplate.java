/*
 * Created on 21 oct. 2004
 * $Id: VelocityTemplate.java,v 1.2 2007/08/15 15:13:30 guinnessman Exp $
 */
package net.sf.doolin.gui.ext.template.velocity;

import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import net.sf.doolin.gui.ext.template.TemplateMessages;
import net.sf.doolin.gui.ext.template.support.AbstractTemplate;
import net.sf.doolin.util.CodeException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

/**
 * Velocity template
 * 
 * @version $Id: VelocityTemplate.java,v 1.2 2007/08/15 15:13:30 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class VelocityTemplate extends AbstractTemplate {
	/**
	 * Associated template
	 */
	private Template velocityTemplate;

	/**
	 * Constructor
	 * 
	 * @param aTemplate
	 *            Velocity template which is encapsulated
	 */
	public VelocityTemplate(Template aTemplate) {
		velocityTemplate = aTemplate;
	}

	/**
	 * @see VelocityContext
	 * @see Template#merge(org.apache.velocity.context.Context, Writer)
	 * @see net.sf.doolin.gui.ext.template.Template#generate(Writer, Map)
	 */
	public void generate(Writer writer, Map<String, Object> context) {
		// Velocity Context
		VelocityContext velocityContext = new VelocityContext();
		// Add parameters
		if (context != null) {
			for (Iterator i = context.entrySet().iterator(); i.hasNext();) {
				Map.Entry entry = (Map.Entry) i.next();
				String name = (String) entry.getKey();
				Object value = entry.getValue();
				velocityContext.put(name, value);
			}
		}
		// Add the Velocity tools
		velocityContext.put("tools", new VelocityTools());

		try {
			// Get the template
			velocityTemplate.merge(velocityContext, writer);
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(TemplateMessages.CANNOT_GENERATE_TEMPLATE, ex, ex);
		}
	}

}
