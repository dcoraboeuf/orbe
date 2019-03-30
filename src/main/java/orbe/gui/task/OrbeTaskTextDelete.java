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
import orbe.model.element.text.OrbeTextList;

/**
 * Suppression d'un texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextDelete.java,v 1.3 2006/11/29 15:22:24 guinnessman Exp $
 */
public class OrbeTaskTextDelete extends OrbeTask {

	private OrbeText text;

	public OrbeTaskTextDelete(OrbeControler orbeControler, OrbeText text) {
		super(orbeControler);
		this.text = text;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Inverts the text containment
		OrbeTextList list = map.getTextList();
		if (list.contains(text)) {
			list.remove(text);
		} else {
			/*
			 * When the task is performed or redone, the test is of course at
			 * the end of the list
			 */
			list.add(text);
		}
		// Refreshes the text zone (including old zone & new zone)
		Rectangle zone = TextRenderer.getRefreshingScreenZone(text, map, viewSettings, getControler().getGraphics2D());
		getControler().refresh(zone);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTextDelete.name", text.getValue());
	}

}
