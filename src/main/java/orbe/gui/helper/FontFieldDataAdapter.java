/*
 * Created on Sep 20, 2007
 */
package orbe.gui.helper;

import java.awt.Font;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.field.helper.FieldDataAdapter;

public class FontFieldDataAdapter implements FieldDataAdapter<Font, String> {

	public String convertFieldToModel(Font fieldData) {
		return fieldData != null ? fieldData.getName() : null;
	}

	public Font convertModelToField(String modelValue) {
		return StringUtils.isNotBlank(modelValue) ? new Font(modelValue, Font.PLAIN, 1) : null;
	}
}
