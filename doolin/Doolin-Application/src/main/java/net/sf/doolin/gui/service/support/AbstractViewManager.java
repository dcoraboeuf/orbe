/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.service.support;

import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainerFactory;
import net.sf.doolin.gui.core.support.DefaultViewContainerFactory;
import net.sf.doolin.gui.core.support.ViewDataFactory;
import net.sf.doolin.gui.core.view.ViewAdapter;
import net.sf.doolin.gui.core.view.ViewReference;
import net.sf.doolin.gui.service.ViewManager;

/**
 * Abstract root for view managers.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractViewManager.java,v 1.1 2007/08/10 16:54:38 guinnessman
 *          Exp $
 */
public abstract class AbstractViewManager implements ViewManager {

	private Map<String, View> openedViews = new HashMap<String, View>();

	/**
	 * Registers an opened view
	 * 
	 * @param view
	 *            Opened view
	 */
	protected void registerOpenedView(final View view) {
		openedViews.put(view.getId(), view);
		view.addViewListener(new ViewAdapter() {
			@Override
			public void onViewClosed (View v) {
				openedViews.remove(view.getId());
				view.removeViewListener(this);
			}
		});
	}

	/**
	 * Returns registered opened view.
	 * 
	 * @see net.sf.doolin.gui.service.ViewManager#getOpenedView(java.lang.String)
	 * @see #registerOpenedView(View)
	 */
	public View getOpenedView(String id) {
		return openedViews.get(id);
	}

	public void openViewReference(View view, ViewReference viewReference) {
		Object constraint = viewReference.getConstraint();
		View subView = viewReference.getView();
		// Get the child view data
		Object subViewData = null;
		ViewDataFactory viewDataFactory = viewReference.getViewDataFactory();
		if (viewDataFactory != null) {
			subViewData = viewDataFactory.getViewData(view.getViewData());
		}
		// Opens the view
		openView(view, subView, constraint, subViewData);
	}

	private ViewContainerFactory viewContainerFactory = new DefaultViewContainerFactory();

	/**
	 * @return Returns the viewContainerFactory.
	 */
	public ViewContainerFactory getViewContainerFactory() {
		return viewContainerFactory;
	}

	/**
	 * @param viewContainerFactory
	 *            The viewContainerFactory to set.
	 */
	public void setViewContainerFactory(ViewContainerFactory viewContainerFactory) {
		this.viewContainerFactory = viewContainerFactory;
	}

}
