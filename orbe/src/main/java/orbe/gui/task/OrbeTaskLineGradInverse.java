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

/**
 * Inversion de la graduation sur une ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskLineGradInverse.java,v 1.1 2006/12/04 14:33:41 guinnessman Exp $
 */
public class OrbeTaskLineGradInverse extends OrbeTask {

	private OrbeLine line;

	public OrbeTaskLineGradInverse(OrbeControler orbeControler, OrbeLine line) {
		super(orbeControler);
		this.line = line;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Inverts the graduation
		boolean status = line.isGradInverse();
		line.setGradInverse(!status);
		// Refreshes the line zone
		LineRenderer renderer = LineRendererFactory.getInstance().getInstance(line.getForm());
		Rectangle zone = renderer.getRefreshZone(map, viewSettings, line);
		getControler().refresh(zone);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineGradInverse.name");
	}

}
