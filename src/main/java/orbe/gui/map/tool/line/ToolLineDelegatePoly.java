/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.tool.line;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import orbe.gui.map.renderer.LineRendererFactory;
import orbe.gui.map.scale.ScaleMath;
import orbe.gui.map.tool.ToolLine;
import orbe.hex.HXPoint2D;
import orbe.model.PointDecimal;
import orbe.model.element.ElementFlag;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbePolyLine;

/**
 * Dessin des ligne libres.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolLineDelegatePoly.java,v 1.7 2006/12/04 14:22:26 guinnessman Exp $
 */
public class ToolLineDelegatePoly extends AbstractToolLineDelegateNew<OrbePolyLine> {

	/**
	 * Constructeur.
	 * 
	 * @param tool
	 *            Outil parent
	 */
	public ToolLineDelegatePoly(ToolLine tool) {
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
				editedLine = new OrbePolyLine();
				// Style par défaut
				editedLine.setStyle(getToolSettings().getLineStyle());
				// Flag de la ligne à nouvelle
				editedLine.setFlag(ElementFlag.FLAG_NEW, true);
				// Ajout de la ligne
				getMap().getLineList().add(editedLine);
			}
			// Continuation d'une ligne
			// Conversion de coordonnées
			PointDecimal px = ScaleMath.scaleScreenToPX(getMap(), getViewSettings(), e.getPoint());
			HXPoint2D hx = ScaleMath.scalePXToHX(getMap(), px);
			// Ajout du point
			editedLine.append(hx);
			// Rafraichissement de la zone affectée
			@SuppressWarnings("unchecked")
			Rectangle zone = LineRendererFactory.getInstance().getInstance(LineForm.POLY).getLastRefreshZone(getMap(), getViewSettings(), editedLine);
			if (zone != null) {
				getToolLine().getControler().refresh(zone);
			}
		}
	}

	@Override
	protected void onMouseMovedForEdition(MouseEvent e) {
		if (editedLine != null) {
			// Dernier point
			HXPoint2D hx = editedLine.getLastPoint();
			// Conversion de coordonnées
			PointDecimal px = ScaleMath.scaleHXToPX(getMap(), hx);
			Point p = ScaleMath.scalePXToScreen(getMap(), getViewSettings(), px);
			// Création d'une forme
			GeneralPath path = new GeneralPath();
			path.moveTo(p.x, p.y);
			path.lineTo(e.getX(), e.getY());
			// Ajout d'une forme temporaire
			getToolLine().getControler().setEditionShape(path);
		}
	}

}
