/*
 * Created on 6 déc. 06
 */
package orbe.gui.main;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.Preferences;
import orbe.gui.IViews;
import orbe.gui.export.ExportTask;
import orbe.gui.form.FormExport;

/**
 * Export de la carte au format PNG.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionExport.java,v 1.2 2006/12/06 13:48:14 guinnessman Exp $
 */
public class ActionExport extends AbstractActionMain {

	/**
	 * Default constructor.
	 */
	public ActionExport() {
		setEnabled(false);
	}

	@Override
	protected void process() {
		// Formulaire d'export
		FormExport form = new FormExport(getContext());
		// Restauration depuis les préférences
		Preferences preferences = GUIUtils.getPreferences();
		preferences.restoreMemento(form);
		// Affichage
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_EXPORT, form);
		// Export
		if (ok) {
			// Sauvegarde des préférences
			preferences.saveMemento(form);
			preferences.save();
			// Export
			ExportTask task = new ExportTask(form, getControler());
			task.launch();
		}
	}

	@Override
	protected void onContextChanged() {
		super.onContextChanged();
		setEnabled(getContext() != null);
	}

}
