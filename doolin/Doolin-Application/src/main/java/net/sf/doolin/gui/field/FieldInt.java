/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import net.sf.doolin.gui.field.support.IntSupport;

public class FieldInt extends AbstractSupportField<IntSupport> {

	private Integer min = null;

	private Integer max = null;

	public void setFieldData(Object formData, Object fieldData) {
		int value = (Integer) fieldData;
		getSupport().setValue(value);
	}

	@Override
	protected IntSupport createSupport() {
		return createSupport(IntSupport.class);
	}

	public Object getFieldData(Object formData) {
		return getSupport().getValue();
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}
}
