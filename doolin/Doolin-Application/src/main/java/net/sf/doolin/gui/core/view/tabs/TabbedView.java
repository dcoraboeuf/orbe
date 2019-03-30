/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;

import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.view.AbstractView;

/**
 * This view arranges subviews in tabs.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabbedView.java,v 1.2 2007/08/14 11:48:56 guinnessman Exp $
 */
public class TabbedView extends AbstractView {

	private JTabbedPane tabbedPane;

	private TabPlacement tabPlacement = TabPlacement.TOP;

	private TabComponentFactory tabComponentFactory = new DefaultTabComponentFactory();

	/**
	 * Creates the tabbed pane.
	 * 
	 * @see net.sf.doolin.gui.core.View#init()
	 */
	public void init() {
		super.init();
		tabbedPane = new JTabbedPane(tabPlacement.getPlacement());
	}

	/**
	 * Returns the tabbed pane.
	 * 
	 * @see net.sf.doolin.gui.core.View#getComponent()
	 */
	public JComponent getComponent() {
		return tabbedPane;
	}

	/**
	 * Creates a view container for each tab.
	 * 
	 * @see TabbedViewContainer
	 * @see net.sf.doolin.gui.core.View#createViewContainer()
	 */
	public ViewContainer createViewContainer() {
		return new TabbedViewContainer();
	}

	/**
	 * Does validate the subviews.
	 * 
	 * @see net.sf.doolin.gui.core.View#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void validate(ValidationReport validationReport) {
		// TODO Code of TabbedView.validate method
		throw new RuntimeException("NYI");
	}

	/**
	 * Gets the placement policy of tabs
	 * 
	 * @return Placement policy of tabs
	 * @see TabPlacement
	 */
	public TabPlacement getTabPlacement() {
		return tabPlacement;
	}

	/**
	 * Sets the Placement policy of tabs
	 * 
	 * @param tabPlacement
	 *            Placement policy of tabs
	 * @see TabPlacement
	 */
	public void setTabPlacement(TabPlacement tabPlacement) {
		this.tabPlacement = tabPlacement;
	}

	/**
	 * Adds a child as a new tab.
	 * 
	 * @see net.sf.doolin.gui.core.view.AbstractView#addChild(net.sf.doolin.gui.core.ViewContainer)
	 */
	@Override
	public void addChild(ViewContainer viewContainer) {
		int index = tabbedPane.getTabCount();
		// Computes the tab information
		TabComponent tabComponent = tabComponentFactory.getTabComponent(this, (TabbedViewContainer) viewContainer);
		// Adds the tab
		tabbedPane.insertTab(null, null, viewContainer.getComponent(), null, index);
		tabbedPane.setComponentAt(index, viewContainer.getComponent());
		tabbedPane.setTabComponentAt(index, tabComponent.getComponent());
		// Ok
		super.addChild(viewContainer);
	}

	/**
	 * Removes child from tabs.
	 * 
	 * @see net.sf.doolin.gui.core.view.AbstractView#removeChild(net.sf.doolin.gui.core.ViewContainer)
	 */
	@Override
	public void removeChild(ViewContainer viewContainer) {
		// Removes the tab
		int index = tabbedPane.indexOfComponent(viewContainer.getComponent());
		// No more tab
		tabbedPane.removeTabAt(index);
		// Ok
		super.removeChild(viewContainer);
	}

	/**
	 * Gets the factory for tab header components.
	 * 
	 * @return Factory for tab header components.
	 */
	public TabComponentFactory getTabComponentFactory() {
		return tabComponentFactory;
	}

	/**
	 * Sets the factory for tab header components.
	 * 
	 * @param tabComponentFactory
	 *            Factory for tab header components.
	 */
	public void setTabComponentFactory(TabComponentFactory tabComponentFactory) {
		this.tabComponentFactory = tabComponentFactory;
	}

	/**
	 * Delegates to all subviews.
	 * 
	 * @see net.sf.doolin.gui.core.View#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		Set<ViewContainer> viewContainers = getChildrenViewContainers();
		for (ViewContainer viewContainer : viewContainers) {
			viewContainer.getView().setEnabled(enabled);
		}
	}

}
