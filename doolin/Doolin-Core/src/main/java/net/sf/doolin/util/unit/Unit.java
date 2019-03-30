/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.util.unit;

import java.awt.Toolkit;

/**
 * Different types of units.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Unit.java,v 1.1 2007/07/31 15:32:49 guinnessman Exp $
 */
public enum Unit {

	/**
	 * Percentage
	 */
	PERCENT("%") {
		@Override
		public double getPixels(Number reference) {
			return reference.doubleValue() / 100.0;
		}
	},

	/**
	 * Pixel
	 */
	PIXEL("px") {
		@Override
		public double getPixels(Number reference) {
			return 1;
		}
	},

	/**
	 * Centimeter
	 */
	CM("cm") {
		@Override
		public double getPixels(Number reference) {
			double pixelsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
			double cmPerInch = 2.54;
			return pixelsPerInch / cmPerInch;
		}
	},

	/**
	 * Inch
	 */
	INCH("\"") {
		@Override
		public double getPixels(Number reference) {
			double pixelsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
			return pixelsPerInch;
		}
	};

	private String symbol;

	private Unit(String aSymbol) {
		symbol = aSymbol;
	}

	/**
	 * @return Associated symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Calculates the number of corresponding pixels for this unit, according to
	 * a reference. This reference is used only for percentage unit.
	 * 
	 * @param reference
	 *            Reference value
	 * @return Number of pixels
	 */
	public abstract double getPixels(Number reference);

}
