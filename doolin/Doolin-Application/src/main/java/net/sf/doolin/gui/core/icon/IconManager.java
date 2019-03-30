/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.net.URL;

import javax.swing.Icon;

/**
 * Definition for an icon manager service.
 * 
 * @author Damien Coraboeuf
 * @version $Id: IconManager.java,v 1.4 2007/08/10 16:54:44 guinnessman Exp $
 */
public interface IconManager {

	/**
	 * Fetch an icon
	 * 
	 * @param id
	 *            Icon id
	 * @param size
	 *            Required icon size
	 * @return Icon
	 */
	Icon getIcon(String id, IconSize size);

	/**
	 * Returns the URL to an icon.
	 * 
	 * @param id
	 *            Icon id
	 * @param size
	 *            Required icon size
	 * @return URL to the icon
	 */
	URL getIconURL(String iconId, IconSize medium);

}
