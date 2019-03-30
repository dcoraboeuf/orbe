/*
 * Created on 20 avr. 2005
 */
package net.sf.doolin.gui.ext.template.velocity;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.doolin.gui.service.GUIStrings;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Some tools for the template
 * 
 * @author guinnessman
 * @version $Id: VelocityTools.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
public class VelocityTools {
	/**
	 * Escape a string for XML
	 * 
	 * @param value
	 *            Value to escape
	 * @return Escaped value
	 * @see StringEscapeUtils#escapeXml(String)
	 */
	public String escapeXML(String value) {
		return StringEscapeUtils.escapeXml(value);
	}

	/**
	 * Escape Java string to HTML. All carriage returns are replaced by
	 * <code>&lt;br/&gt;</code>.
	 * 
	 * @param value
	 *            Java string
	 * @return Escaped HTML string
	 * @see StringEscapeUtils#escapeHtml(String)
	 */
	public String translateJavaToHTML(String value) {
		if (value == null) {
			return "";
		}
		// Escape HTML
		value = StringEscapeUtils.escapeHtml(value);
		// Transforms each carriage return to <br>
		value = StringUtils.replace(value, "\n", "<br>");
		// Ok
		return value;
	}

	/**
	 * Format a date using the specified format
	 * 
	 * @param date
	 *            Date to format
	 * @param format
	 *            Format to use
	 * @return Formatted date
	 */
	public String dateFormat(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Translates a string code to a full text.
	 * 
	 * @param code
	 *            String code
	 * @return Corresponding text
	 * @see GUIStrings#get(String, Object[])
	 */
	public String getString(String code) {
		return GUIStrings.get(code);
	}

}
