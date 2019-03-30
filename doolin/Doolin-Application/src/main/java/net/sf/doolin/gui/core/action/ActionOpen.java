package net.sf.doolin.gui.core.action;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.DataFactory;
import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * This action action opens a subview in the parent view.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public class ActionOpen extends AbstractAction {

	private View childView;

	private Object viewConstraint;

	private DataFactory<Object> viewDataFactory;

	@Override
	protected void process() {
		Object viewData = null;
		if (viewDataFactory != null) {
			viewData = viewDataFactory.createData();
		}
		GUIUtils.getViewManager().openView(getView(), childView, viewConstraint, viewData);
	}

	/**
	 * Returns the constraint associated with the view to open
	 * 
	 * @return the viewConstraint
	 */
	public Object getViewConstraint() {
		return viewConstraint;
	}

	/**
	 * Sets the constraint associated with the view to open
	 * 
	 * @param viewConstraint
	 *            the viewConstraint to set
	 */
	public void setViewConstraint(Object viewConstraint) {
		this.viewConstraint = viewConstraint;
	}

	/**
	 * Returns the view to open
	 * 
	 * @return the view
	 */
	public View getChildView() {
		return childView;
	}

	/**
	 * Sets the view to open
	 * 
	 * @param view
	 *            the view to set
	 */
	public void setChildView(View view) {
		this.childView = view;
	}

	/**
	 * Returns the data factory that is used to generate the view data
	 * 
	 * @return the viewDataFactory
	 */
	public DataFactory<Object> getViewDataFactory() {
		return viewDataFactory;
	}

	/**
	 * Sets the data factory that is used to generate the view data
	 * 
	 * @param viewDataFactory
	 *            the viewDataFactory to set
	 */
	public void setViewDataFactory(DataFactory<Object> viewDataFactory) {
		this.viewDataFactory = viewDataFactory;
	}

}
