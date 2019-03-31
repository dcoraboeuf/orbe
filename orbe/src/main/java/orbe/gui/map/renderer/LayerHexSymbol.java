/*
 * Created on Oct 24, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import orbe.model.hex.Hex;
import orbe.model.style.HexSymbol;

public class LayerHexSymbol extends AbstractLayerHex {

	private SymbolRenderer symbolRenderer;

	public LayerHexSymbol() {
		symbolRenderer = SymbolRenderer.getInstance();
	}

	public Layer getId() {
		return Layer.HEX_SYMBOL;
	}

	@Override
	protected void drawHex(Graphics2D g, int i, int j, Hex hex, Shape shape) {
		HexSymbol symbol = hex.getFillSymbol();
		if (symbol != null) {
			Rectangle bounds = shape.getBounds();
			symbolRenderer.drawSymbol(g, bounds, symbol);
		}
	}

}
