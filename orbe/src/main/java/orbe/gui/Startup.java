/*
 * Created on Tue Oct 03 14:42:35 CEST 2006
 */
package orbe.gui;

import java.io.File;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.FrameStartup;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.monitor.TaskMonitor;
import net.sf.doolin.gui.monitor.TaskMonitorFactory;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.util.task.AbstractTask;
import orbe.gui.jmx.OrbeManagerAgent;
import orbe.gui.main.ActionOpen;
import orbe.gui.map.renderer.SymbolRenderer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Startup of the application.
 * 
 * @version $Id: Startup.java,v 1.5 2006/11/24 15:20:35 guinnessman Exp $
 */
public class Startup extends FrameStartup {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Startup.class);

	/**
	 * Démarrage de l'application après initialisation de Doolin.
	 */
	@Override
	public void start(ApplicationManager applicationManager) {
		// Use a progress dialog to load all ressources
		StartupTask task = new StartupTask(applicationManager);
		// Get the monitor
		TaskMonitor monitor = GUIUtils.getService(TaskMonitorFactory.class).createMonitor(null);
		monitor.setCancellable(false);
		monitor.setDoCycle(false);
		monitor.setMaximum(task.getMaximum());
		monitor.setMessage(GUIStrings.get("Startup.monitor.message"));
		monitor.setTitle(GUIStrings.get("Startup.monitor.title"));
		monitor.start(task);
		// Opens the main frame
		super.start(applicationManager);
		// Opens a file at startup
		String fileName = (String) Application.getContext().getAttribute(IAttributes.APPLICATION_STARTUP_FILE);
		if (StringUtils.isNotBlank(fileName)) {
			open(applicationManager, fileName);
		}
	}

	/**
	 * Ouverture d'un fichier au démarrage
	 * 
	 * @param applicationManager
	 *            Application
	 * @param fileName
	 *            Chemin du fichier � ouvrir
	 */
	protected void open(ApplicationManager applicationManager, String fileName) {
		log.info("Opening file at startup: " + fileName);
		// Get the frame
		View frame = GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN);
		// Get the action open
		ActionOpen open = (ActionOpen) Application.getApplication().getBean(IActions.ACTION_OPEN);
		open.setParamFile(new File(fileName));
		frame.execute(open);
	}

	protected class StartupTask extends AbstractTask {

		public StartupTask(ApplicationManager applicationManager) {
		}

		public int getMaximum() {
			return 2;
		}

		public String getName() {
			return "Orbe Startup";
		}

		protected void execute() {
			// Orbe JMX interface
			log("Startup.JMX");
			OrbeManagerAgent.getInstance().register();
			// Symbols
			log("Startup.Symbols");
			SymbolRenderer.getInstance();
			// Ok
			fireFinish();
		}

		private void log(String code) {
			fireProgress(1, GUIStrings.get(code));
		}

	}

}
