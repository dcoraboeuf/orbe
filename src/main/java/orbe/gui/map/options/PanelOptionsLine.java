/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.options;

/**
 * Options pour l'outil ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PanelOptionsLine.java,v 1.2 2006/12/04 10:21:28 guinnessman Exp $
 */
public class PanelOptionsLine extends PanelCompoundOptions {

	/**
	 * Initialisation.
	 *
	 */
	public PanelOptionsLine() {
		addOption(new OptionCboLineForm());
		addOption(new OptionCboLineStyle());
		addOption(new OptionButtonLineStyle());
	}
}
