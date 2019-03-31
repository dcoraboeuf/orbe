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
import orbe.model.style.StyleLine;

/**
 * Changement du style d'une ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskLineStyle.java,v 1.1 2006/12/04 15:17:16 guinnessman Exp $
 */
public class OrbeTaskLineStyle extends OrbeTask {

	private OrbeLine line;

	private StyleLine style;

	public OrbeTaskLineStyle(OrbeControler orbeControler, OrbeLine line, StyleLine style) {
		super(orbeControler);
		this.line = line;
		this.style = style;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		// Renderer
		LineRenderer renderer = LineRendererFactory.getInstance().getInstance(line.getForm());
		// Old value
		StyleLine oldStyle = line.getStyle();
		Rectangle oldZone = renderer.getRefreshZone(map, viewSettings, line);
		// Set the value
		line.setStyle(style);
		// Refreshes the text zone (including old zone & new zone)
		Rectangle newZone = renderer.getRefreshZone(map, viewSettings, line);
		Rectangle zone = newZone.union(oldZone);
		getControler().refresh(zone);
		// Inverts for next undo
		style = oldStyle;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineStyle.name");
	}

}
