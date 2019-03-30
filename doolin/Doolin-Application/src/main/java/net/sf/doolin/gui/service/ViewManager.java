/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.service;

import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.view.ViewReference;

/**
 * Management of views.
 * 
 * This interface represents the central object responsible for the management
 * of views in a Doolin GUI application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ViewManager.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 * @see ApplicationManager#getViewManager()
 */
public interface ViewManager {

	/**
	 * Opens a view as a frame
	 * 
	 * @param view
	 *            View to be displayed by the frame
	 * @param viewData
	 *            Data for the view
	 */
	void openFrame(View view, Object viewData);

	/**
	 * Opens a view reference
	 * 
	 * @param view
	 *            Hosting view
	 * @param viewReference
	 *            View reference definition
	 */
	void openViewReference(View view, ViewReference viewReference);

	/**
	 * Opens a view
	 * 
	 * @param parentView
	 *            Hosting view
	 * @param view
	 *            View to open inside the parent view
	 * @param viewConstraint
	 *            Constraints to associated with the child view container
	 * @param viewData
	 *            Data to associate with the child view
	 */
	void openView(View parentView, View view, Object viewConstraint, Object viewData);

	/**
	 * Opens a dialog
	 * 
	 * @param parentView
	 *            Parent view
	 * @param dialogView
	 *            Dialog view to open
	 * @param viewData
	 *            Dialog data
	 * @return Result of the dialog validation
	 */
	boolean openDialog(View parentView, View dialogView, Object viewData);

	/**
	 * Gets an opened view by its ID
	 * 
	 * @param id
	 *            ID of the view to look for
	 * @return Corresponding view or <code>null</code> if this view is not
	 *         opened.
	 */
	View getOpenedView(String id);

}
