/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 * Constraints for placing the tabs.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabPlacement.java,v 1.1 2007/07/31 15:33:05 guinnessman Exp $
 */
public enum TabPlacement {

	/**
	 * Tabs at the top
	 * 
	 * @see SwingConstants#TOP
	 */
	TOP(JTabbedPane.TOP),
	/**
	 * Tabs at the bottom
	 * 
	 * @see SwingConstants#BOTTOM
	 */
	BOTTOM(JTabbedPane.BOTTOM),
	/**
	 * Tabs at the left
	 * 
	 * @see SwingConstants#LEFT
	 */
	LEFT(JTabbedPane.LEFT),
	/**
	 * Tabs at the right
	 * 
	 * @see SwingConstants#RIGHT
	 */
	RIGHT(JTabbedPane.RIGHT);

	private int placement;

	private TabPlacement(int placement) {
		this.placement = placement;
	}

	/**
	 * Gets the tabs placement constraints
	 * 
	 * @return Tabs placement constraints
	 * @see JTabbedPane#getTabPlacement()
	 */
	public int getPlacement() {
		return placement;
	}

}
