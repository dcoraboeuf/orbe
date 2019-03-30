/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.core.support;

import javax.swing.Icon;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconManager;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.service.AlertManager;
import net.sf.doolin.gui.service.DesktopService;
import net.sf.doolin.gui.service.ExpressionEvaluator;
import net.sf.doolin.gui.service.Preferences;
import net.sf.doolin.gui.service.PropertyService;
import net.sf.doolin.gui.service.SwingFactory;
import net.sf.doolin.gui.service.ViewManager;

/**
 * General utility methods for the Doolin GUI framework.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUIUtils.java,v 1.8 2007/08/15 15:13:34 guinnessman Exp $
 */
public class GUIUtils {

	/**
	 * Get a service deployed in the Doolin GUI framework.
	 * 
	 * @param <T>
	 *            Service type
	 * @param serviceClass
	 *            Service type
	 * @return Service instance
	 */
	public static <T> T getService(Class<T> serviceClass) {
		return Application.getApplication().getService(serviceClass);
	}

	/**
	 * Computes the title of a view.
	 * 
	 * @param view
	 *            View to compute the title from
	 * @return View title
	 */
	public static String getActualViewTitle(View view) {
		ExpressionEvaluator evaluator = getService(ExpressionEvaluator.class);
		String actualTitle = evaluator.evaluate(view.getTitle(), "viewData", view.getViewData());
		return actualTitle;
	}

	/**
	 * Get a property from an object.
	 * 
	 * @param item
	 *            Object to get the property from
	 * @param property
	 *            Property name
	 * @return Property value
	 * @see PropertyService#getProperty(Object, String)
	 * @see #getService(Class)
	 */
	public static Object getProperty(Object item, String property) {
		PropertyService service = getService(PropertyService.class);
		return service.getProperty(item, property);
	}

	/**
	 * Sets a property in an object.
	 * 
	 * @param item
	 *            Object to set the property in
	 * @param property
	 *            Property name
	 * @param value
	 *            Property value
	 * @see PropertyService#setProperty(Object, String, Object)
	 * @see #getService(Class)
	 */
	public static void setProperty(Object item, String property, Object value) {
		PropertyService service = getService(PropertyService.class);
		service.setProperty(item, property, value);
	}

	/**
	 * Gets the <code>{@link AlertManager}</code> service.
	 * 
	 * @return Application message handler
	 * @see AlertManager
	 * @see #getService(Class)
	 */
	public static AlertManager getAlertManager() {
		return getService(AlertManager.class);
	}

	/**
	 * Evaluates an expression with one variable.
	 * 
	 * @param expression
	 *            Expression to evaluate
	 * @param name
	 *            Variable name (<code>null</code> if not needed)
	 * @param value
	 *            Variable value
	 * @return Result of the expression
	 * @see #getService(Class)
	 * @see ExpressionEvaluator
	 */
	public static String evaluate(String expression, String name, Object value) {
		ExpressionEvaluator expressionEvaluator = getService(ExpressionEvaluator.class);
		return expressionEvaluator.evaluate(expression, name, value);
	}

	/**
	 * Displays an executes a modal dialog.
	 * 
	 * @param parentView
	 *            Parent view
	 * @param dialogViewName
	 *            ID of the view bean in the application configuration.
	 * @param viewData
	 *            View data for the dialog
	 * @return Result of the dialog
	 * @see Application#getView(String)
	 * @see #getViewManager()
	 * @see ViewManager#openDialog(View, Object)
	 */
	public static boolean openDialog(View parentView, String dialogViewName, Object viewData) {
		// Get the view definition
		View dialogView = Application.getApplication().getView(dialogViewName);
		// Displays the dialog
		boolean ok = getViewManager().openDialog(parentView, dialogView, viewData);
		// Ok
		return ok;
	}

	/**
	 * Shortcut method to get an icon for a given id and a given size.
	 * 
	 * @param iconId
	 *            Requested icon id
	 * @param size
	 *            Requested icon size
	 * @return Icon or <code>null</code> if not defined
	 * @see #getIconManager()
	 * @see IconManager#getIcon(String, IconSize)
	 */
	public static Icon getIcon(String iconId, IconSize size) {
		return getIconManager().getIcon(iconId, size);
	}

	/**
	 * Gets the icon for the given view
	 * 
	 * @param view
	 *            View
	 * @param size
	 *            Requested icon size
	 * @return Icon or <code>null</code> if no icon is associated with the
	 *         view
	 * @see View#getIconId()
	 * @see #getIcon(String, IconSize)
	 */
	public static Icon getViewIcon(View view, IconSize size) {
		String iconId = view.getIconId();
		if (StringUtils.isNotBlank(iconId)) {
			return getIcon(iconId, size);
		} else {
			return null;
		}
	}

	/**
	 * Displays an error
	 * 
	 * @param view
	 *            Parent view
	 * @param th
	 *            Exception to display
	 * @see #getAlertManager()
	 * @see AlertManager#displayException(View, Throwable)
	 */
	public static void displayException(View view, Throwable th) {
		getAlertManager().displayException(view, th);
	}

	/**
	 * Gets the <code>{@link SwingFactory}</code> service.
	 * 
	 * @return Service instance
	 * @see SwingFactory
	 * @see #getService(Class)
	 */
	public static SwingFactory getSwingFactory() {
		return getService(SwingFactory.class);
	}

	/**
	 * Gets the <code>{@link DesktopService}</code> service.
	 * 
	 * @return Service instance
	 * @see DesktopService
	 * @see #getService(Class)
	 */
	public static DesktopService getDesktopService() {
		return getService(DesktopService.class);
	}

	/**
	 * Gets the <code>{@link Preferences}</code> service.
	 * 
	 * @return Service instance
	 * @see Preferences
	 * @see #getService(Class)
	 */
	public static Preferences getPreferences() {
		return getService(Preferences.class);
	}

	/**
	 * Gets the <code>{@link ViewManager}</code> service.
	 * 
	 * @return Service instance
	 * @see ViewManager
	 * @see #getService(Class)
	 */
	public static ViewManager getViewManager() {
		return getService(ViewManager.class);
	}

	/**
	 * Gets the <code>{@link IconManager}</code> service.
	 * 
	 * @return Service instance
	 * @see IconManager
	 * @see #getService(Class)
	 */
	public static IconManager getIconManager() {
		return getService(IconManager.class);
	}

	/**
	 * Gets a bean from the general configuration.
	 * 
	 * @param name
	 *            Bean name
	 * @return Bean instance
	 */
	public static Object getBean(String name) {
		return Application.getApplication().getBean(name);
	}
}
