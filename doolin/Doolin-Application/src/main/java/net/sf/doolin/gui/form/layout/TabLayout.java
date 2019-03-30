/*
 * Created on Sep 19, 2007
 */
package net.sf.doolin.gui.form.layout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;

import org.apache.commons.lang.StringUtils;

/**
 * Layout of fields using tabs.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class TabLayout extends AbstractLayout {

	private List<Tab> tabs = new ArrayList<Tab>();
	
	private JTabbedPane tabbedPane;

	public JComponent getComponent() {
		return tabbedPane;
	}

	public void init() {
		// Tabbed pane
		tabbedPane = new JTabbedPane();
		
		// For each tab
		for (Tab tab : tabs) {
			// Gets the title
			String tabTitle = null;
			String title = tab.getTitle();
			if (StringUtils.isNotBlank(title)) {
				tabTitle = GUIStrings.get(title);
			}
			// Gets the icon
			Icon tabIcon = null;
			String icon = tab.getIcon();
			if (StringUtils.isNotBlank(icon)) {
				tabIcon = GUIUtils.getIcon(icon, IconSize.MEDIUM);
			}
			// Layouts the tab
			Layout layout = tab.getLayout();
			layout.setForm(getForm());
			layout.init();
			// Adds the tab
			tabbedPane.addTab(tabTitle, tabIcon, layout.getComponent());
		}
	}

	public List<Tab> getTabs() {
		return tabs;
	}

	/**
	 * Sets the list of tabs
	 * 
	 * @param tabs
	 *            List of tabs
	 */
	@Configurable(mandatory = false, description = "List of tabs", defaultsTo = "empty")
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}

}
