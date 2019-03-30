/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.tool.text;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;
import orbe.gui.cursor.ICursors;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.TextRenderer;
import orbe.gui.map.tool.ToolText;
import orbe.gui.task.OrbeTaskTextRotate;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.text.OrbeText;

/**
 * Délégation pour la gestion du mouvement.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolTextDelegateRotate.java,v 1.1 2006/11/23 17:15:33
 *          guinnessman Exp $
 */
public class ToolTextDelegateRotate extends ToolTextDelegate {

	/**
	 * Texte déplacé.
	 */
	private OrbeText text;

	/**
	 * Position initiale, utilisée pour back-up.
	 */
	private int initialRotation;

	/**
	 * Offset de rotation initiale, détecté au moment de l'initialisation du
	 * mouvement.
	 */
	private int initialOffset;

	private Cursor cursorRotate;

	private OrbeMap map;

	private ViewSettings viewSettings;

	private Graphics2D g;

	/**
	 * Initialisation.
	 * 
	 * @param tool
	 *            Outil parent.
	 * @param text
	 *            Texte déplacé.
	 */
	public ToolTextDelegateRotate(ToolText tool, OrbeText text) {
		super(tool);
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		cursorRotate = cursorFactory.getCursor(ICursors.ROTATE);
		map = tool.getControler().getContext().getMap();
		viewSettings = tool.getControler().getView().getViewSettings();
		g = tool.getControler().getGraphics2D();
		this.text = text;
	}

	@Override
	public void start(MouseEvent e) {
		initialRotation = text.getRotation();
		initialOffset = getAngle(e.getPoint());
	}

	/**
	 * Calcul de l'angle entre le point et le référentiel du texte.
	 * 
	 * @param p
	 *            Point
	 * @return Angle en degrés.
	 */
	protected int getAngle(Point p) {
		// Conversion dans le référentiel PX
		PointDecimal px = getTool().getPX(p);
		// Position du texte dans le référentiel PX
		PointDecimal txtPX = TextRenderer.getTextPXPosition(text, map, getTool().getControler().getView().getViewSettings());
		// Offset
		double x = px.x - txtPX.x;
		double y = px.y - txtPX.y;
		// Calcul de l'angle en radians
		double theta;
		if (x == 0.0) {
			if (y > 0.0) {
				theta = Math.PI / 2;
			} else if (y < 0.0) {
				theta = 3 * Math.PI / 2;
			} else {
				theta = 0.0;
			}
		} else {
			theta = Math.atan2(y, x);
		}
		// Normalisation
		if (theta < 0) {
			theta += 2 * Math.PI;
		}
		// Conversion en degrés
		int angle = (int) (theta * 180 / Math.PI);
		// Ok
		return angle;
	}

	@Override
	public void drag(MouseEvent e) {
		getTool().setCursor(cursorRotate);
		// Angle du point courant
		int angle = getAngle(e.getPoint());
		// Nouvel angle
		int rotation = initialRotation + angle - initialOffset;
		// Contrainte ?
		if (e.isShiftDown()) {
			rotation = constraint(rotation);
		}
		// Changement
		change(rotation);
	}

	/**
	 * Contrainte de l'angle aux 45° les plus proches.
	 * 
	 * @param rotation
	 *            Angle non contraint.
	 * @return Angle contraint.
	 */
	protected int constraint(int rotation) {
		return ((rotation - 22) / 45) * 45;
	}

	/**
	 * Changement de l'angle
	 */
	protected void change(int rotation) {
		// Ancienne zone de texte
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
		// Rotation
		text.setRotation(rotation);
		// Nouvelle zone
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
		// Rafraichissement
		Rectangle zone = oldZone.union(newZone);
		getTool().getControler().refresh(zone);
	}

	@Override
	public void end(MouseEvent e) {
		// Création d'une tâche de rotation
		OrbeTaskTextRotate task = new OrbeTaskTextRotate(getTool().getControler(), text, initialRotation);
		getTool().getControler().getContext().addEdit(task);
	}

	@Override
	public void cancel() {
		// Restauration de l'ancienne position rotation
		change(initialRotation);
	}

}
