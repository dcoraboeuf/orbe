/*
 * Created on Nov 13, 2006
 */
package orbe.gui.map;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;
import orbe.gui.form.FormTerrains;
import orbe.gui.task.OrbeTaskTerrains;
import orbe.model.OrbeMap;
import orbe.model.style.RepositoryHexTerrain;

/**
 * Edition des terrains.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionMapTerrains.java,v 1.1 2006/11/13 15:59:24 guinnessman Exp $
 */
public class ActionMapTerrains extends AbstractActionMap {

	@Override
	protected void process() {
		OrbeMap map = getContext().getMap();
		// Original list
		RepositoryHexTerrain mapTerrains = map.getSettings().getTerrains();
		// Wrapping list
		FormTerrains form = new FormTerrains(mapTerrains);
		// Edition des terrains
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_MAP_TERRAINS, form);
		if (ok) {
			// Validation de la saisie
			validate(form);
		}
	}

	/**
	 * Report des terrains édités vers la liste originale
	 */
	protected void validate(FormTerrains formTerrains) {
		OrbeTaskTerrains task = new OrbeTaskTerrains(getControler(), formTerrains.getTerrains());
		task.process();
		getContext().addEdit(task);
	}

}
