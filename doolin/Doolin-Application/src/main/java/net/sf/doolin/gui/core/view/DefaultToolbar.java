/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.core.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JToolBar;

import net.sf.doolin.gui.core.View;

/**
 * Default toolbar implementation.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class DefaultToolbar extends AbstractToolbar {

	private List<ToolbarItem> items = new ArrayList<ToolbarItem>();

	/**
	 * Returns the list of items for this toolbar.
	 * 
	 * @return List of toolbar items
	 */
	public List<ToolbarItem> getItems() {
		return items;
	}

	/**
	 * Sets the list of items for this toolbar.
	 * 
	 * @param items
	 *            List of toolbar items
	 */
	public void setItems(List<ToolbarItem> items) {
		this.items = items;
	}

	public JToolBar createToolbar(View view) {
		// Toolbar
		JToolBar j = super.createToolbar(view);

		// Configuration

		// For each item
		for (ToolbarItem toolbarItem : items) {
			toolbarItem.createToolbarItem(j, view);
		}

		// Ok
		return j;
	}

}
