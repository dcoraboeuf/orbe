/*
 * Created on Oct 27, 2006
 */
package orbe.gui.main;

import net.sf.doolin.gui.service.GUIStrings;


public class ActionUndo extends AbstractActionUndo {

	@Override
	protected boolean canDo() {
		return getContext().canUndo();
	}

	@Override
	protected void changeState() {
		setEnabled(canDo());
		if (isEnabled()) {
			setName(getContext().getUndoName());
		} else {
			setName(GUIStrings.get("ActionUndo"));
		}
	}

	@Override
	protected void doTask() {
		getContext().undo();
	}

}
