/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.math.BigDecimal;


/**
 * Configuration pour la création d'une nouvelle carte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMapConfig.java,v 1.3 2006/12/06 15:31:11 guinnessman Exp $
 */
public class OrbeMapConfig {
	private int width = 40;

	private int height = 60;

	private OrbeSettings settings = new OrbeSettings();
	
	public OrbeMapConfig() {
		settings.getScale().setUnit(Unit.PIXEL);
		settings.getScale().setValue(new BigDecimal(20));
	}

	/**
	 * @return the settings
	 */
	public OrbeSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(OrbeSettings settings) {
		this.settings = settings;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
