/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JToolBar;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.swing.BarPosition;

/**
 * Default ancestor for toolbars implementation.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractToolbar implements Toolbar {

	private BarPosition position = BarPosition.TOP;

	private boolean floatable = true;

	/**
	 * Returns if the toolbar is floatable
	 * 
	 * @return Floatable toobar status
	 */
	public boolean isFloatable() {
		return floatable;
	}

	/**
	 * Sets if the toolbar is floatable
	 * 
	 * @param floatable
	 *            Floatable toobar status
	 */
	@Configurable(mandatory = false, description = "If the toolbar is floatable", defaultsTo = "true")
	public void setFloatable(boolean floatable) {
		this.floatable = floatable;
	}

	/**
	 * Returns the bar position
	 * 
	 * @see net.sf.doolin.gui.core.view.Toolbar#getPosition()
	 */
	public BarPosition getPosition() {
		return position;
	}

	/**
	 * Sets the bar position
	 * 
	 * @param position
	 */
	@Configurable(mandatory = false, description = "Toolbar position", defaultsTo = "TOP")
	public void setPosition(BarPosition position) {
		this.position = position;
	}
	
	public JToolBar createToolbar(View view) {
		JToolBar j = new JToolBar();
		j.setFloatable(floatable);
		return j;
	}

}
