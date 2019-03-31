/*
 * Created on Oct 26, 2006
 */
package orbe.gui.task;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.gui.service.GUIStrings;

import orbe.gui.map.core.OrbeControler;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.model.OrbeMap;
import orbe.model.hex.Hex;

public class OrbeTaskHexPaint extends OrbeTask {

	private boolean start;

	private HashMap<HXPoint, Hex> savedHexs;

	public OrbeTaskHexPaint(OrbeControler c) {
		super(c);
		start = true;
		savedHexs = new HashMap<HXPoint, Hex>();
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		HashMap<HXPoint, Hex> restoredHexs = new HashMap<HXPoint, Hex>();
		Rectangle2D.Double affectedZone = null;
		for (Map.Entry<HXPoint, Hex> entry : savedHexs.entrySet()) {
			HXPoint hxp = entry.getKey();
			Hex savedHex = entry.getValue();
			Hex currentHex = map.getHexMap().getHex(hxp);
			Hex restoredHex = new Hex();
			restoredHex.copy(currentHex);
			currentHex.copy(savedHex);
			restoredHexs.put(hxp, restoredHex);

			Rectangle2D.Double hxBounds = hxg.getHexBounds(hxp.i, hxp.j);
			if (affectedZone == null) {
				affectedZone = hxBounds;
			} else {
				Rectangle2D.union(affectedZone, hxBounds, affectedZone);
			}
		}

		savedHexs = restoredHexs;
		if (affectedZone != null) {
			Shape shape = getControler().getView().getViewSettings().zoom(affectedZone);
			getControler().repaint(shape, true);
		}
	}

	public void saveHex(HXPoint hxp, Hex hex) {
		if (start) {
			setDirty();
			start = false;
		}
		Hex savedHex = new Hex();
		savedHex.copy(hex);
		savedHexs.put(hxp, savedHex);
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskHexPaint.name");
	}

}
