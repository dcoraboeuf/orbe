/*
 * Created on Dec 6, 2006
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

public abstract class OrbeTaskLineMove extends OrbeTask {

	private OrbeLine line;

	private boolean done;

	private int index;

	public OrbeTaskLineMove(OrbeControler orbeControler, OrbeLine line) {
		super(orbeControler);
		this.line = line;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineMoveFront.name");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		ViewSettings viewSettings = getControler().getView().getViewSettings();
		OrbeLineList lineList = getControler().getContext().getMap().getLineList();
		if (done) {
			if (index != -1) {
				lineList.restore(line, index);
			}
			done = false;
		} else {
			index = move(lineList, line);
			done = true;
		}
		LineRenderer renderer = LineRendererFactory.getInstance().getInstance(line.getForm());
		Rectangle zone = renderer.getRefreshZone(map, viewSettings, line);
		getControler().refresh(zone);
	}

	protected abstract int move(OrbeLineList lineList, OrbeLine line);

}
