/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.tool.line;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import orbe.gui.map.renderer.LineEdgeRenderer;
import orbe.gui.map.renderer.LineRendererFactory;
import orbe.gui.map.scale.ScaleMath;
import orbe.gui.map.tool.ToolLine;
import orbe.hex.HXCorner;
import orbe.hex.HXGraphics;
import orbe.model.PointDecimal;
import orbe.model.element.ElementFlag;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbeEdgeLine;

/**
 * Dessin de ligne passant par les contours d'hex.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolLineDelegatePoly.java,v 1.4 2006/12/04 10:58:46 guinnessman
 *          Exp $
 */
public class ToolLineDelegateEdge extends AbstractToolLineDelegateNew<OrbeEdgeLine> {

	/**
	 * Constructeur.
	 * 
	 * @param tool
	 *            Outil parent
	 */
	public ToolLineDelegateEdge(ToolLine tool) {
		super(tool);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Only simple clics are taken into account
		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		// 2-click => fin d'édition
		if (e.getClickCount() > 1) {
			stop();
		} else {
			// Création d'une ligne
			if (editedLine == null) {
				editedLine = new OrbeEdgeLine();
				// Style par défaut
				editedLine.setStyle(getToolSettings().getLineStyle());
				// Flag de la ligne à nouvelle
				editedLine.setFlag(ElementFlag.FLAG_NEW, true);
				// Ajout de la ligne
				getMap().getLineList().add(editedLine);
			}
			// Continuation d'une ligne
			// Conversion de coordonnées
			HXCorner h = getCorner(e);
			// Ajout du point
			editedLine.append(h);
			// Rafraichissement de la zone affectée
			@SuppressWarnings("unchecked")
			Rectangle zone = LineRendererFactory.getInstance().getInstance(LineForm.EDGE).getLastRefreshZone(getMap(), getViewSettings(), editedLine);
			if (zone != null) {
				getToolLine().getControler().refresh(zone);
			}
		}
	}

	/**
	 * Obtention d'un coin depuis une position écran.
	 */
	protected HXCorner getCorner(MouseEvent e) {
		PointDecimal px = ScaleMath.scaleScreenToPX(getMap(), getViewSettings(), e.getPoint());
		// Détermination de l'hex touché
		HXGraphics hxg = new HXGraphics(getMap().getSettings().getScale());
		HXCorner c = hxg.locateCorner(px);
		return c;
	}

	@Override
	protected void onMouseMovedForEdition(MouseEvent e) {
		// Renderer
		LineEdgeRenderer renderer = (LineEdgeRenderer) LineRendererFactory.getInstance().getInstance(LineForm.EDGE);
		// Dernier point
		HXCorner last = editedLine.getLastPoint();
		// Nouveau point
		HXCorner hx = getCorner(e);
		// Création d'une forme
		Shape path = renderer.createScreenPath(getMap(), getViewSettings(), last, hx);
		// Ajout d'une forme temporaire
		getToolLine().getControler().setEditionShape(path);
	}

}
