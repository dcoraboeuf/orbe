/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;

import net.sf.doolin.util.Pair;
import net.sf.doolin.util.TreeProperties;

/**
 * This icon manager loads icon definitions from a property file.
 * 
 * This property file is configured as a tree of properties, with following
 * nodes:
 * <ul>
 * <li><b>prefix</b> - (optional) defines a classpath prefix used to load all
 * other classpaths defined in the property file
 * <li><b>icon</b> - defines an icon
 * <ul>
 * <li><b>&lt;id&gt;</b> - id of the icon
 * <ul>
 * <li><b>mini</b> - path suffix for the mini size icon
 * <li><b>small</b> - path suffix for the small size icon
 * <li><b>medium</b> - path suffix for the medium size icon
 * <li><b>large</b> - path suffix for the large size icon
 * <li><b>big</b> - path suffix for the big size icon
 * </ul>
 * </ul>
 * </ul>
 * 
 * For example, we can consider the following property file:
 * 
 * <pre style="border: solid black 1px, background-color:#CCCCCC;">
 *   prefix = /my/application/
 *   
 *   icon.Frame.small=Main16.gif
 *   icon.Frame.medium=Main32.gif
 *   
 *   icon.Button.small=Button16.gif
 *   icon.Button.medium=Button32.gif
 *   
 *   icon.Logo.big=Logo.gif
 * </pre>
 * 
 * @author Damien Coraboeuf
 * @version $Id: PropertyIconManager.java,v 1.2 2007/08/15 09:05:25 guinnessman Exp $
 */
public class PropertyIconManager extends DefaultIconManager implements InitializingBean {

	private Properties properties;

	/**
	 * @return Returns the properties.
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            The properties to set.
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * Loads all definitions from the property file.
	 */
	public void afterPropertiesSet() throws Exception {
		// Loads the properties as a tree property file
		TreeProperties<String> tree = new TreeProperties<String>(properties);
		// Get the prefix
		String prefix = tree.get("prefix");
		// Get all icons
		TreeProperties<String> icons = tree.getTree("icon");
		for (Pair<String, TreeProperties<String>> entry : icons.entries()) {
			String iconId = entry.getFirstValue();
			TreeProperties<String> iconTree = entry.getSecondValue();
			String mini = iconTree.get("mini");
			String small = iconTree.get("small");
			String medium = iconTree.get("medium");
			String large = iconTree.get("large");
			String big = iconTree.get("big");
			IconDefinition iconDefinition = new IconDefinition(prefix, mini, small, medium, big, large);
			getIconDefinitions().put(iconId, iconDefinition);
		}
	}

}
