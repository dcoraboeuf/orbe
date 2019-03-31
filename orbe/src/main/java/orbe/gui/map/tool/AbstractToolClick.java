/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.tool;

import java.awt.event.MouseEvent;

import orbe.model.PointDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractToolClick extends AbstractToolMap {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractToolClick.class);

	@Override
	public void mouseClicked(MouseEvent e) {
		PointDecimal pointPX = getPX(e);
		// Processes the click
		log.debug("Click at PX " + pointPX);
		onClick(pointPX, e);
	}

	protected abstract void onClick(PointDecimal pointKM, MouseEvent e);

}
