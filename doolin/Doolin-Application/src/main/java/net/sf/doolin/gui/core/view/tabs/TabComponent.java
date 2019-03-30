/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

/**
 * Defines a component to be put as a tab header.
 * 
 * @see JTabbedPane#setTabComponentAt(int, java.awt.Component)
 * @author Damien Coraboeuf
 * @version $Id: TabComponent.java,v 1.1 2007/07/31 15:33:05 guinnessman Exp $
 */
public interface TabComponent {

	/**
	 * Set the associated view container
	 */
	void setTabbedViewContainer(TabbedViewContainer container);

	/**
	 * Gets the associated view container
	 */
	TabbedViewContainer getTabbedViewContainer();

	/**
	 * Returns the associated component.
	 * 
	 * @return Component suitable for a tab header.
	 */
	JComponent getComponent();


}
