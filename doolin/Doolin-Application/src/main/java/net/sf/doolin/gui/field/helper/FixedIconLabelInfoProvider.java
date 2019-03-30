/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.field.helper;

import javax.swing.Icon;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * Uses an icon id to return always the same icon.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FixedIconLabelInfoProvider.java,v 1.2 2007/08/15 09:05:26 guinnessman Exp $
 */
public class FixedIconLabelInfoProvider implements LabelInfoProvider {

	private String iconId;

	private Icon icon;

	/**
	 * @return Returns the icon id.
	 */
	public String getIconId() {
		return iconId;
	}

	/**
	 * @param iconId
	 *            The icon id to set.
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public LabelInfo getLabelIcon(Object item) {
		if (icon != null) {
			return new LabelInfo(null, icon);
		} else {
			icon = GUIUtils.getIcon(iconId, IconSize.SMALL);
			return new LabelInfo(null, icon);
		}
	}

}
