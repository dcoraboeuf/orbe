/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;


/**
 * Factory for tab header components.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabComponentFactory.java,v 1.1 2007/07/31 15:33:05 guinnessman Exp $
 */
public interface TabComponentFactory {

	/**
	 * Creates a tab component for the given view container.
	 * 
	 * @param view
	 *            Hosting view
	 * @param viewContainer
	 *            View container to set as a tab
	 * @return Tab component to use
	 */
	TabComponent getTabComponent(TabbedView view, TabbedViewContainer viewContainer);

}
