/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Echelle. Une unité vaut un certain nombre de kilomètres.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Scale.java,v 1.10 2006/11/09 11:22:41 guinnessman Exp $
 */
public class Scale implements Serializable {

	private static final long serialVersionUID = 1;

	private Unit unit;

	private BigDecimal value;

	private transient double pixels;
	
	public Scale() {
		unit = Unit.CM;
		value = BigDecimal.ONE;
		computePixels();
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
		computePixels();
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
		computePixels();
	}

	private void computePixels() {
		double nbPixelsPerUnit = unit.getPixels();
		pixels = nbPixelsPerUnit * this.value.doubleValue();
	}

	/**
	 * Number of pixels for this scale.
	 */
	public double toPixels() {
		return pixels;
	}
}
