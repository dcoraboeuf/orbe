/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JToolBar;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.swing.BarPosition;

/**
 * Toolbar definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface Toolbar {

	/**
	 * Returns the position of the toolbar in its container
	 * 
	 * @return Position
	 */
	BarPosition getPosition();

	/**
	 * Creates a suitable toolbar.
	 * 
	 * @param view
	 *            Hosting view
	 * @return Swing toolbar
	 */
	JToolBar createToolbar(View view);

}
