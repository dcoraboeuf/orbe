/*
 * Created on Dec 1, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Color;

public class LayerUtils {

	/**
	 * Calcul d'une couleur transparente
	 */
	public static Color transparentColor(Color initial, boolean transparent) {
		if (transparent) {
			Color color = new Color(initial.getRed(), initial.getGreen(), initial.getBlue(), 128);
			return color;
		} else {
			return initial;
		}
	}
}
