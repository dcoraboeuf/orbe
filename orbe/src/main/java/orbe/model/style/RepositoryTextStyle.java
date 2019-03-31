/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;

import net.sf.doolin.gui.service.GUIStrings;

/**
 * Liste de styles pour texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: RepositoryTextStyle.java,v 1.1 2006/11/16 13:18:39 guinnessman
 *          Exp $
 */
public class RepositoryTextStyle extends RepositoryStyle<TextStyle> {

	/**
	 * création d'une liste avec style par défaut.
	 */
	public static RepositoryTextStyle createDefaultRepository() {
		RepositoryTextStyle list = new RepositoryTextStyle();
		// Style par défaut
		list.addStyle(createDefaultStyle());
		// Ok
		return list;
	}

	/**
	 * @return Style par défaut
	 */
	public static TextStyle createDefaultStyle() {
		TextStyle style = new TextStyle();
		style.setId(DEFAUL_STYLE_ID);
		style.setName(GUIStrings.get("TextStyle.Default"));
		style.setFontName("SansSerif");
		style.setFontSize(100);
		style.setFontBold(false);
		style.setFontItalic(false);
		style.setFontColor(Color.BLACK);
		return style;
	}

	@Override
	public TextStyle createDefault() {
		TextStyle style = createDefaultStyle();
		style.setName("");
		return style;
	}

}
