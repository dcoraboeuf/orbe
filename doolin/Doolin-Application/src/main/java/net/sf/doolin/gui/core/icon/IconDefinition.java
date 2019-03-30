/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.icon;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Association of icon sizes and corresponding paths.
 * 
 * @author Damien Coraboeuf
 * @version $Id: IconDefinition.java,v 1.3 2007/08/15 09:05:25 guinnessman Exp $
 */
public class IconDefinition {

	private Map<IconSize, String> paths = new HashMap<IconSize, String>();

	/**
	 * Empty constructor.
	 * 
	 */
	public IconDefinition() {
	}

	/**
	 * Builds an icon definition using a prefix and some suffixes for the paths.
	 * 
	 * @param prefix
	 *            Prefix to use for the paths.
	 * @param miniPath
	 *            Path suffix to the mini icon or <code>null</code>
	 * @param smallPath
	 *            Path suffix to the small icon or <code>null</code>
	 * @param mediumPath
	 *            Path suffix to the medium icon or <code>null</code>
	 * @param largePath
	 *            Path suffix to the large icon or <code>null</code>
	 * @param bigPath
	 *            Path suffix to the big icon or <code>null</code>
	 */
	public IconDefinition(String prefix, String miniPath, String smallPath, String mediumPath, String largePath, String bigPath) {
		put(IconSize.MINI, prefix, miniPath);
		put(IconSize.SMALL, prefix, smallPath);
		put(IconSize.MEDIUM, prefix, mediumPath);
		put(IconSize.LARGE, prefix, largePath);
		put(IconSize.BIG, prefix, bigPath);
	}

	/**
	 * Registers a path prefix and a path suffix for a given icon size
	 * 
	 * @param size
	 *            Icon size
	 * @param prefix
	 *            Path prefix
	 * @param path
	 *            Path suffix
	 */
	protected void put(IconSize size, String prefix, String path) {
		if (StringUtils.isNotBlank(path)) {
			path = prefix + path;
			paths.put(size, path);
		}
	}

	/**
	 * Gets the map of paths and icon sizes
	 * 
	 * @return Paths indexed by icon sizes
	 */
	public Map<IconSize, String> getPaths() {
		return paths;
	}

	/**
	 * Sets the map of paths and icon sizes
	 * 
	 * @param paths
	 *            Paths indexed by icon sizes
	 */
	public void setPaths(Map<IconSize, String> paths) {
		this.paths = paths;
	}

}
