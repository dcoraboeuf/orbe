/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;

import net.sf.doolin.gui.core.plugin.PluginAwareBean;

/**
 * Menu bar definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Menubar.java,v 1.1 2007/07/18 17:51:00 guinnessman Exp $
 */
public class Menubar extends PluginAwareBean<JMenuBar> {
	
	private List<Menu> menus = new ArrayList<Menu>();

	/**
	 * @return Returns the menus.
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * @param menus The menus to set.
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
