/*
 * Created on Jul 25, 2007
 */
package net.sf.doolin.gui.field.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.util.Utils;

/**
 * Label provider for a date.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DateLabelInfoProvider.java,v 1.3 2007/08/15 09:05:26 guinnessman Exp $
 */
public class DateLabelInfoProvider implements LabelInfoProvider {

	private String style = null;

	private String timeStyle = null;

	private String format = null;

	public LabelInfo getLabelIcon(Object item) {
		DateFormat dateFormat = getDateFormat();
		String text = dateFormat.format(item);
		return new LabelInfo(text, null);
	}

	/**
	 * Computes the <code>DataFormat</code> to use.
	 * 
	 * If the <code>format</code> attribute is set, is is used to create a
	 * <code>{@link SimpleDateFormat}</code> instance.
	 * 
	 * If either <code>style</code> or <code>timeStyle</code> attribute is
	 * set, a <code>DateFormat</code> instance is created from the style (see
	 * <code>{@link DateFormat#getDateInstance(int)}</code>,
	 * <code>{@link DateFormat#getDateTimeInstance(int, int)}</code> and
	 * <code>{@link DateFormat#getTimeInstance(int)}</code>).
	 * 
	 * If no attribute is set, a default <code>DateFormat</code> instance is
	 * created (see <code>{@link DateFormat#getDateInstance()}</code>).
	 * 
	 * @return <code>DateFormat</code> instance.
	 */
	protected DateFormat getDateFormat() {
		if (StringUtils.isNotBlank(format)) {
			return new SimpleDateFormat(format);
		} else if (StringUtils.isNotBlank(style)) {
			int formatStyle = (Integer) Utils.getConstant(DateFormat.class, style);
			if (StringUtils.isNotBlank(timeStyle)) {
				int formatTimeStyle = (Integer) Utils.getConstant(DateFormat.class, style);
				return DateFormat.getDateTimeInstance(formatStyle, formatTimeStyle);
			} else {
				return DateFormat.getDateInstance(formatStyle);
			}
		} else if (StringUtils.isNotBlank(timeStyle)) {
			int formatTimeStyle = (Integer) Utils.getConstant(DateFormat.class, style);
			return DateFormat.getTimeInstance(formatTimeStyle);
		} else {
			return DateFormat.getDateInstance();
		}
	}

	/**
	 * Returns the <code>timeStyle</code> property.
	 * 
	 * @return <code>timeStyle</code> property.
	 */
	public String getTimeStyle() {
		return timeStyle;
	}

	/**
	 * Sets the <code>timeStyle</code> property.
	 * 
	 * @param timeStyle
	 *            <code>timeStyle</code> property.
	 */
	public void setTimeStyle(String timeStyle) {
		this.timeStyle = timeStyle;
	}

	/**
	 * Returns the <code>format</code> property.
	 * 
	 * @return <code>format</code> property.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the <code>format</code> property.
	 * 
	 * @param format
	 *            <code>format</code> property.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Returns the <code>style</code> property.
	 * 
	 * @return <code>style</code> property.
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Sets the <code>style</code> property.
	 * 
	 * @param style
	 *            <code>style</code> property.
	 */
	public void setStyle(String style) {
		this.style = style;
	}

}
