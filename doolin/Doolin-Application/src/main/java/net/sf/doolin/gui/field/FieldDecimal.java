/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import java.math.BigDecimal;

import net.sf.doolin.gui.field.support.DecimalSupport;

/**
 * This field allows the input of a decimal number (as an instance of a
 * <code>BigDecimal</code>).
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class FieldDecimal extends AbstractSupportField<DecimalSupport> {

	private BigDecimal min = null;

	private BigDecimal max = null;

	private int fractionDigits = 2;

	private int integerDigits = 10;

	private boolean fractionShowAll = true;

	public void setFieldData(Object formData, Object fieldData) {
		BigDecimal value = (BigDecimal) fieldData;
		getSupport().setValue(value);
	}

	@Override
	protected DecimalSupport createSupport() {
		return createSupport(DecimalSupport.class);
	}

	public Object getFieldData(Object formData) {
		return getSupport().getValue();
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public int getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(int fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public boolean isFractionShowAll() {
		return fractionShowAll;
	}

	public void setFractionShowAll(boolean fractionShowAll) {
		this.fractionShowAll = fractionShowAll;
	}

	public int getIntegerDigits() {
		return integerDigits;
	}

	public void setIntegerDigits(int integerDigits) {
		this.integerDigits = integerDigits;
	}
}
