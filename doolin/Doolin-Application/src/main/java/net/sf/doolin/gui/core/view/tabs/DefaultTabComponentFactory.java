/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;


/**
 * Default tab component factory that creates a label and buttons to manage a
 * tab component. Icon &amp; text are depending of the view data. Buttons are
 * created according to the view constrains (closeable).
 * 
 * @TODO TabbedViewConstaints that manages a closeable state
 * @author Damien Coraboeuf
 * @version $Id: DefaultTabComponentFactory.java,v 1.1 2007/07/31 15:33:05 guinnessman Exp $
 */
public class DefaultTabComponentFactory implements TabComponentFactory {

	/**
	 * @see net.sf.doolin.gui.core.view.tabs.TabComponentFactory#getTabComponent(net.sf.doolin.gui.core.view.tabs.TabbedView, net.sf.doolin.gui.core.ViewContainer)
	 */
	public TabComponent getTabComponent(TabbedView view, TabbedViewContainer viewContainer) {
		return new DefaultTabComponent(viewContainer);
	}
	
}
