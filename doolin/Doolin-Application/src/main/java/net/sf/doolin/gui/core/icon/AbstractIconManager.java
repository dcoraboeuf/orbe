/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Icon manager that loads icon from a map of icon definitions.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractIconManager.java,v 1.4 2007/07/31 15:59:32 guinnessman
 *          Exp $
 */
public abstract class AbstractIconManager implements IconManager {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractIconManager.class);

	private final static String PREFIX = "/net/sf/doolin/gui/icon/";

	private Map<String, IconDefinition> iconDefinitions = new HashMap<String, IconDefinition>();

	private Map<String, IconItem> iconItems;

	/**
	 * Constructor.
	 * 
	 * Registers some predefined icons.
	 */
	public AbstractIconManager() {
		// Default icons
		iconDefinitions.put(Icons.ERROR, new IconDefinition(PREFIX, "ErrorMini.png", null, null, null, null));
	}

	/**
	 * Initializes the manager. This is a lazy initialization.
	 * 
	 * @see #load()
	 */
	protected void init() {
		if (iconItems == null) {
			load();
		}
	}

	/**
	 * This method actually initializes the icon manager.
	 */
	protected synchronized void load() {
		if (iconItems == null) {
			log.info("Loading all icons...");
			HashMap<String, IconItem> items = new HashMap<String, IconItem>();
			for (Map.Entry<String, IconDefinition> iconDefinitionEntry : iconDefinitions.entrySet()) {
				String id = iconDefinitionEntry.getKey();
				IconDefinition iconDefinition = iconDefinitionEntry.getValue();
				IconItem iconItem = new IconItem();
				//
				Map<IconSize, String> paths = iconDefinition.getPaths();
				for (Map.Entry<IconSize, String> pathEntry : paths.entrySet()) {
					IconSize size = pathEntry.getKey();
					String path = pathEntry.getValue();
					Icon iconObject = loadIconObject(path);
					if (iconObject != null) {
						iconItem.iconObjects.put(size, iconObject);
					}
				}
				// Ok
				items.put(id, iconItem);
			}
			log.info("End of the load of all icons.");
			iconItems = items;
		}
	}

	/**
	 * This method must be overriden by subclasses in order to load an icon from
	 * a path.
	 * 
	 * @param path
	 *            Path to the icon (could be a resource path but could be
	 *            something else according to the icon manager implementation).
	 * @return Icon
	 */
	protected abstract Icon loadIconObject(String path);

	/**
	 * This method must be overriden by subclasses in order to return the URL to
	 * an icon defined by the given path.
	 * 
	 * @param path
	 *            Path to the icon (could be a resource path but could be
	 *            something else according to the icon manager implementation).
	 * @return Icon URL
	 */
	protected abstract URL toURL(String path);

	public Icon getIcon(String id, IconSize size) {
		init();
		IconItem iconItem = iconItems.get(id);
		if (iconItem == null) {
			log.warn("Cannot find any icon for id = " + id);
			return null;
		} else {
			Icon iconObject = iconItem.iconObjects.get(size);
			if (iconObject != null) {
				return iconObject;
			} else {
				log.warn("Cannot find any icon for id = " + id + " and size = " + size);
				return null;
			}
		}
	}

	public URL getIconURL(String iconId, IconSize size) {
		init();
		IconDefinition iconDefinition = iconDefinitions.get(iconId);
		if (iconDefinition != null) {
			String path = iconDefinition.getPaths().get(size);
			if (path != null) {
				return toURL(path);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets the list of icon definitions
	 * 
	 * @return List of icon definitions
	 */
	public Map<String, IconDefinition> getIconDefinitions() {
		return iconDefinitions;
	}

	/**
	 * Sets the list of icon definitions
	 * 
	 * @param iconDefinitions
	 *            List of icon definitions
	 */
	public void setIconDefinitions(Map<String, IconDefinition> iconDefinitions) {
		this.iconDefinitions = iconDefinitions;
	}

	/**
	 * Associations between icon sizes and actual icons.
	 * 
	 * @author Damien Coraboeuf
	 * @version $Id: AbstractIconManager.java,v 1.6 2007/08/15 09:05:25
	 *          guinnessman Exp $
	 */
	protected static class IconItem {

		/**
		 * Associations between icon sizes and actual icons.
		 */
		protected Map<IconSize, Icon> iconObjects = new HashMap<IconSize, Icon>();

	}

}
