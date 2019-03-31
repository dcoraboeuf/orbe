/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;
import orbe.gui.form.FormStyles;
import orbe.gui.task.OrbeTaskStyleLine;
import orbe.model.OrbeMap;
import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.StyleLine;

/**
 * Gestion des styles de lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionMapStyleLine.java,v 1.1 2006/11/29 16:45:30 guinnessman Exp $
 */
public class ActionMapStyleLine extends AbstractActionMap {

	@Override
	protected void process() {
		// Liste des styles de texte
		OrbeMap map = getContext().getMap();
		RepositoryStyleLine orginalStyleLineList = map.getSettings().getStyleLineList();
		// Wrapping
		FormStyles<StyleLine> form = new FormStyles<StyleLine>(orginalStyleLineList);
		// Edition
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_MAP_STYLE_LINE, form);
		// Validation
		if (ok) {
			validate(form);
		}
	}

	protected void validate(FormStyles<StyleLine> form) {
		OrbeTaskStyleLine task = new OrbeTaskStyleLine(getControler(), form.getRepository());
		task.process();
		getContext().addEdit(task);
	}

}
