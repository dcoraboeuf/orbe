/*
 * Created on Nov 10, 2006
 */
package orbe.gui.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Enables JMX management.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeManagerAgent.java,v 1.1 2006/11/10 13:16:55 guinnessman Exp $
 */
public class OrbeManagerAgent {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(OrbeManagerAgent.class);

	/**
	 * Unique instance
	 */
	private static OrbeManagerAgent instance = null;

	/**
	 * Get the instance
	 */
	public static OrbeManagerAgent getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OrbeManagerAgent createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OrbeManagerAgent temp = new OrbeManagerAgent();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Initialization
	 */
	private OrbeManagerAgent() {

	}

	public void register() {

		log.info("Register OrbeManagerAgent on JMX");

		Runnable jmxTask = new Runnable() {
			public void run() {
				// JMX platform
				MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

				// Get the instance of register
				OrbeManager orbeManager = OrbeManager.getInstance();

				try {

					// Get a unique name
					ObjectName objectName = new ObjectName("Orbe:type=OrbeManager");

					// Registration
					mbs.registerMBean(orbeManager, objectName);

					// Wait for ever...
					Thread.sleep(Long.MAX_VALUE);

				} catch (Exception ex) {
					// Doe snot block in case of an error
					log.error("Cannot register OrbeManagerAgent for JMX", ex);
				}
			}
		};

		Thread jmx = new Thread(jmxTask, "OrbeJMX");
		jmx.setDaemon(true);
		jmx.start();
	}

}
