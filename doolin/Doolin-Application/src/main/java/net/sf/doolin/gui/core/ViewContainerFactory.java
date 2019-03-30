/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core;

/**
 * This interface defines the service which is used to create top-level view
 * containers. Those are view containers which are not created by other views.
 * There can be only two types of top-level view containers: frames and modal
 * dialogs.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ViewContainerFactory.java,v 1.3 2007/08/02 19:31:11 guinnessman
 *          Exp $
 */
public interface ViewContainerFactory {

	/**
	 * Creates a view container that represents a frame.
	 * 
	 * @param view
	 *            View that will be hosted by the container.
	 * @return View container
	 */
	ViewContainer createFrameViewContainer(View view);

	/**
	 * Creates a view container that represents a modal dialog.
	 * 
	 * @param view
	 *            View that will be hosted by the container.
	 * @return View container
	 * @see ViewContainer#display(View)
	 */
	ViewContainer createDialogViewContainer(View view);

}
