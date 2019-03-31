/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import java.awt.Rectangle;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.TextRenderer;
import orbe.model.OrbeMap;
import orbe.model.element.text.OrbeText;

/**
 * Rotation d'un texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextRotate.java,v 1.2 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskTextRotate extends OrbeTask {

	private OrbeText text;

	private int initialRotation;

	/**
	 * Constructeur.
	 * 
	 * @param orbeControler
	 *            Controleur.
	 * @param text
	 *            Texte déplacé.
	 * @param rotation
	 *            Rotation initiale.
	 */
	public OrbeTaskTextRotate(OrbeControler orbeControler, OrbeText text, int rotation) {
		super(orbeControler);
		this.text = text;
		this.initialRotation = rotation;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Old value
		int oldRotation = text.getRotation();
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		// Set the value
		text.setRotation(initialRotation);
		// Refreshes the text zone (including old zone & new zone)
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		Rectangle zone = newZone.union(oldZone);
		getControler().refresh(zone);
		// Inverts for next undo
		initialRotation = oldRotation;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTextRotate.name", text.getValue());
	}

}
