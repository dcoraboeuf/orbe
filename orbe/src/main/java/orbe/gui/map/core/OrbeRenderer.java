/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.core;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import orbe.model.OrbeMap;

public interface OrbeRenderer {

	void paint (OrbeMap map, ViewSettings viewSettings, Graphics2D g, Rectangle2D.Double	zone);
	
}

