/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.BeanNameAware;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.bus.GUISubscriber;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.ViewListener;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.validation.ValidationReporter;

/**
 * Root ancestor for views.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractView.java,v 1.7 2007/08/15 09:05:23 guinnessman Exp $
 */
public abstract class AbstractView implements View, BeanNameAware {

	/**
	 * Bean id
	 */
	private String id;

	/**
	 * View title
	 */
	private String title;

	/**
	 * View icon
	 */
	private String iconId;

	/**
	 * Closing action
	 */
	private Action closeAction;

	/**
	 * Menubar
	 */
	private Menubar menubar;

	/**
	 * List of toolbars
	 */
	private List<Toolbar> toolbars = new ArrayList<Toolbar>(0);

	/**
	 * Size
	 */
	private Size size;

	/**
	 * Enabled state
	 */
	private boolean initiallyEnabled = true;

	/**
	 * Sub-views
	 */
	private List<ViewReference> views = new ArrayList<ViewReference>();

	/**
	 * View bus subscribers
	 */
	private List<GUISubscriber> subscribers = new ArrayList<GUISubscriber>();

	/**
	 * View listeners
	 */
	private List<ViewListener> viewListeners = new ArrayList<ViewListener>(0);

	/**
	 * View data
	 */
	private Object viewData;

	/**
	 * Parent view
	 */
	private View parentView;

	/**
	 * View container
	 */
	private ViewContainer viewContainer;

	/**
	 * Validation reporter
	 */
	private ValidationReporter validationReporter;

	/**
	 * Children view containers
	 */
	private HashSet<ViewContainer> childrenViewContainers = new HashSet<ViewContainer>();

	public void addViewListener(ViewListener l) {
		viewListeners.add(l);
	}

	public void removeViewListener(ViewListener l) {
		viewListeners.remove(l);
	}
	
	public boolean isDisplayed() {
		return viewContainer != null && viewContainer.isDisplayed();
	}

	/**
	 * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	 */
	public void setBeanName(String name) {
		id = name;
	}

	/**
	 * @see net.sf.doolin.gui.core.View#getId()
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Returns the menubar.
	 */
	public Menubar getMenubar() {
		return menubar;
	}

	/**
	 * @param menubar
	 *            The menubar to set.
	 */
	public void setMenubar(Menubar menubar) {
		this.menubar = menubar;
	}

	/**
	 * @return Returns the closeAction.
	 */
	public Action getCloseAction() {
		return closeAction;
	}

	/**
	 * @param closeAction
	 *            The closeAction to set.
	 */
	public void setCloseAction(Action closeAction) {
		this.closeAction = closeAction;
	}

	public void execute(Action action) {
		action.setView(this);
		action.execute();
	}

	public Size getSize() {
		return size;
	}

	/**
	 * @param size
	 *            Size definition for this view
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	public List<ViewReference> getViews() {
		return views;
	}

	/**
	 * @param views
	 *            List of view references
	 */
	public void setViews(List<ViewReference> views) {
		this.views = views;
	}

	public Object getViewData() {
		return viewData;
	}

	public void setViewData(Object viewData) {
		this.viewData = viewData;
		if (viewContainer != null) {
			viewContainer.onViewDataChanged();
		}
	}

	public ViewContainer getViewContainer() {
		return viewContainer;
	}

