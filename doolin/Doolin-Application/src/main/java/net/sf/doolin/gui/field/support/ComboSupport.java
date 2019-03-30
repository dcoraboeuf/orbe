/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.support;

import net.sf.doolin.gui.field.FieldCombo;
import net.sf.doolin.gui.field.helper.FieldDataAdapter;

public interface ComboSupport extends FieldSupport<FieldCombo> {

	Object getSelectedItem();

	void setSelectedItem(Object fieldData, FieldDataAdapter fieldDataAdapter);

}
