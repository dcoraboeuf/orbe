/*
 * Created on Oct 27, 2006
 */
package orbe.gui.main;

import net.sf.doolin.gui.service.GUIStrings;

public class ActionRedo extends AbstractActionUndo {

	@Override
	protected boolean canDo() {
		return getContext().canRedo();
	}

	@Override
	protected void changeState() {
		setEnabled(canDo());
		if (isEnabled()) {
			setName(getContext().getRedoName());
		} else {
			setName(GUIStrings.get("ActionRedo"));
		}
	}

	@Override
	protected void doTask() {
		getContext().redo();
	}

}
