/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core;

import java.util.List;
import java.util.Set;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.view.Menubar;
import net.sf.doolin.gui.core.view.Size;
import net.sf.doolin.gui.core.view.Toolbar;
import net.sf.doolin.gui.core.view.ViewReference;
import net.sf.doolin.util.expression.ExpressionString;

/**
 * View instance. It defines the content of a view to be displayed. A view is
 * always integrated in a {@link ViewContainer view container} in order to be
 * displayed.
 * 
 * @author Damien Coraboeuf
 * @version $Id: View.java,v 1.5 2007/08/14 11:48:58 guinnessman Exp $
 */
public interface View {

	/**
	 * ID of the view
	 * 
	 * @return Unique identifier for this view
	 */
	String getId();

	/**
	 * Title. It is an expression that depends on the view data (the expression
	 * is mapping the view data to the $viewData variable).
	 * 
	 * @return Title expression.
	 * @see ExpressionString
	 */
	String getTitle();

	/**
	 * View size definition. This class is used to setup the size of the view
	 * according to its container.
	 * 
	 * @return Size definition
	 */
	Size getSize();

	/**
	 * Close action. This action is executed when the view is about to be
	 * closed. It is the responsability of this action to actually close the
	 * view by calling the <code>{@link #close()}</code> method.
	 * 
	 * @return Close action
	 */
	Action getCloseAction();

	/**
	 * Returns the menu bar which is associated with this view.
	 * 
	 * @return Menu bar definition
	 */
	Menubar getMenubar();

	/**
	 * Returns the list of toolbars associated with this view.
	 * 
	 * @return List of toolbar, can be <code>null</code> or empty.
	 */
	List<Toolbar> getToolbars();

	/**
	 * Executes an action for this view.
	 * 
	 * @param action
	 *            Action to be executed
	 */
	void execute(Action action);

	/**
	 * Get the component of this view.
	 * 
	 * @return Component of this view
	 */
	JComponent getComponent();

	/**
	 * Set the view data
	 * 
	 * @param value
	 *            Value to associate with this view.
	 */
	void setViewData(Object value);

	/**
	 * Gets the view data
	 * 
	 * @return Value associated with this view.
	 */
	Object getViewData();

	/**
	 * Sub-views. It defines the list of views which ar to be displayed together
	 * with this view.
	 * 
	 * @return List of view reference definitions
	 */
	List<ViewReference> getViews();

	/**
	 * Gets the view container
	 * 
	 * @return Get the view container that contains this view.
	 */
	ViewContainer getViewContainer();

	/**
	 * Sets the view container for this view
	 * 
	 * @param container
	 *            View container that contains this view.
	 */
	void setViewContainer(ViewContainer container);

	/**
	 * Creates a suitable container for this view. This method is called when
	 * this view is about to contain a sub view. The returned container will be
	 * used to contain this sub view. This view knows explicitely the type of
	 * view container it is able to contain.
	 * 
	 * @return A view container able to be hosted by this view.
	 */
	ViewContainer createViewContainer();

	/**
	 * Adds a subview container in this view. The type of view container has
	 * been created by a previous call to the
	 * <code>{@link #createViewContainer()}</code> method.
	 * 
	 * @param viewContainer
	 *            The child view container to add to this view.
	 */
	void addChild(ViewContainer viewContainer);

	/**
	 * Removes a subview container from this view. This view container has been
	 * added to this view by a previous call to the
	 * <code>{@link #addChild(ViewContainer)}</code> method.
	 * 
	 * @param viewContainer
	 *            The child view container to remove from this view.
	 */
	void removeChild(ViewContainer viewContainer);

	/**
	 * Closes this view
	 */
	void close();

	/**
	 * Returns the list of sub view containers which are opened under this view.
	 * 
	 * @return List of actual sub view containers
	 */
	Set<ViewContainer> getChildrenViewContainers();

	/**
	 * Validation of this view.
	 * 
	 * @param validationReport
	 *            Report to fill
	 */
	void validate(ValidationReport validationReport);

	/**
	 * Initialization of the view
	 */
	void init();

	/**
	 * Displays a validation report
	 * 
	 * @param report
	 *            Report to display
	 */
	void display(ValidationReport report);

	/**
	 * @return Associated icon id
	 */
	String getIconId();

	/**
	 * Enables the view
	 * 
	 * @param enabled
	 *            Enabled flag
	 */
	void setEnabled(boolean enabled);

	/**
	 * This method is called just before the view is actually displayed.
	 * 
	 */
	void onBeforeDisplay();

	/**
	 * Adds a view listener
	 * 
	 * @param l
	 *            View listener
	 */
	void addViewListener(ViewListener l);

	/**
	 * Removes a view listener
	 * 
	 * @param l
	 *            View listener
	 */
	void removeViewListener(ViewListener l);

	/**
	 * Returns if the view is actually displayed or not.
	 * 
	 * @return Displayed state
	 */
	boolean isDisplayed();

}
