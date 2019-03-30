/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.element.line.OrbeLineList;
import orbe.model.element.text.OrbeTextList;
import orbe.model.hex.HexMap;

/**
 * General structure.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMap.java,v 1.11 2006/12/06 13:48:12 guinnessman Exp $
 */
public interface OrbeMap {

	/**
	 * @return Largeur en hexs
	 */
	int getWidth();

	/**
	 * @return Hauteur en hexs
	 */
	int getHeight();

	/**
	 * @return Param�tres
	 */
	OrbeSettings getSettings();

	/**
	 * Actualisation des param�tres
	 * 
	 * @param settings
	 *            Nouveaux param�tres
	 */
	void setSettings(OrbeSettings settings);

	/**
	 * Version
	 */
	String getVersion();

	/**
	 * Hexs
	 */
	HexMap getHexMap();

	/**
	 * D�termine si l'hex est dans la carte.
	 */
	boolean isHexInBounds(HXPoint hxp);

	/**
	 * Composants texte
	 */
	OrbeTextList getTextList();
	
	/**
	 * Composants lignes
	 */
	OrbeLineList getLineList();

	/**
	 * HX graphics
	 */
	HXGraphics getHXGraphics();

}
