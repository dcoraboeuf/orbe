/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import orbe.model.OrbeMap;

public interface OrbeView {

	void refresh(OrbeMap map, Rectangle2D.Double zone);

	void paint(OrbeMap map, Graphics2D g, Rectangle2D.Double zone);

	void setRenderer(OrbeRenderer renderer);

	OrbeRenderer getRenderer();
	
	ViewSettings getViewSettings();

	Dimension getDisplaySize(OrbeMap map);

}
