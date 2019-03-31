/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.model.OrbeMap;
import orbe.model.element.text.OrbeText;
import orbe.model.style.RepositoryStyle;
import orbe.model.style.RepositoryTextStyle;
import orbe.model.style.TextStyle;

/**
 * Edition des styles de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextStyles.java,v 1.1 2006/11/17 10:56:36 guinnessman
 *          Exp $
 */
public class OrbeTaskTextStyles extends OrbeTaskStyles<TextStyle> {

	public OrbeTaskTextStyles(OrbeControler orbeControler, RepositoryStyle<TextStyle> repository) {
		super(orbeControler, repository);
	}

	@Override
	protected RepositoryStyle<TextStyle> getOriginalRepository() {
		OrbeMap map = getControler().getContext().getMap();
		RepositoryTextStyle originalList = map.getSettings().getTextStyles();
		return originalList;
	}

	@Override
	protected void onRemove(RepositoryStyle<TextStyle> originalList, TextStyle style) {
		int originalStyleId = style.getId();
		OrbeMap map = getControler().getContext().getMap();
		// MAJ des styles
		for (OrbeText text : map.getTextList().getElements()) {
			if (text.getStyle().getId() == originalStyleId) {
				text.setStyle(originalList.getStyle(RepositoryTextStyle.DEFAUL_STYLE_ID));
			}
		}
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTextStyles.name");
	}

}
