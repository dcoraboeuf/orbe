/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.helper;

import org.apache.commons.lang.ObjectUtils;

public class DefaultLabelInfoProvider implements LabelInfoProvider {

	public LabelInfo getLabelIcon(Object item) {
		String value = ObjectUtils.toString(item, "");
		return new LabelInfo (value, null);
	}

}
