/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.awt.Toolkit;

/**
 * Unités de mesure utilisées.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Unit.java,v 1.3 2006/10/19 19:44:52 guinnessman Exp $
 */
public enum Unit {
	/**
	 * Pixel à l'écran.
	 */
	PIXEL {
		@Override
		public double getPixels() {
			return 1;
		}
	},
	/**
	 * Centimètre
	 */
	CM {
		@Override
		public double getPixels() {
			double pixelsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
			double cmPerInch = 2.54;
			return pixelsPerInch / cmPerInch;
		}
	};

	public abstract double getPixels();
}
