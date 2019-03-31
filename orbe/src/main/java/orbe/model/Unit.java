/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.awt.Toolkit;

/**
 * Unit�s de mesure utilis�es.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Unit.java,v 1.3 2006/10/19 19:44:52 guinnessman Exp $
 */
public enum Unit {
	/**
	 * Pixel � l'�cran.
	 */
	PIXEL {
		@Override
		public double getPixels() {
			return 1;
		}
	},
	/**
	 * Centimêtre
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
