/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.util.Strings;

/**
 * Utility methods for the template management.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TemplateUtils.java,v 1.2 2007/08/15 15:13:32 guinnessman Exp $
 */
public class TemplateUtils {

	/**
	 * Bundle
	 */
	static {
		Strings.add("net.sf.doolin.gui.ext.template.TemplateBundle");
	}

	/**
	 * Gets the template manager instance
	 * 
	 * @return Template manager
	 * @see GUIUtils#getService(Class)
	 */
	public static TemplateManager getTemplateManager() {
		return GUIUtils.getService(TemplateManager.class);
	}

	/**
	 * Generates a string from a template.
	 * 
	 * @param template
	 *            Template to generate from
	 * @param context
	 *            Template context
	 * @return Generated string
	 */
	public static String generateString(Template template, Map<String, Object> context) {
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);
		try {
			try {
				template.generate(writer, context);
			} finally {
				writer.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException("Cannot generate template into a string", ex);
		}
		// Converts to string
		return stringWriter.toString();
	}

}
