/*
 * Created on Sep 19, 2007
 */
package net.sf.doolin.gui.form.layout;

import net.sf.doolin.gui.annotation.Configurable;

/**
 * Tab definition for a <code>{@link TabLayout}</code>.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class Tab {

	private String title;

	private String icon;

	private Layout layout;

	public String getTitle() {
		return title;
	}

	/**
	 * Sets the key for the tab title.
	 * 
	 * @param title
	 *            String key
	 */
	@Configurable(mandatory = true, description = "Key for the tab title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the key for the tab icon.
	 * 
	 * @param icon
	 *            Icon key
	 */
	@Configurable(mandatory = false, description = "Key for the tab icon", defaultsTo = "null")
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Layout getLayout() {
		return layout;
	}

	/**
	 * Sets the tab layout
	 * 
	 * @param layout
	 *            Layout
	 */
	@Configurable(mandatory = true, description = "Tab layout")
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

}
