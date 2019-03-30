/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.model.OrbeMap;
import orbe.model.element.line.OrbeLine;
import orbe.model.style.RepositoryStyle;
import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.StyleLine;

/**
 * Edition des styles de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextStyles.java,v 1.1 2006/11/17 10:56:36 guinnessman
 *          Exp $
 */
public class OrbeTaskStyleLine extends OrbeTaskStyles<StyleLine> {

	public OrbeTaskStyleLine(OrbeControler orbeControler, RepositoryStyle<StyleLine> repository) {
		super(orbeControler, repository);
	}

	@Override
	protected RepositoryStyle<StyleLine> getOriginalRepository() {
		OrbeMap map = getControler().getContext().getMap();
		RepositoryStyleLine originalList = map.getSettings().getStyleLineList();
		return originalList;
	}

	@Override
	protected void onRemove(RepositoryStyle<StyleLine> originalList, StyleLine style) {
		int originalStyleId = style.getId();
		OrbeMap map = getControler().getContext().getMap();
		// MAJ des styles des lignes
		for (OrbeLine line : map.getLineList().getElements()) {
			if (line.getStyle().getId() == originalStyleId) {
				line.setStyle(originalList.getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));
			}
		}
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskStyleLine.name");
	}

}
