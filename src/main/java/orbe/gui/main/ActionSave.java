/*
 * Created on 3 oct. 06
 */
package orbe.gui.main;

import java.io.File;

public class ActionSave extends ActionSaveAs {
	
	@Override
	protected void process() {
		File file = getContext().getFile();
		if (file == null) {
			// Same as save as 
			super.process();
		} else {
			doSave();
		}
	}

	@Override
	protected void updateState() {
		setEnabled(getContext() != null && getContext().isDirty());
	}
}
