/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.io.File;

import orbe.model.hex.DefaultHexMap;
import orbe.model.impl.DefaultOrbeMap;
import orbe.model.io.OrbeIO;

/**
 * Creation de cartes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMapFactory.java,v 1.3 2006/10/24 16:52:15 guinnessman Exp $
 */
public class OrbeMapFactory {
	/**
	 * Unique instance
	 */
	private static OrbeMapFactory instance = null;

	/**
	 * Get the instance
	 */
	public static OrbeMapFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OrbeMapFactory createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OrbeMapFactory temp = new OrbeMapFactory();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Initialization
	 */
	private OrbeMapFactory() {
	}

	/**
	 * Lecture depuis un fichier
	 */
	public OrbeMap readMap(File file) {
		DefaultOrbeMap map = OrbeIO.getInstance().read(DefaultOrbeMap.class, file);
		return map;
	}

	/**
	 * Création d'une carte vierge
	 */
	public OrbeMap createMap(OrbeMapConfig config) {
		DefaultOrbeMap map = new DefaultOrbeMap();
		map.setWidth(config.getWidth());
		map.setHeight(config.getHeight());
		map.setSettings(config.getSettings());
		map.setHexMap(new DefaultHexMap(config.getWidth(), config.getHeight(), map.getSettings().getTerrains().getDefaultTerrain()));
		return map;
	}
}
