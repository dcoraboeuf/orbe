/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.List;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbeLine;

/**
 * Dessin des lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayerLine.java,v 1.1 2006/11/30 17:09:28 guinnessman Exp $
 */
public class LayerLine extends AbstractLayer {

	public Layer getId() {
		return Layer.LINE;
	}

	@SuppressWarnings("unchecked")
	public void paint(OrbeMap map, ViewSettings viewSettings, Graphics2D g, Double zone) {
		// Parcours des lignes
		List<OrbeLine> elements = map.getLineList().getElements();
		for (OrbeLine line : elements) {
			// Forme de la ligne
			LineForm form = line.getForm();
			// Associated renderer
			LineRenderer renderer = LineRendererFactory.getInstance().getInstance(form);
			// Calcul de la zone de la ligne
			if (renderer.hasToRefresh(line, map, viewSettings, zone)) {
				// Rendu effectif de la ligne
				renderer.render(line, map, viewSettings, g);
			}
		}
	}

}
