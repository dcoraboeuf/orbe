/*
 * Created on Nov 20, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;
import java.util.List;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;
import orbe.model.element.ElementFlag;
import orbe.model.element.text.OrbeText;

public class LayerText extends AbstractLayer {

	public Layer getId() {
		return Layer.TEXT;
	}

	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		// Parcours des textes
		List<OrbeText> elements = map.getTextList().getElements();
		for (OrbeText text : elements) {
			// Ne pas dessiner les textes en cours d'édition
			if (text.getFlag(ElementFlag.FLAG_EDITED)) {
				continue;
			}
			// Calcul de la zone du texte
			Rectangle textZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
			// Intersection avec la zone de rendu ?
			if (textZone.intersects(zone)) {
				// Rendu effectif du texte
				TextRenderer.render(text, map, viewSettings, g);
			}
		}
	}

}
