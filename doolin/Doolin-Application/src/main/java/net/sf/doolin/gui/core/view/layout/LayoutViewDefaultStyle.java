/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.layout;

import javax.swing.BorderFactory;

import net.sf.doolin.gui.style.SimpleStyle;

/**
 * Default style for a layout view container.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayoutViewDefaultStyle.java,v 1.1 2007/07/31 15:32:59
 *          guinnessman Exp $
 */
public class LayoutViewDefaultStyle extends SimpleStyle {

	/**
	 * Initialisation
	 */
	public LayoutViewDefaultStyle() {
		setBorder(BorderFactory.createEtchedBorder());
	}

}
