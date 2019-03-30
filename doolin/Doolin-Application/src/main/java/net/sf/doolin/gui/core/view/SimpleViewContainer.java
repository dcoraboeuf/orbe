/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.Component;

import net.sf.doolin.gui.core.View;

/**
 * This container just hosts the child view component in a panel.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SimpleViewContainer.java,v 1.1 2007/07/31 15:32:58 guinnessman Exp $
 */
public class SimpleViewContainer extends AbstractFrameViewContainer {

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.core.ViewContainer#close()
	 */
	public void close() {
	}

	/**
	 * Just returns <code>true</code>.
	 * 
	 * @see net.sf.doolin.gui.core.ViewContainer#display(net.sf.doolin.gui.core.View)
	 */
	public boolean display(View parentView) {
		return true;
	}

	/**
	 * Returns the container.
	 * 
	 * @see AbstractFrameViewContainer#getContainer()
	 * @see net.sf.doolin.gui.core.ViewContainer#getComponent()
	 */
	public Component getComponent() {
		return getContainer();
	}
	
	/**
	 * Does nothing
	 */
	@Override
	public void activate() {
	}

}
