/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.service.support;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.ViewContainerFactory;

/**
 * Default view manager.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultViewManager.java,v 1.2 2007/07/25 18:10:40 guinnessman
 *          Exp $
 */
public class DefaultViewManager extends AbstractViewManager {

	/**
	 * Opens a view as a frame.
	 */
	public void openFrame(View view, Object viewData) {
		// Init the view
		view.init();
		// Creates a view container
		ViewContainer viewContainer = getViewContainerFactory().createFrameViewContainer(view);
		// Init the container
		viewContainer.init(view, viewData);
		// Displays the view
		view.onBeforeDisplay();
		// Displays the container
		viewContainer.display(null);
		// Registration
		registerOpenedView(view);
	}

	/**
	 * Displays and validates a dialog.
	 * 
	 * @see ViewContainerFactory#createDialogViewContainer()
	 * @see net.sf.doolin.gui.service.ViewManager#openDialog(net.sf.doolin.gui.core.View,
	 *      java.lang.Object)
	 */
	public boolean openDialog(View parentView, View view, Object viewData) {
		// Init the view
		view.init();
		// Creates a view container
		ViewContainer viewContainer = getViewContainerFactory().createDialogViewContainer(view);
		// Init the container
		viewContainer.init(view, viewData);
		// Displays the view
		view.onBeforeDisplay();
		// Displays the container
		return viewContainer.display(parentView);
	}

	public void openView(View parentView, View view, Object viewConstraint, Object viewData) {
		// Is this view already opened ?
		boolean alreadyOpened = (getOpenedView(view.getId()) == view);
		if (!alreadyOpened) {
			// Init the view
			view.init();
			// Creates a view container
			ViewContainer viewContainer = parentView.createViewContainer();
			// Init the container
			viewContainer.setViewConstraint(viewConstraint);
			viewContainer.init(view, viewData);
			// Installs the container in parent
			parentView.addChild(viewContainer);
			// Displays the view
			view.onBeforeDisplay();
			// Displays the container
			viewContainer.display(parentView);
			// Registration
			registerOpenedView(view);
		} else {
			// Updates the data
			view.setViewData(viewData);
			// Brings the view to front
			view.getViewContainer().activate();
		}
	}

}
