/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.Component;
import java.util.List;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.support.GUIUtils;

public abstract class AbstractViewContainer implements ViewContainer {
	
	private View view;
	
	private Object viewData;
	
	private Object viewConstraint;
	
	public boolean isDisplayed() {
		Component component = getComponent();
		return component != null && component.isVisible();
	}

	public void init(View view, Object viewData) {
		// Context
		this.view = view;
		this.viewData = viewData;
		// Builds the container
		build();
		// View setup
		view.setViewData(viewData);
		view.setViewContainer(this);
		// Set the inside of the container
		setContent(view.getComponent());
		// Management of subviews
		List<ViewReference> views = view.getViews();
		if (views != null) {
			for (ViewReference viewReference : views) {
				GUIUtils.getViewManager().openViewReference(view, viewReference);
			}
		}
	}
	
	protected abstract void build();
	
	protected abstract void setContent (JComponent component);

	/**
	 * @return Returns the view.
	 */
	public View getView() {
		return view;
	}

	/**
	 * @return Returns the viewData.
	 */
	public Object getViewData() {
		return viewData;
	}

	public Object getViewConstraint() {
		return viewConstraint;
	}

	public void setViewConstraint(Object viewConstraint) {
		this.viewConstraint = viewConstraint;
	}

	/**
	 * Does nothing by default.
	 * @see net.sf.doolin.gui.core.ViewContainer#onViewDataChanged()
	 */
	public void onViewDataChanged() {
	}
	
}
