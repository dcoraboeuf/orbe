/*
 * Created on Nov 8, 2006
 */
package orbe.gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.plugin.AbstractPlugin;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.Preferences;
import orbe.gui.IActions;
import orbe.gui.IViews;
import orbe.gui.message.OrbeMessageFile;

import org.apache.commons.lang.StringUtils;

/**
 * Gestion des fichiers récents.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PluginRecentFiles.java,v 1.2 2006/11/29 11:41:59 guinnessman Exp $
 */
public class PluginMenuRecentFiles extends AbstractPlugin<JMenu> {

	private static final String PREF_FILES = "MenuRecentFiles.files";

	private final class ActionOpenRecent implements ActionListener {

		private String path;

		private ActionOpenRecent(String path) {
			this.path = path;
		}

		public void actionPerformed(ActionEvent e) {
			View mainFrame = GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN);
			ActionOpen actionOpen = (ActionOpen) Application.getApplication().getBean(IActions.ACTION_OPEN);
			actionOpen.setParamFile(new File(path));
			mainFrame.execute(actionOpen);
		}
	}

	private JMenu menu;

	private LinkedList<String> files;
	
	public void activate(JMenu context, String scope) {
		menu = context;

		// Liste des fichiers récents
		String token = GUIUtils.getPreferences().getString(PREF_FILES, "");
		String[] paths = StringUtils.split(token, ";");
		files = new LinkedList<String>();
		for (String path : paths) {
			files.add(path);
		}
		setup();

		// Notification pour l'ouverture des fichiers
		Bus.get().subscribe(OrbeMessageFile.class, new Subscriber<OrbeMessageFile>() {

			public void receive(OrbeMessageFile message) {
				File file = message.getValue();
				String path = file.getAbsolutePath();
				if (files.contains(path)) {
					files.remove(path);
					files.addFirst(path);
				} else {
					files.addFirst(path);
				}
				setup();
			}

		});
	}

	private void setup() {
		// Checks the list
		Iterator<String> i = files.iterator();
		while (i.hasNext()) {
			String path = i.next();
			File file = new File(path);
			if (!file.exists()) {
				i.remove();
			}
		}
		// Truncates the list
		while (files.size() > 8) {
			files.removeLast();
		}
		// Fills the menu
		menu.removeAll();
		int index = 1;
		for (String path : files) {
			// Libellé
			String label = index + " - " + path;
			// Menu
			JMenuItem item = new JMenuItem(label);
			item.setMnemonic(String.valueOf(index).charAt(0));
			// Action
			item.addActionListener(new ActionOpenRecent(path));
			// Ok
			menu.add(item);
			index++;
		}
		// Updates the preferences
		String token = StringUtils.join(files.toArray(), ";");
		Preferences preferences = GUIUtils.getPreferences();
		preferences.setString(PREF_FILES, token);
		preferences.save();
	}

}
