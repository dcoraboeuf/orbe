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
import orbe.model.element.ElementFlag;
import orbe.model.element.text.OrbeText;

/**
 * Changement de la valeur d'un texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskText.java,v 1.4 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskText extends OrbeTask {

	private OrbeText text;

	private String value;

	public OrbeTaskText(OrbeControler orbeControler, OrbeText text, String value) {
		super(orbeControler);
		this.text = text;
		this.value = value;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Old value
		String oldValue = text.getValue();
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		// Set the value
		text.setValue(value);
		// Reset the text status
		text.setFlag(ElementFlag.FLAG_EDITED, false);
		// Refreshes the text zone (including old zone & new zone)
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		Rectangle zone = newZone.union(oldZone);
		getControler().refresh(zone);
		// Inverts for next undo
		value = oldValue;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskText.name");
	}

}
