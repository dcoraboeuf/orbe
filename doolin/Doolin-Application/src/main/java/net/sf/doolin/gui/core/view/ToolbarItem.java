/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JToolBar;

import net.sf.doolin.gui.core.View;

/**
 * Definition for a toolbar item.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface ToolbarItem {

	/**
	 * Creates a component in the target toolbar
	 * 
	 * @param j
	 *            Target toolbar
	 * @param view
	 *            Hosting view
	 */
	void createToolbarItem(JToolBar j, View view);

}
