/*
 * Created on Nov 30, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;
import orbe.model.element.line.OrbeLine;

/**
 * Rendu des lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LineRenderer.java,v 1.3 2006/12/04 14:10:48 guinnessman Exp $
 */
public interface LineRenderer<L extends OrbeLine> {

	boolean hasToRefresh(L line, OrbeMap map, ViewSettings viewSettings, Double zone);

	void render(L line, OrbeMap map, ViewSettings viewSettings, Graphics2D g);

	/**
	 * Zone de rafraichissement du dernier tronçon de la ligne
	 */
	Rectangle getLastRefreshZone(OrbeMap map, ViewSettings viewSettings, L line);

	/**
	 * Zone de rafraichissement globale de la ligne
	 */
	Rectangle getRefreshZone(OrbeMap map, ViewSettings viewSettings, L line);

	/**
	 * Tests if a point is "close" to a line. A point is close a line if the
	 * distance between this point and this line is less than 5 in PX space.
	 * 
	 * @param map
	 *            Map environment
	 * @param viewSettings
	 *            View settings
	 * @param line
	 *            Line to test
	 * @param p
	 *            Point to test
	 * @return <code>true</code> if the point is close to the line.
	 */
	boolean isOver(OrbeMap map, ViewSettings viewSettings, L line, Point p);

	/**
	 * Renvoit la forme à surligner d'une ligne.
	 * 
	 * @param map
	 *            Map environment
	 * @param viewSettings
	 *            View settings
	 * @param line
	 *            Line to outline
	 * @return Outlined shape
	 */
	Shape getOutline(OrbeMap map, ViewSettings viewSettings, L line);

}
