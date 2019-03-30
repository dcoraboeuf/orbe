/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template;

import java.io.Writer;
import java.util.Map;

/**
 * Reusable template.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Template.java,v 1.2 2007/08/15 15:13:32 guinnessman Exp $
 */
public interface Template {

	/**
	 * Generates the template to an output stream using a given context.
	 * 
	 * @param out
	 *            Writer to generate the template in.
	 * @param context
	 *            Template context, containing all needed variables.
	 */
	void generate(Writer out, Map<String, Object> context);

}
