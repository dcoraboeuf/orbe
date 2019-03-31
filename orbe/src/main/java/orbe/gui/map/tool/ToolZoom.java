/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;

import orbe.gui.cursor.ICursors;
import orbe.gui.task.OrbeTaskZoom;
import orbe.model.PointDecimal;

public class ToolZoom extends AbstractToolClick {

	private Cursor zoomOutCursor;

	private Cursor zoomInCursor;

	public ToolZoom() {
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		zoomOutCursor = cursorFactory.getCursor(ICursors.ZOOM_OUT);
		zoomInCursor = cursorFactory.getCursor(ICursors.ZOOM_IN);
	}

	@Override
	protected void onClick(PointDecimal pointPX, MouseEvent e) {
		// Current zoom
		BigDecimal zoom = getViewSettings().getZoom();
		// Adjust the zoom factor
		zoom = getNewZoom(zoom, e);
		// Set the new zoom factor
		OrbeTaskZoom task = new OrbeTaskZoom(getControler(), zoom);
		task.setCenterPX(pointPX);
		task.process();
	}

	protected BigDecimal getNewZoom(BigDecimal zoom, MouseEvent e) {
		if (e.isControlDown()) {
			return zoom.divide(new BigDecimal(2), 3, RoundingMode.HALF_UP);
		} else {
			return zoom.multiply(new BigDecimal(2));
		}
	}

	public String getId() {
		return ITool.ZOOM;
	}

	/**
	 * Gestion du curseur.
	 * 
	 * @see orbe.gui.map.tool.AbstractTool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.isControlDown()) {
			setCursor(zoomOutCursor);
		} else {
			setCursor(zoomInCursor);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			setCursor(zoomOutCursor);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			setCursor(zoomInCursor);
		}
	}

}
