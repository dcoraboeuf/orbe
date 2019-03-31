/*
 * Created on 3 oct. 06
 */
package orbe.gui.main;

import java.io.File;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.swing.FileBrowser;
import orbe.gui.IViews;
import orbe.gui.context.OrbeContext;
import orbe.gui.message.OrbeMessageFile;
import orbe.model.io.OrbeIO;

public class ActionSaveAs extends AbstractActionMain {

	public ActionSaveAs() {
		setEnabled(false);
	}

	@Override
	protected void onContextChanged() {
		setView(GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN));
		updateState();
	}

	protected void updateState() {
		setEnabled(getContext() != null);
	}

	@Override
	protected void onContextDirtyChanged() {
		updateState();
	}

	@Override
	protected void process() {
		File file = getContext().getFile();
		// Selects a file
		FileBrowser browser = new FileBrowser();
		browser.setModeSave(true);
		browser.setFileFilter(GUIStrings.get("File.filter"));
		browser.setFileDescription(GUIStrings.get("File.description"));
		file = browser.browse(getView().getComponent());
		if (file != null) {
			getContext().setFile(file);
			doSave();
		}
	}

	protected void doSave() {
		OrbeContext context = getContext();
		File file = context.getFile();
		if (file == null) {
			throw new IllegalStateException("No file is associated with the context.");
		} else {
			// Saves the context in file
			OrbeIO.getInstance().save(context.getMap(), file);
			// Reset the dirty state
			context.hasBeenSaved();
			// Refresh the title of the view...
			getView().setViewData(context);
			// Publish the fact that the file has been opened...
			Bus.get().publish(new OrbeMessageFile(file));
		}
	}

}
