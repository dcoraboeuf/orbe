/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.tool.line;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;

import orbe.gui.map.renderer.LineHexRenderer;
import orbe.gui.map.renderer.LineRendererFactory;
import orbe.gui.map.scale.ScaleMath;
import orbe.gui.map.tool.ToolLine;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.PointDecimal;
import orbe.model.element.ElementFlag;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbeHexLine;

/**
 * Dessin de ligne passant par les centres d'hex.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolLineDelegatePoly.java,v 1.4 2006/12/04 10:58:46 guinnessman
 *          Exp $
 */
public class ToolLineDelegateHex extends AbstractToolLineDelegateNew<OrbeHexLine> {

	/**
	 * Constructeur.
	 * 
	 * @param tool
	 *            Outil parent
	 */
	public ToolLineDelegateHex(ToolLine tool) {
		super(tool);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Only simple clics are taken into account
		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}
		// 2-click => fin d'�dition
		if (e.getClickCount() > 1) {
			stop();
		} else {
			// création d'une ligne
			if (editedLine == null) {
				editedLine = new OrbeHexLine();
				// Style par défaut
				editedLine.setStyle(getToolSettings().getLineStyle());
				// Flag de la ligne � nouvelle
				editedLine.setFlag(ElementFlag.FLAG_NEW, true);
				// Ajout de la ligne
				getMap().getLineList().add(editedLine);
			}
			// Continuation d'une ligne
			// Conversion de coordonn�es
			HXPoint h = getHX(e);
			// Ajout du point
			editedLine.append(h);
			// Rafraichissement de la zone affect�e
			@SuppressWarnings("unchecked")
			Rectangle zone = LineRendererFactory.getInstance().getInstance(LineForm.HEX).getLastRefreshZone(getMap(), getViewSettings(), editedLine);
			if (zone != null) {
				getToolLine().getControler().refresh(zone);
			}
		}
	}

	/**
	 * Obtention d'un hex depuis une position �cran.
	 */
	protected HXPoint getHX(MouseEvent e) {
		PointDecimal px = ScaleMath.scaleScreenToPX(getMap(), getViewSettings(), e.getPoint());
		// D�termination de l'hex touch�
		HXGraphics hxg = new HXGraphics(getMap().getSettings().getScale());
		HXPoint h = hxg.locateHex(px);
		return h;
	}

	@Override
	protected void onMouseMovedForEdition(MouseEvent e) {
		// Renderer
		LineHexRenderer renderer = (LineHexRenderer) LineRendererFactory.getInstance().getInstance(LineForm.HEX);
		// Dernier point
		HXPoint last = editedLine.getLastPoint();
		// Nouveau point
		HXPoint hx = getHX(e);
		// création d'une forme
		Shape path = renderer.createScreenPath(getMap(), getViewSettings(), last, hx);
		// Ajout d'une forme temporaire
		getToolLine().getControler().setEditionShape(path);
	}

}
