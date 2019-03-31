/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.Set;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.CursorFactory;
import orbe.gui.cursor.ICursors;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.task.OrbeTaskHexPaint;
import orbe.hex.HXBrushCollector;
import orbe.hex.HXGeom;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.hex.Hex;
import orbe.model.hex.HexFlag;
import orbe.model.hex.HexMap;

public abstract class AbstractToolHex extends AbstractToolDrag {

	private HXGraphics hxg;

	private HXPoint currentHex;

	private OrbeTaskHexPaint edit;

	private Cursor pipetteCursor;

	private Cursor defaultCursor;

	/**
	 * Default constructor.
	 */
	public AbstractToolHex() {
		CursorFactory cursorFactory = GUIUtils.getService(CursorFactory.class);
		pipetteCursor = cursorFactory.getCursor(ICursors.PIPETTE);
		defaultCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public void setControler(OrbeControler ctrl) {
		super.setControler(ctrl);
		hxg = new HXGraphics(getContext().getMap().getSettings().getScale());
	}

	@Override
	protected void onDragged(PointDecimal pointPX, MouseEvent e) {
		if (currentHex != null) {
			HXPoint hxp = hxg.locateHex(pointPX);
			if (!hxp.equals(currentHex)) {
				int hxDistance = HXGeom.distance(hxp, currentHex);
				if (hxDistance > 1) {
					HXBrushCollector collector = new HXBrushCollector(getHexBrushSize());
					HXGeom.line(currentHex, hxp, true, true, collector);
					paintHexs(collector.getLocs());
					currentHex = hxp;
				} else {
					currentHex = hxp;
					Set<HXPoint> locs = HXGeom.getHexAround(currentHex, getHexBrushSize());
					paintHexs(locs);
				}
			}
		}
	}

	@Override
	protected void onPressed(PointDecimal pointPX, MouseEvent e) {
		if (e.isControlDown()) {
			currentHex = null;
		} else {
			HXPoint hxp = hxg.locateHex(pointPX);
			if (getMap().isHexInBounds(hxp)) {
				// Initialisation
				currentHex = hxp;
				getMap().getHexMap().setFlag(HexFlag.TOOL, false);
				// création de la commande
				edit = new OrbeTaskHexPaint(getControler());
				// Premiers hexs
				Set<HXPoint> locs = HXGeom.getHexAround(currentHex, getHexBrushSize());
				paintHexs(locs);
			} else {
				currentHex = null;
			}
		}
	}

	protected int getHexBrushSize() {
		return getToolSettings().getHexBrushSize();
	}

	protected void paintHexs(Set<HXPoint> locs) {
		OrbeMap map = getMap();
		GeneralPath path = new GeneralPath();
		for (HXPoint hxp : locs) {
			if (map.isHexInBounds(hxp)) {
				onHex(hxp);
				Shape shape = hxg.getHexShape(hxp.i, hxp.j);
				path.append(shape, false);
			}
		}
		Shape shape = getViewSettings().zoom(path);
		getControler().repaint(shape, true);
	}

	@Override
	protected void onReleased(PointDecimal pointPX, MouseEvent e) {
		if (currentHex != null) {
			HXPoint hxp = hxg.locateHex(pointPX);
			onRelease(edit, hxp);
		} else if (e.isControlDown()) {
			HXPoint hxp = hxg.locateHex(pointPX);
			if (getMap().isHexInBounds(hxp)) {
				Hex hex = getMap().getHexMap().getHex(hxp);
				extract(hxp, hex);
			}
		}
	}

	/**
	 * Extracts information from the hex and publish it as a default.
	 */
	protected abstract void extract(HXPoint hxp, Hex hex);

	protected void onRelease(OrbeTaskHexPaint edit, HXPoint hxp) {
		getContext().addEdit(edit);
	}

	protected void onHex(HXPoint hxp) {
		HexMap hexMap = getMap().getHexMap();
		Hex hex = hexMap.getHex(hxp);
		boolean flag = hex.getFlag(HexFlag.TOOL);
		if (!flag) {
			edit.saveHex(hxp, hex);
			paintHex(hex);
			hex.setFlag(HexFlag.TOOL, true);
		}
	}

	protected abstract void paintHex(Hex hex);

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
			setCursor(defaultCursor);
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
			setCursor(defaultCursor);
		}
	}
}
