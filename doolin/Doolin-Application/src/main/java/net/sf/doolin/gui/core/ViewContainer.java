/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core;

import java.awt.Component;

/**
 * This interface defines a component whose purpose is to host a view. Usually,
 * view containers are not defined at configuration time but are created by view
 * implementations in order to host their subviews.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ViewContainer.java,v 1.4 2007/08/02 19:31:11 guinnessman Exp $
 */
public interface ViewContainer {

	/**
	 * Displays the view container in its parent.
	 * 
	 * @param parentView
	 *            Parent view or <code>null</code> if this container has no
	 *            parent.
	 * @return Result of the validation of the displayed view if the view
	 *         container is blocking the display until user validation.
	 */
	boolean display(View parentView);

	/**
	 * Initializes the view container with its contained view and the associated
	 * view data.
	 * 
	 * @param view
	 *            Contained view
	 * @param viewData
	 *            View data
	 */
	void init(View view, Object viewData);

	/**
	 * Returns the contained view
	 * 
	 * @return Contained view
	 */
	View getView();

	/**
	 * Sets some constraints for this view container when it is displayed inside
	 * another view. The type of constraint object is specific to the view
	 * container implementation.
	 * 
	 * @param viewConstraint
	 *            View displaying constraints for this view container
	 */
	void setViewConstraint(Object viewConstraint);

	/**
	 * Returns constraints associated with this view container
	 * 
	 * @return Constraints associated with this view container
	 * @see #setViewConstraint(Object)
	 */
	Object getViewConstraint();

	/**
	 * Returns the displayable component associated with this view container
	 * 
	 * @return Component that represents a concrete view of this view container
	 */
	Component getComponent();

	/**
	 * Actually closes this view container
	 * 
	 */
	void close();

	/**
	 * This method is called by the view when its view data has been changed.
	 * 
	 * @see View#getViewData()
	 */
	void onViewDataChanged();

	/**
	 * Returns if the view container is actually displayed or not.
	 * 
	 * @return Displayed state
	 */
	boolean isDisplayed();

	/**
	 * Activates the view (brings it to the front).
	 */
	void activate();

}
