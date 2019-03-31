/*
 * Created on Oct 3, 2006
 */
package orbe.gui.main;

import java.io.File;

import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.swing.FileBrowser;

import orbe.gui.context.OrbeContext;

/**
 * Open a map from a file.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionOpen.java,v 1.5 2006/11/30 17:09:29 guinnessman Exp $
 */
public class ActionOpen extends AbstractActionContext {

	private File paramFile;

	@Override
	protected void process() {
		try {
			if (closeCurrent()) {
				openContext();
			}
		} finally {
			paramFile = null;
		}
	}

	protected void openContext() {
		File file;
		if (paramFile == null) {
			// Selects a file
			FileBrowser browser = new FileBrowser();
			browser.setModeSave(false);
			browser.setFileFilter(GUIStrings.get("File.filter"));
			browser.setFileDescription(GUIStrings.get("File.description"));
			file = browser.browse(getView().getComponent());
		} else {
			file = paramFile;
		}

		if (file != null) {
			// Opens the file
			OrbeContext context = new OrbeContext(file);
			// Set the context
			openContext(context);
		}

		if (paramFile != null) {
			paramFile = null;
		}
	}

	public File getParamFile() {
		return paramFile;
	}

	public void setParamFile(File file) {
		this.paramFile = file;
	}

}
