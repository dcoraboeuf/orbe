/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.field.helper;


/**
 * Uses two label providers, one for the text and one for the icon.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MixedLabelInfoProvider.java,v 1.2 2007/08/15 09:05:26 guinnessman Exp $
 */
public class MixedLabelInfoProvider implements LabelInfoProvider {

	private LabelInfoProvider textProvider;

	private LabelInfoProvider iconProvider;

	/**
	 * @return Returns the icon provider.
	 */
	public LabelInfoProvider getIconProvider() {
		return iconProvider;
	}

	/**
	 * @param iconProvider
	 *            The icon provider to set.
	 */
	public void setIconProvider(LabelInfoProvider iconProvider) {
		this.iconProvider = iconProvider;
	}

	/**
	 * @return Returns the text provider.
	 */
	public LabelInfoProvider getTextProvider() {
		return textProvider;
	}

	/**
	 * @param textProvider
	 *            The text provider to set.
	 */
	public void setTextProvider(LabelInfoProvider textProvider) {
		this.textProvider = textProvider;
	}

	public LabelInfo getLabelIcon(Object item) {
		LabelInfo info = new LabelInfo();
		// Text
		if (textProvider != null) {
			LabelInfo textInfo = textProvider.getLabelIcon(item);
			if (textInfo != null) {
				info.setText(textInfo.getText());
			}
		}
		// Icon
		if (iconProvider != null) {
			LabelInfo iconInfo = iconProvider.getLabelIcon(item);
			if (iconInfo != null) {
				info.setIcon(iconInfo.getIcon());
			}
		}
		// Ok
		return info;
	}

}
