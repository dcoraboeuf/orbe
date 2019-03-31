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
import orbe.model.element.ElementFlag;
import orbe.model.element.line.OrbeLine;
import orbe.model.element.line.OrbeLineList;

/**
 * création d'une ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskLineNew.java,v 1.1 2006/12/04 10:46:21 guinnessman Exp $
 */
public class OrbeTaskLineNew extends OrbeTask {

	private OrbeLine line;

	public OrbeTaskLineNew(OrbeControler orbeControler, OrbeLine line) {
		super(orbeControler);
		this.line = line;
		// Reset the text status
		line.setFlag(ElementFlag.FLAG_EDITED, false);
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
		// Refreshes the line zone (including old zone & new zone)
		LineRenderer renderer = LineRendererFactory.getInstance().getInstance(line.getForm());
		Rectangle zone = renderer.getRefreshZone(map, viewSettings, line);
		getControler().refresh(zone);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineNew.name");
	}

}
