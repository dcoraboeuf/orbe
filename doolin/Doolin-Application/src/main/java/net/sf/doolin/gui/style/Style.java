/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.style;

import javax.swing.JComponent;

/**
 * Style definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Style.java,v 1.1 2007/07/31 15:33:00 guinnessman Exp $
 */
public interface Style {

	/**
	 * Applies this style to the given component.
	 * 
	 * @param component
	 *            Component to apply the style to.
	 */
	void apply(JComponent component);

}
