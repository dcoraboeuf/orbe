/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;

import net.sf.doolin.gui.service.GUIStrings;

/**
 * Liste de styles pour lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: RepositoryTextStyle.java,v 1.1 2006/11/16 13:18:39 guinnessman
 *          Exp $
 */
public class RepositoryStyleLine extends RepositoryStyle<StyleLine> {

	/**
	 * Création d'une liste avec style par défaut.
	 */
	public static RepositoryStyleLine createDefaultRepository() {
		RepositoryStyleLine list = new RepositoryStyleLine();
		list.addStyle(createDefaultStyle());
		return list;
	}

	/**
	 * @return Style par défaut
	 */
	public static StyleLine createDefaultStyle() {
		StyleLine style = new StyleLine();
		style.setId(DEFAUL_STYLE_ID);
		style.setName(GUIStrings.get("StyleLine.Default"));
		style.setColor(Color.BLACK);
		style.setDot(LineDotType.NONE);
		style.setGrad(LineGradType.NONE);
		style.setThickness(5);
		style.setTransparent(false);
		style.setType(LineType.OTHER);
		return style;
	}

	@Override
	public StyleLine createDefault() {
		StyleLine style = createDefaultStyle();
		style.setName("");
		return style;
	}

}
