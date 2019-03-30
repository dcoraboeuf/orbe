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
import orbe.model.style.TextStyle;

/**
 * Changement de la valeur d'un texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextStyle.java,v 1.3 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskTextStyle extends OrbeTask {

	private OrbeText text;

	private TextStyle style;

	public OrbeTaskTextStyle(OrbeControler orbeControler, OrbeText text, TextStyle style) {
		super(orbeControler);
		this.text = text;
		this.style = style;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Old value
		TextStyle oldStyle = text.getStyle();
		Rectangle oldZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		// Set the value
		text.setStyle(style);
		// Refreshes the text zone (including old zone & new zone)
		Rectangle newZone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		Rectangle zone = newZone.union(oldZone);
		getControler().refresh(zone);
		// Inverts for next undo
		style = oldStyle;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTextStyle.name", text.getValue());
	}

}