	public void setViewContainer(ViewContainer viewContainer) {
		this.viewContainer = viewContainer;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            Expression for the view title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public void addChild(ViewContainer viewContainer) {
		childrenViewContainers.add(viewContainer);
	}

	public void removeChild(ViewContainer viewContainer) {
		childrenViewContainers.remove(viewContainer);
	}

	public Set<ViewContainer> getChildrenViewContainers() {
		return childrenViewContainers;
	}

	public void close() {
		// Closes all children
		ViewContainer[] viewContainers = childrenViewContainers.toArray(new ViewContainer[0]);
		for (ViewContainer childViewContainer : viewContainers) {
			childViewContainer.getView().close();
		}
		// Closes the container
		viewContainer.close();
		// Notifies the view parent
		if (parentView != null) {
			parentView.removeChild(viewContainer);
		}
		// Notifies the listeners
		ViewListener[] tl = viewListeners.toArray(new ViewListener[0]);
		for (int i = 0; i < tl.length; i++) {
			ViewListener listener = tl[i];
			listener.onViewClosed(this);
		}
	}

	/**
	 * @return Parent view or <code>null</code>
	 */
	public View getParentView() {
		return parentView;
	}

	/**
	 * @param parentView
	 *            Parent view or <code>null</code>
	 */
	public void setParentView(View parentView) {
		this.parentView = parentView;
	}

	public void display(ValidationReport report) {
		getValidationReporter().report(this, report);
	}

	/**
	 * Returns the validation reporter specific to this view or the one defined
	 * at application level.
	 * 
	 * @see #setValidationReporter(ValidationReporter)
	 * @see GUIUtils#getService(Class)
	 * @return Validation reporter
	 */
	public ValidationReporter getValidationReporter() {
		return validationReporter != null ? validationReporter : GUIUtils.getService(ValidationReporter.class);
	}

	/**
	 * Sets the validation reporter specific to this view. Let it to
	 * <code>null</code> if the default validation reporter must be used.
	 * 
	 * @see GUIUtils#getService(Class)
	 * @param validationReporter
	 *            Validation reporter
	 */
	public void setValidationReporter(ValidationReporter validationReporter) {
		this.validationReporter = validationReporter;
	}

	/**
	 * @return Returns the iconId.
	 */
	public String getIconId() {
		return iconId;
	}

	/**
	 * @param iconId
	 *            The iconId to set.
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	/**
	 * Returns the <code>subscribers</code> property.
	 * 
	 * @return <code>subscribers</code> property.
	 */
	public List<GUISubscriber> getSubscribers() {
		return subscribers;
	}

	/**
	 * Sets the <code>subscribers</code> property.
	 * 
	 * @param subscribers
	 *            <code>subscribers</code> property.
	 */
	public void setSubscribers(List<GUISubscriber> subscribers) {
		this.subscribers = subscribers;
	}

	/**
	 * General initialization of the view.
	 * 
	 * @see #initSubscribers
	 * @see net.sf.doolin.gui.core.View#init()
	 */
	public void init() {
		initSubscribers();
	}

	/**
	 * Setup the initial state of the view before display.
	 * 
	 * @see net.sf.doolin.gui.core.View#onBeforeDisplay()
	 */
	public void onBeforeDisplay() {
		if (!initiallyEnabled) {
			setEnabled(false);
		}
	}

	/**
	 * Tells all view subscribers to register the view.
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void initSubscribers() {
		for (GUISubscriber subscriber : subscribers) {
			subscriber.register(this);
		}
	}

	/**
	 * Returns the <code>initiallyEnabled</code> property.
	 * 
	 * @return <code>initiallyEnabled</code> property.
	 */
	public boolean isInitiallyEnabled() {
		return initiallyEnabled;
	}

	/**
	 * Sets the <code>initiallyEnabled</code> property.
	 * 
	 * @param initiallyEnabled
	 *            <code>initiallyEnabled</code> property.
	 */
	public void setInitiallyEnabled(boolean initiallyEnabled) {
		this.initiallyEnabled = initiallyEnabled;
	}

	/**
	 * Gets the list of toolbars.
	 * 
	 * @see net.sf.doolin.gui.core.View#getToolbars()
	 */
	public List<Toolbar> getToolbars() {
		return toolbars;
	}

	/**
	 * Sets the list of toolbars.
	 * 
	 * @param toolbars
	 *            List of toolbars
	 */
	@Configurable(mandatory = false, description = "List of toolbars", defaultsTo = "Empty")
	public void setToolbars(List<Toolbar> toolbars) {
		this.toolbars = toolbars;
	}

}
