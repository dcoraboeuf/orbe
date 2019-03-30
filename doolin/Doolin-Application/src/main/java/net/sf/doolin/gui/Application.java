/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui;

import java.util.HashMap;

import javax.swing.SwingUtilities;

import net.sf.doolin.gui.auth.AuthManager;
import net.sf.doolin.gui.auth.support.DefaultAuthManager;
import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.DefaultIconManager;
import net.sf.doolin.gui.core.icon.IconManager;
import net.sf.doolin.gui.core.validation.DefaultValidationReporter;
import net.sf.doolin.gui.core.validation.ValidationReporter;
import net.sf.doolin.gui.field.support.FieldSupportFactory;
import net.sf.doolin.gui.field.support.swing.SwingFieldSupportFactory;
import net.sf.doolin.gui.monitor.TaskMonitorFactory;
import net.sf.doolin.gui.monitor.support.DefaultTaskMonitorFactory;
import net.sf.doolin.gui.service.AlertManager;
import net.sf.doolin.gui.service.CursorFactory;
import net.sf.doolin.gui.service.DesktopService;
import net.sf.doolin.gui.service.ExpressionEvaluator;
import net.sf.doolin.gui.service.MnemonicInfoFactory;
import net.sf.doolin.gui.service.Preferences;
import net.sf.doolin.gui.service.PropertyService;
import net.sf.doolin.gui.service.ScriptEvaluator;
import net.sf.doolin.gui.service.SwingFactory;
import net.sf.doolin.gui.service.ViewManager;
import net.sf.doolin.gui.service.support.DefaultAlertManager;
import net.sf.doolin.gui.service.support.DefaultCursorFactory;
import net.sf.doolin.gui.service.support.DefaultExpressionEvaluator;
import net.sf.doolin.gui.service.support.DefaultMnemonicInfoFactory;
import net.sf.doolin.gui.service.support.DefaultPreferences;
import net.sf.doolin.gui.service.support.DefaultPropertyService;
import net.sf.doolin.gui.service.support.DefaultScriptEvaluator;
import net.sf.doolin.gui.service.support.DefaultSwingFactory;
import net.sf.doolin.gui.service.support.DefaultViewManager;
import net.sf.doolin.gui.service.support.JDK6DesktopService;
import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.Strings;
import net.sf.doolin.util.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

;

/**
 * Top-level management of a Doolin application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Application.java,v 1.10 2007/08/17 15:07:01 guinnessman Exp $
 */
