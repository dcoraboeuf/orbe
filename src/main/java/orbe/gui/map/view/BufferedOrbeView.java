/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.jmx.OrbeManager;
import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BufferedOrbeView extends AbstractOrbeView {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(BufferedOrbeView.class);

	/**
	 * Internal image
	 */
	private OrbeBufferedImage image;

	/**
	 * Constructor
	 * 
	 * @param map
	 *            Associated model
	 */
	public BufferedOrbeView(OrbeMap map, ViewSettings settings) {
		super(map, settings);

		// Display size
		Dimension size = getDisplaySize(map);
		int width = size.width;
		int height = size.height;

		// Builds the in-memory image
		log.debug("RESIZE " + width + "," + height);
		image = new OrbeBufferedImage(width, height);
	}

	public void paint(OrbeMap map, Graphics2D g, Double zone) {
		if (OrbeManager.getInstance().getLayeredOrbeRendererDebug()) {
			log.debug("PAINT " + zone);
		}
		g.drawRenderedImage(image, null);
	}

	public OrbeBufferedImage getImage() {
		return image;
	}

}
