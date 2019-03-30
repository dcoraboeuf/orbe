/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map.tool;

import java.awt.event.MouseEvent;

import orbe.model.PointDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractToolDrag extends AbstractToolMap {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractToolDrag.class);

	protected void debug(String type, MouseEvent e) {
		if (log.isDebugEnabled()) {
			String message = type + " " + e.getX() + "," + e.getY();
			log.debug(message);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		debug("DRAGGED", e);
		PointDecimal pointPX = getPX(e);
		onDragged(pointPX, e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		debug("PRESSED", e);
		PointDecimal pointPX = getPX(e);
		onPressed(pointPX, e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		debug("RELEASED", e);
		PointDecimal pointPX = getPX(e);
		onReleased(pointPX, e);
	}

	protected abstract void onDragged(PointDecimal pointPX, MouseEvent e);

	protected abstract void onPressed(PointDecimal pointPX, MouseEvent e);

	protected abstract void onReleased(PointDecimal pointPX, MouseEvent e);

}
