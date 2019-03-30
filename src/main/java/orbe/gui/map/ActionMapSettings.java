/*
 * Created on 3 oct. 06
 */
package orbe.gui.map;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;
import orbe.gui.form.FormSettings;
import orbe.model.OrbeMap;
import orbe.model.OrbeSettings;

public class ActionMapSettings extends AbstractActionMap {

	@Override
	protected void process() {
		OrbeMap map = getContext().getMap();
		// Copy of the current settings
		OrbeSettings settings = map.getSettings();
		// TODO Makes a copy of the settings before edition
		/*
		 * Cloning is dangerous in a Swing env. because objects can still be
		 * referenced
		 */
		/*
		 * What can be done is a cloning and then a restoration but the object
		 * must support that. An interface like Restorable could be used with
		 * two methods: copy():Object and restore(Object)
		 */
		// settings = Utils.clone(settings);
		// Edit them
		FormSettings form = new FormSettings(settings);
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_MAP_SETTINGS, form);
		if (ok) {
			map.setSettings(form.getSettings());
		}
	}

}
