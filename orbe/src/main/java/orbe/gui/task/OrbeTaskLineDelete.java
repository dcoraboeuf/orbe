/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import java.awt.Rectangle;

import net.sf.doolin.gui.service.GUIStrings;

import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.renderer.LineRenderer;
import orbe.gui.map.renderer.LineRendererFactory;
import orbe.model.OrbeMap;
import orbe.model.element.line.OrbeLine;
import orbe.model.element.line.OrbeLineList;

/**
 * Suppression d'une ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskLineDelete.java,v 1.1 2006/12/04 14:22:27 guinnessman Exp $
 */
public class OrbeTaskLineDelete extends OrbeTask {

	private OrbeLine line;

	public OrbeTaskLineDelete(OrbeControler orbeControler, OrbeLine line) {
		super(orbeControler);
		this.line = line;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Inverts the line containment
		OrbeLineList list = map.getLineList();
		if (list.contains(line)) {
			list.remove(line);
		} else {
			/*
			 * When the task is performed or redone, the test is of course at
			 * the end of the list
			 */
			list.add(line);
		}
		// Refreshes the text zone (including old zone & new zone)
		LineRenderer renderer = LineRendererFactory.getInstance().getInstance(line.getForm());
		Rectangle zone = renderer.getRefreshZone(map, viewSettings, line);
		getControler().refresh(zone);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineDelete.name");
	}

}
