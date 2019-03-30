/*
 * Created on Sep 10, 2007
 */
package net.sf.doolin.gui.core;

/**
 * Defines a listener for view events.
 * 
 * @author BE05735
 * @version $Id$
 */
public interface ViewListener {

	/**
	 * This method is called whenever the view is closed.
	 * 
	 * @param v
	 *            Closed view
	 */
	void onViewClosed(View v);

}
