/*
 * Created on Dec 6, 2006
 */
package orbe.gui.task;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.model.element.line.OrbeLine;
import orbe.model.element.line.OrbeLineList;

public class OrbeTaskLineMoveDown extends OrbeTaskLineMove {

	public OrbeTaskLineMoveDown(OrbeControler orbeControler, OrbeLine line) {
		super(orbeControler, line);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskLineMoveDown.name");
	}

	protected int move(OrbeLineList lineList, OrbeLine line) {
		return lineList.moveDown(line);
	}

}
