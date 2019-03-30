/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.tool.text;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.TextRenderer;
import orbe.gui.map.tool.ToolText;
import orbe.gui.task.OrbeTaskTextMove;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.element.text.OrbeText;

/**
 * Délégation pour la gestion du mouvement.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolTextDelegateMove.java,v 1.4 2006/11/29 15:01:21 guinnessman
 *          Exp $
 */
public class ToolTextDelegateMove extends ToolTextDelegate {

	/**
	 * Texte déplacé.
	 */
	private OrbeText text;

	/**
	 * Position initiale, utilisée pour back-up.
	 */
	private HXPoint2D initialPosition;

	/**
	 * Offset initial, entre l'ancre du texte et l'emplacement de l'événement.
	 */
	private HXPoint2D initialOffset;

	private Cursor cursorMove;

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
	public ToolTextDelegateMove(ToolText tool, OrbeText text) {
		super(tool);
		cursorMove = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		map = tool.getControler().getContext().getMap();
		viewSettings = tool.getControler().getView().getViewSettings();
		g = tool.getControler().getGraphics2D();
		this.text = text;
	}

	@Override
	public void start(MouseEvent e) {
		initialPosition = text.getPosition();
		HXPoint2D position = getTool().getHX(e.getPoint());
		initialOffset = new HXPoint2D(position.x - initialPosition.x, position.y - initialPosition.y);
	}

	/**
	 * Déplacement du texte vers le point indiqué.
	 * 
	 * @param point
	 *            Nouvelle position.
	 */
	protected void moveTo(Point point, boolean constraint) {
		// Nouvelle position
		HXPoint2D px = getTool().getHX(point);
		// Application de l'offset
		px.x -= initialOffset.x;
		px.y -= initialOffset.y;
		// Application de contraintes
		if (constraint) {
			double dx = Math.abs(px.x - initialPosition.x);
			double dy = Math.abs(px.y - initialPosition.y);
			if (dx > dy) {
				px.y = initialPosition.y;
			} else {
				px.x = initialPosition.x;
			}
		}
		// Déplacement
		moveTo(px);
	}

	protected void moveTo(HXPoint2D px) {
		// Ancienne zone de texte
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
		// Déplacement
		text.setPosition(px);
		// Nouvelle zone
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
		// Rafraichissement
		Rectangle zone = oldZone.union(newZone);
		getTool().getControler().refresh(zone);
	}

	@Override
	public void drag(MouseEvent e) {
		getTool().setCursor(cursorMove);
		boolean constraint = e.isShiftDown();
		moveTo(e.getPoint(), constraint);
		// Ensure that text remains visible in the view
		Rectangle zone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, g);
		getTool().getControler().ensureVisibility(zone);
	}

	@Override
	public void end(MouseEvent e) {
		// Création d'une tâche de déplacement
		OrbeTaskTextMove task = new OrbeTaskTextMove(getTool().getControler(), text, initialPosition);
		getTool().getControler().getContext().addEdit(task);
	}

	@Override
	public void cancel() {
		// Restauration de l'ancienne position
		moveTo(initialPosition);
	}

}
