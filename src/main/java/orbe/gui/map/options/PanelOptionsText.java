/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.options;

/**
 * Options pour l'outil texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PanelOptionsText.java,v 1.2 2006/12/04 10:21:28 guinnessman Exp $
 */
public class PanelOptionsText extends PanelCompoundOptions {

	/**
	 * Initialisation.
	 *
	 */
	public PanelOptionsText() {
		addOption(new OptionCboTextStyle());
		addOption(new OptionButtonTextStyle());
	}
}
