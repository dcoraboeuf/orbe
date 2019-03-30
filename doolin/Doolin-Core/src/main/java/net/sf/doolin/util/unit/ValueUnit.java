/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.util.unit;

import java.math.BigDecimal;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Numeric value associated with a unit.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ValueUnit.java,v 1.1 2007/07/31 15:32:49 guinnessman Exp $
 */
public class ValueUnit {

	private Number value;

	private Unit unit;

	/**
	 * Constructor with a predefined value and unit.
	 * 
	 * @param value
	 *            Numeric value
	 * @param unit
	 *            Unit
	 */
	public ValueUnit(Number value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}

	/**
	 * Constructor with no predefined value or unit.
	 */
	public ValueUnit() {
	}

	/**
	 * @return Associated unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            Associated unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * @return Associated value
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * @param value
	 *            Associated value
	 */
	public void setValue(Number value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + unit.getSymbol();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ValueUnit) {
			ValueUnit vu = (ValueUnit) obj;
			return ObjectUtils.equals(this.unit, vu.unit) && ObjectUtils.equals(this.value, vu.value);
		} else {
			return false;
		}
	}

	/**
	 * Converts a formatted string to a numeric value and a unit.
	 * 
	 * @param string
	 *            Formatted string.
	 * @return Value + unit or <code>null</code> if the string is empty
	 * @throws IllegalArgumentException
	 *             If the provided string cannot be parsed
	 */
	public static ValueUnit valueOf(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		} else {
			Unit[] units = Unit.values();
			for (Unit unit : units) {
				String symbol = unit.getSymbol();
				if (string.endsWith(symbol)) {
					String numericValue = StringUtils.substringBefore(string, symbol);
					try {
						Number number = new BigDecimal(numericValue);
						return new ValueUnit(number, unit);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Cannot parse " + string, ex);
					}
				}
			}
			throw new IllegalArgumentException("Cannot parse " + string);
		}
	}

	/**
	 * Converts this value to another unit.
	 * 
	 * @param targetUnit
	 *            Target unit
	 * @param reference
	 *            Reference value for the percentage unit
	 * @return New value
	 */
	public ValueUnit convert(Unit targetUnit, Number reference) {
		// No need of conversion for same units
		if (this.unit == targetUnit) {
			return new ValueUnit(value, unit);
		} else if (this.unit == Unit.PERCENT) {
			/*
			 * Special case
			 * 
			 * The new value is a percentage of the reference
			 */
			double newValue = value.doubleValue() * reference.doubleValue() / 100;
			return new ValueUnit(newValue, targetUnit);
		} else {
			// Converts current value to pixels
			double pixels = value.doubleValue() * unit.getPixels(reference);
			// Converts pixels to new unit
			Number newValue = pixels / targetUnit.getPixels(reference);
			// Ok
			return new ValueUnit(newValue, targetUnit);
		}
	}

}
