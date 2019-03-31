/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import javax.swing.JButton;
import javax.swing.JComponent;

import orbe.gui.IActions;
import orbe.gui.map.core.OrbeControler;

/**
 * Bouton d'affichage du diaogue d'édition des styles de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboHexTerrain.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public class OptionButtonTextStyle extends AbstractToolOption {

	private JButton button;

	public OptionButtonTextStyle() {
		button = createButton(IActions.ACTION_MAP_TEXT_STYLES);

	}

	public JComponent getComponent() {
		return button;
	}

	/**
	 * Aucun libell�.
	 * 
	 * @see orbe.gui.map.options.ToolOption#getLabel()
	 */
	public String getLabel() {
		return null;
	}

	public void setup(OrbeControler controler) {
		if (controler != null && controler.getContext() != null) {
			button.setEnabled(true);
		} else {
			button.setEnabled(false);
		}
	}

}
