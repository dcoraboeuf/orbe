/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;
import orbe.gui.form.FormStyles;
import orbe.gui.task.OrbeTaskTextStyles;
import orbe.model.OrbeMap;
import orbe.model.style.RepositoryTextStyle;
import orbe.model.style.TextStyle;

/**
 * Gestion des styles de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionMapTextStyles.java,v 1.1 2006/11/17 10:56:38 guinnessman Exp $
 */
public class ActionMapTextStyles extends AbstractActionMap {

	@Override
	protected void process() {
		// Liste des styles de texte
		OrbeMap map = getContext().getMap();
		RepositoryTextStyle originalTextStyles = map.getSettings().getTextStyles();
		// Wrapping
		FormStyles<TextStyle> form = new FormStyles<TextStyle>(originalTextStyles);
		// Edition
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_MAP_TEXT_STYLES, form);
		// Validation
		if (ok) {
			validate(form);
		}
	}

	protected void validate(FormStyles<TextStyle> form) {
		OrbeTaskTextStyles task = new OrbeTaskTextStyles(getControler(), form.getRepository());
		task.process();
		getContext().addEdit(task);
	}

}
