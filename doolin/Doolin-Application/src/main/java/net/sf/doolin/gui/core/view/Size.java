/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.Component;

import net.sf.doolin.gui.core.View;

public interface Size {
	
	void setSize (View view, Component component);
	
	void saveSize (View view, Component component);

}
