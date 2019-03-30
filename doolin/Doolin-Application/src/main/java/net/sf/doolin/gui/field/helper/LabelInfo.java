/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.helper;

import javax.swing.Icon;

/**
 * Association of a text and an icon
 * 
 * @author Damien Coraboeuf
 * @version $Id: LabelInfo.java,v 1.1 2007/07/31 15:33:02 guinnessman Exp $
 */
public class LabelInfo {

	private String text;

	private Icon iconObject;

	public LabelInfo(String text, Icon iconObject) {
		this.text = text;
		this.iconObject = iconObject;
	}

	public LabelInfo() {
	}

	public Icon getIcon() {
		return iconObject;
	}

	public void setIcon(Icon iconObject) {
		this.iconObject = iconObject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
