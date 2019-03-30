/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.style;

import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * Simple configurable style.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SimpleStyle.java,v 1.1 2007/07/31 15:33:00 guinnessman Exp $
 */
public class SimpleStyle extends AbstractStyle {

	private Border border;

	public void apply(JComponent component) {
		// Border
		if (border != null) {
			component.setBorder(border);
		}
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

}
