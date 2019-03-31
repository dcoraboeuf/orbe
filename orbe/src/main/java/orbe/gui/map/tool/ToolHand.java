/*
 * Created on Dec 6, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;
import orbe.gui.cursor.ICursors;

/**
 * Outil de navigation.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolHand.java,v 1.1 2006/12/06 15:26:36 guinnessman Exp $
 */
public class ToolHand extends AbstractTool {

	private Cursor handCursor;

	private Point initialPosition;

	private Rectangle initialVisibleRect;

	public ToolHand() {
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		handCursor = cursorFactory.getCursor(ICursors.HAND);
	}

	public String getId() {
		return ITool.HAND;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		setCursor(handCursor);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Component associated with the controler
		JComponent component = getControler().getComponent();
		// Get the initial position on screen
		initialPosition = e.getPoint();
		SwingUtilities.convertPointToScreen(initialPosition, component);
		// Get the initial visible rect
		initialVisibleRect = component.getVisibleRect();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		setCursor(handCursor);
		// Component associated with the controler
		JComponent component = getControler().getComponent();
		// Get position on the screen
		Point p = e.getPoint();
		SwingUtilities.convertPointToScreen(p, component);
		// Calculates the offset
		int dx = p.x - initialPosition.x;
		int dy = p.y - initialPosition.y;
		// Offset the visible rect
		Rectangle r = new Rectangle(initialVisibleRect);
		r.translate(-dx, -dy);
		// Set as new visible zone
		component.scrollRectToVisible(r);
	}
}
