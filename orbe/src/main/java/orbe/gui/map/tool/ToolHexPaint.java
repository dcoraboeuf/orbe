/*
 * Created on Nov 7, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;
import orbe.gui.cursor.ICursors;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.map.options.PanelOptionsHexPaint;
import orbe.gui.message.OrbeMessageTerrain;
import orbe.gui.task.OrbeTaskHexPaint;
import orbe.hex.HXGeom;
import orbe.hex.HXGraphics;
import orbe.hex.HXPaintControler;
import orbe.hex.HXPoint;
import orbe.model.PointDecimal;
import orbe.model.hex.Hex;
import orbe.model.hex.HexFlag;
import orbe.model.style.HexTerrain;

/**
 * Outil de remplissage.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolHexPaint.java,v 1.10 2006/11/22 20:13:35 guinnessman Exp $
 */
public class ToolHexPaint extends AbstractToolClick {

	public ToolHexPaint() {
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		pipetteCursor = cursorFactory.getCursor(ICursors.PIPETTE);
		paintCursor = cursorFactory.getCursor(ICursors.PAINT);
	}

	private PanelOptionsHexPaint panelOptions;

	private HXGraphics hxg;

	private Cursor pipetteCursor;

	private Cursor paintCursor;

	@Override
	public PanelOptions getPanelOptions() {
		if (panelOptions == null) {
			panelOptions = new PanelOptionsHexPaint();
		}
		return panelOptions;
	}

	@Override
	public void setControler(OrbeControler c) {
		super.setControler(c);
		hxg = new HXGraphics(getMap().getSettings().getScale());
	}

	@Override
	protected void onClick(PointDecimal pointPX, MouseEvent e) {
		// Hex cliqué
		HXPoint hxp = hxg.locateHex(pointPX);
		// Hex valide ?
		if (getMap().isHexInBounds(hxp)) {
			// Sélection du terrain
			if (e.isControlDown()) {
				Hex hex = getMap().getHexMap().getHex(hxp);
				HexTerrain terrain = hex.getTerrain();
				Bus.get().publish(new OrbeMessageTerrain(terrain));
			}
			// Peinture
			else {
				// Reset all flags
				getMap().getHexMap().setFlag(HexFlag.TOOL, false);
				// Starts the painting
				OrbeTaskHexPaint editHexPaint = new OrbeTaskHexPaint(getControler());
				// Paints
				HexPaintControler paintControler = new HexPaintControler(editHexPaint, hxp);
				HXGeom.paint(hxp, paintControler);
				// Refresh the view
				Double affectedZone = paintControler.getAffectedZone();
				if (affectedZone != null) {
					Shape shape = getControler().getView().getViewSettings().zoom(affectedZone);
					getControler().repaint(shape, true);
				}
				// OK
				getContext().addEdit(editHexPaint);
			}
		}
	}

	/**
	 * Collecte des points à peindre.
	 */
	protected class HexPaintControler implements HXPaintControler {

		private OrbeTaskHexPaint edit;

		private HexTerrain fillTerrain;

		private Rectangle2D.Double affectedZone = null;

		private HexTerrain startTerrain;

		public HexPaintControler(OrbeTaskHexPaint editHexPaint, HXPoint startPoint) {
			edit = editHexPaint;
			startTerrain = getMap().getHexMap().getHex(startPoint).getTerrain();
			fillTerrain = getToolSettings().getHexTerrain();
		}

		public boolean canFill(HXPoint hx) {
			if (getMap().isHexInBounds(hx)) {
				Hex hex = getMap().getHexMap().getHex(hx);
				return (!hex.getFlag(HexFlag.TOOL) && hex.getTerrain().getId() == startTerrain.getId());
			} else {
				return false;
			}
		}

		public void fill(HXPoint hx) {
			Hex hex = getMap().getHexMap().getHex(hx);
			if (!hex.getFlag(HexFlag.TOOL)) {
				edit.saveHex(hx, hex);
				hex.setTerrain(fillTerrain);
				hex.setSymbol(null);
				hex.setFlag(HexFlag.TOOL, true);

				Rectangle2D.Double hxBounds = hxg.getHexBounds(hx.i, hx.j);
				if (affectedZone == null) {
					affectedZone = hxBounds;
				} else {
					Rectangle2D.union(affectedZone, hxBounds, affectedZone);
				}
			}
		}

		public Rectangle2D.Double getAffectedZone() {
			return affectedZone;
		}

	}

	public String getId() {
		return ITool.HEX_PAINT;
	}

	/**
	 * Gestion du curseur.
	 * 
	 * @see orbe.gui.map.tool.AbstractTool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.isControlDown()) {
			setCursor(pipetteCursor);
		} else {
			setCursor(paintCursor);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			setCursor(pipetteCursor);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			setCursor(paintCursor);
		}
	}

}