public class Application {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Application.class);

	/**
	 * Bundle
	 */
	static {
		Strings.add("net.sf.doolin.gui.DoolinGUIBundle");
	}

	/**
	 * Current application.
	 */
	private static Application application;

	/**
	 * ID class
	 */
	private static Class idClass;

	/**
	 * Context
	 */
	private static Context context = new Context();

	/**
	 * Returns the ID class, used to identify the running application at system
	 * level (for preferences management for example).
	 * 
	 * By default, the ID class is the class that has started the application
	 * (the class that contains the main).
	 * 
	 * @return A class, specific to the actual running application.
	 */
	public static Class getIdClass() {
		return idClass;
	}

	/**
	 * Sets (optionally) the ID class for the running application.
	 * 
	 * @param idClass
	 *            A class, specific to the actual running application.
	 */
	public static void setIdClass(Class idClass) {
		Application.idClass = idClass;
	}

	/**
	 * Launches the unique Doolin application for this JVM.
	 * 
	 * @param configurationPathArray
	 *            List of Spring configuration paths.
	 * @throws CodeException
	 *             <ul>
	 *             <li>net.sf.doolin.gui.ApplicationAlreadyLaunched - If the
	 *             application has already been launched
	 *             </ul>
	 */
	public static synchronized void launch(String[] configurationPathArray) {
		log.info("Starting application");
		if (application != null) {
			throw new CodeException(MessageCodes.APPLICATION_ALREADY_LAUNCHED);
		} else {
			// ID class
			if (idClass == null) {
				Throwable th = new Throwable();
				StackTraceElement[] stackTraces = th.getStackTrace();
				// The second step in the stack trace contains information about
				// the caller of this method
				StackTraceElement stackTraceElement = stackTraces[1];
				String idClassName = stackTraceElement.getClassName();
				log.info("Launching class is " + idClassName);
				idClass = Utils.forClass(idClassName);
			}
			// Spring configuration
			log.info("Reading configuration paths");

			ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(configurationPathArray);
			// Get the application manager singleton
			ApplicationManager applicationManager = (ApplicationManager) applicationContext.getBean(ApplicationManager.class.getName());
			// Setup the application singleton
			Application tempApplication = new Application();
			tempApplication.applicationContext = applicationContext;
			tempApplication.applicationManager = applicationManager;
			// Ok
			application = tempApplication;
			// Starts the application on the EDT
			SwingUtilities.invokeLater(applicationManager);
		}
		log.info("End of application start");
	}

	/**
	 * Get the application singleton.
	 * 
	 * @return Running application
	 * @throws IllegalStateException
	 *             If the application has not been initialized.
	 */
	public static Application getApplication() throws IllegalStateException {
		if (application != null) {
			return application;
		} else {
			throw new IllegalStateException("Application has not been initialized.");
		}
	}

	/**
	 * Application status.
	 * 
	 * @return <code>false</code> if the application is not ready to be used
	 *         or has not been initialized.
	 */
	public static boolean isReady() {
		return application != null;
	}

	/**
	 * Application manager
	 */
	private ApplicationManager applicationManager;

	/**
	 * Application context
	 */
	private ApplicationContext applicationContext;

	/**
	 * Default services
	 */
	private HashMap<Class, Object> defaultServices = new HashMap<Class, Object>();

	/**
	 * Initialization of default services
	 */
	{
		defaultServices.put(MnemonicInfoFactory.class, new DefaultMnemonicInfoFactory());
		defaultServices.put(SwingFactory.class, new DefaultSwingFactory());
		defaultServices.put(PropertyService.class, new DefaultPropertyService());
		defaultServices.put(ExpressionEvaluator.class, new DefaultExpressionEvaluator());
		defaultServices.put(FieldSupportFactory.class, new SwingFieldSupportFactory());
		defaultServices.put(ScriptEvaluator.class, new DefaultScriptEvaluator());
		defaultServices.put(DesktopService.class, new JDK6DesktopService());
		defaultServices.put(AlertManager.class, new DefaultAlertManager());
		defaultServices.put(Preferences.class, new DefaultPreferences());
		defaultServices.put(ViewManager.class, new DefaultViewManager());
		defaultServices.put(IconManager.class, new DefaultIconManager());
		defaultServices.put(ValidationReporter.class, new DefaultValidationReporter());
		defaultServices.put(TaskMonitorFactory.class, new DefaultTaskMonitorFactory());
		defaultServices.put(AuthManager.class, new DefaultAuthManager());
		defaultServices.put(CursorFactory.class, new DefaultCursorFactory());
	}

	/**
	 * Get the current application manager
	 * 
	 * @return Current application manager
	 */
	public ApplicationManager getApplicationManager() {
		return applicationManager;
	}

	/**
	 * Get a service by its interface name.
	 * 
	 * @param serviceClass
	 *            Service class
	 * @param <T>
	 *            Service class
	 * @return Service instance
	 */
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> serviceClass) {
		// Get the service from the application context
		String name = serviceClass.getName();
		if (applicationContext.containsBean(name)) {
			return (T) applicationContext.getBean(name);
		}
		// Get the service from the default services
		else if (defaultServices.containsKey(serviceClass)) {
			return (T) defaultServices.get(serviceClass);
		}
		// Unknown service
		else {
			throw new RuntimeException("Cannot find implementation for the service: " + serviceClass.getName());
		}
	}

	/**
	 * @return Context associated with the application.
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * Accesses a view definition by its name
	 * 
	 * @param viewName
	 *            View ID in the configuration
	 * @return Instance of view
	 */
	public View getView(String viewName) {
		return (View) applicationContext.getBean(viewName);
	}

	/**
	 * Gets a bean from the general configuration.
	 * 
	 * @param name
	 *            Bean name
	 * @return Bean instance
	 */
	public Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * Restarts the application.
	 */
	public static void restart() {
		log.info("Restarting the application.");
		// Starts the application on the EDT
		SwingUtilities.invokeLater(getApplication().getApplicationManager());
	}
}
