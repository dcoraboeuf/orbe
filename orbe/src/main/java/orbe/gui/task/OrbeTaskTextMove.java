/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import java.awt.Rectangle;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.TextRenderer;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.element.text.OrbeText;

/**
 * Changement de la valeur d'un texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextMove.java,v 1.4 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskTextMove extends OrbeTask {

	private OrbeText text;

	private HXPoint2D initialPosition;

	/**
	 * Constructeur.
	 * 
	 * @param orbeControler
	 *            Controleur.
	 * @param text
	 *            Texte déplacé.
	 * @param position
	 *            Position initiale.
	 */
	public OrbeTaskTextMove(OrbeControler orbeControler, OrbeText text, HXPoint2D position) {
		super(orbeControler);
		this.text = text;
		this.initialPosition = position;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Old value
		HXPoint2D oldPosition = text.getPosition();
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		// Set the value
		text.setPosition(initialPosition);
		// Refreshes the text zone (including old zone & new zone)
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		Rectangle zone = newZone.union(oldZone);
		getControler().refresh(zone);
		// Inverts for next undo
		initialPosition = oldPosition;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTextMove.name", text.getValue());
	}

}
