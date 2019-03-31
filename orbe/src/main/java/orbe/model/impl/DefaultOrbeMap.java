/*
 * Created on Oct 3, 2006
 */
package orbe.model.impl;

import net.sf.doolin.bus.Bus;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.OrbeMap;
import orbe.model.OrbeSettings;
import orbe.model.element.line.OrbeLineList;
import orbe.model.element.text.OrbeTextList;
import orbe.model.hex.DefaultHexMap;
import orbe.model.hex.HexMap;

/**
 * Implementation standard d'une carte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultOrbeMap.java,v 1.16 2006/12/06 13:48:11 guinnessman Exp $
 */
public class DefaultOrbeMap implements OrbeMap {

	public static final String VERSION = "2.0";

	private int width;

	private int height;

	private OrbeSettings settings;

	private HexMap hexMap;

	private OrbeTextList textList = new OrbeTextList();

	private OrbeLineList lineList = new OrbeLineList();

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
		Bus.get().publish(new OrbeMessageContextSettings(this));
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public String getVersion() {
		return VERSION;
	}

	public void setVersion(String v) {
		if (!VERSION.equals(v)) {
			throw new IllegalArgumentException("Expected version is " + VERSION);
		}
	}

	public HexMap getHexMap() {
		return hexMap;
	}

	public void setHexMap(HexMap hexMap) {
		this.hexMap = hexMap;
	}

	public HexMap createHexMap() {
		DefaultHexMap map = new DefaultHexMap(width, height, settings.getTerrains().getDefaultTerrain());
		return map;
	}

	public boolean isHexInBounds(HXPoint hxp) {
		int i = hxp.i;
		int j = hxp.j;
		return (i >= 0) && (j >= 0) && (i < width) && (j < height);
	}

	public OrbeTextList getTextList() {
		return textList;
	}

	public void setTextList(OrbeTextList textList) {
		this.textList = textList;
	}

	public OrbeLineList getLineList() {
		return lineList;
	}

	public void setLineList(OrbeLineList lineList) {
		this.lineList = lineList;
	}

	public HXGraphics getHXGraphics() {
		return new HXGraphics(getSettings().getScale());
	}

}
