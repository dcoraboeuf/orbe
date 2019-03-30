/*
 * Created on 16 oct. 2005
 */
package net.sf.doolin.gui.core.icon;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.util.Utils;

/**
 * @author damien
 * @version $Id: ActionURLStreamHandler.java,v 1.1 2005/11/01 10:14:27
 *          guinnessman Exp $
 */
public class IconURLStreamHandler extends URLStreamHandler {

	/**
	 * Action protocol
	 */
	public static final String PROTOCOL_ICON = "icon";

	/**
	 * @see java.net.URLStreamHandler#openConnection(java.net.URL)
	 */
	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		// ID of the Icon to load
		String iconId = u.getPath();
		// Parameters
		Map<String, String> request = Utils.parseQuery(u);
		// Size of the icon
		IconSize size = IconSize.MEDIUM;
		String paramSize = request.get("size");
		if (StringUtils.isNotBlank(paramSize)) {
			paramSize = StringUtils.upperCase(paramSize);
			size = IconSize.valueOf(paramSize);
		}
		// Loads the icon
		IconManager iconManager = GUIUtils.getIconManager();
		URL iconURL = iconManager.getIconURL(iconId, size);
		if (iconURL == null) {
			return null;
		} else {
			return iconURL.openConnection();
		}
	}

}
