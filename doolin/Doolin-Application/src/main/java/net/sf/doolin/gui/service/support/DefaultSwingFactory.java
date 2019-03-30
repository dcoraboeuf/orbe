/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import java.awt.Component;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.auth.AuthUtils;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.plugin.PluginHelper;
import net.sf.doolin.gui.core.view.Menu;
import net.sf.doolin.gui.core.view.MenuAction;
import net.sf.doolin.gui.core.view.MenuItem;
import net.sf.doolin.gui.core.view.MenuSep;
import net.sf.doolin.gui.core.view.Menubar;
import net.sf.doolin.gui.core.view.Toolbar;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.service.MnemonicInfo;
import net.sf.doolin.gui.service.MnemonicInfoFactory;
import net.sf.doolin.gui.service.SwingFactory;
import net.sf.doolin.gui.swing.BarContainer;
import net.sf.doolin.gui.swing.SwingAction;

/**
 * Utilities for Swing binding.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultSwingFactory.java,v 1.1 2007/07/18 17:50:57 guinnessman
 *          Exp $
 */
public class DefaultSwingFactory implements SwingFactory {

	/**
	 * @see SwingAction
	 * @see net.sf.doolin.gui.service.SwingFactory#createSwingAction(net.sf.doolin.gui.core.Action,
	 *      net.sf.doolin.gui.core.View,
	 *      net.sf.doolin.gui.core.action.ActionControler,
	 *      net.sf.doolin.gui.core.icon.IconSize)
	 */
	public Action createSwingAction(net.sf.doolin.gui.core.Action action, View view, ActionControler controler, IconSize iconSize) {
		return new SwingAction(action, view, controler, iconSize);
	}

	/**
	 * @see net.sf.doolin.gui.service.SwingFactory#setMenubar(net.sf.doolin.gui.core.View,
	 *      java.awt.Component)
	 */
	public void setMenubar(View view, Component component) {
		Menubar menubar = view.getMenubar();
		if (menubar != null) {
			// Creates the menubar
			JMenuBar jmenubar = new JMenuBar();
			// For each menu
			for (Menu menu : menubar.getMenus()) {
				JMenu jmenu = createMenu(menu, view);
				if (jmenu != null) {
					jmenubar.add(jmenu);
				}
			}
			// Sets the menubar
			setJMenubar(jmenubar, component);
		}
	}

	/**
	 * Setup of a menu bar on a component. Supported components are:
	 * <ul>
	 * <li>{@link JFrame}
	 * </ul>
	 * 
	 * @param bar
	 *            Menubar to install
	 * @param component
	 *            Component to setup
	 */
	protected void setJMenubar(JMenuBar bar, Component component) {
		if (component instanceof JFrame) {
			((JFrame) component).setJMenuBar(bar);
		} else {
			throw new IllegalStateException("Cannot set a menubar on " + component.getClass().getName());
		}
	}

	/**
	 * Creates a Swing menu from a menu factory.
	 * 
	 * @param menu
	 *            Menu definition
	 * @param view
	 *            Hosting view
	 * @return Swing menu
	 */
	protected JMenu createMenu(Menu menu, View view) {
		// Auth
		if (!AuthUtils.getAuthManager().isAuthorized(view, menu)) {
			return null;
		}
		// Goes on
		JMenu jmenu = new JMenu();
		// Menu name
		String menuName = GUIStrings.get(menu.getName());
		// Menu text
		MnemonicInfoFactory labelInfoFactory = Application.getApplication().getService(MnemonicInfoFactory.class);
		MnemonicInfo labelInfo = labelInfoFactory.getLabelInfo(menuName);
		labelInfo.configureButton(jmenu);
		// Menu items
		boolean separator = true;
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem instanceof Menu) {
				JMenu jsubmenu = createMenu((Menu) menuItem, view);
				jmenu.add(jsubmenu);
				separator = false;
			} else if (menuItem instanceof MenuSep) {
				if (!separator) {
					jmenu.addSeparator();
					separator = true;
				}
			} else if (menuItem instanceof MenuAction) {
				MenuAction menuAction = (MenuAction) menuItem;
				net.sf.doolin.gui.core.Action action = menuAction.getAction();
				if (AuthUtils.getAuthManager().isAuthorized(view, action)) {
					SwingFactory swingFactory = Application.getApplication().getService(SwingFactory.class);
					javax.swing.Action swingAction = swingFactory.createSwingAction(action, view, null, IconSize.SMALL);
					jmenu.add(swingAction);
					separator = false;
				}
			}
		}
		// Activates plugins
		PluginHelper.activatePlugins(menu.getPlugins(), jmenu, null);
		// Ok
		return jmenu;
	}

	/**
	 * Installs the toolbars.
	 * 
	 * @see net.sf.doolin.gui.service.SwingFactory#setToolbars(net.sf.doolin.gui.core.View,
	 *      net.sf.doolin.gui.swing.BarContainer)
	 */
	public void setToolbars(View view, BarContainer container) {
		List<Toolbar> toolbars = view.getToolbars();
		if (toolbars != null) {
			for (Toolbar toolbar : toolbars) {
				// Creates the toolbar component
				JToolBar jtoolbar = createToolbar(view, toolbar);
				// Adds the toolbar component to the bar container
				container.addBar(jtoolbar, toolbar.getPosition());
			}
		}
	}

	/**
	 * Creates a toolbar.
	 * 
	 * @param view
	 *            Hosting view
	 * @param toolbar
	 *            Toolbar definition
	 * @return Toolbar
	 * @see Toolbar#createToolbar(View)
	 */
	protected JToolBar createToolbar(View view, Toolbar toolbar) {
		return toolbar.createToolbar(view);
	}

}
