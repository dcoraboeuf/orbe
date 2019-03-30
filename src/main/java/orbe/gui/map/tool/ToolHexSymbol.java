/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map.tool;

import net.sf.doolin.bus.Bus;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.map.options.PanelOptionsHexSymbol;
import orbe.gui.message.OrbeMessageSymbol;
import orbe.hex.HXPoint;
import orbe.model.hex.Hex;
import orbe.model.style.HexSymbol;

public class ToolHexSymbol extends AbstractToolHex {

	private PanelOptionsHexSymbol panelOptions;

	/**
	 * Default constructor.
	 */
	public ToolHexSymbol() {
	}

	public String getId() {
		return ITool.HEX_SYMBOL;
	}

	@Override
	public PanelOptions getPanelOptions() {
		if (panelOptions == null) {
			panelOptions = new PanelOptionsHexSymbol();
		}
		return panelOptions;
	}

	@Override
	protected void paintHex(Hex hex) {
		HexSymbol symbol = getToolSettings().getHexSymbol();
		hex.setSymbol(symbol);
	}

	@Override
	protected void extract(HXPoint hxp, Hex hex) {
		HexSymbol symbol = hex.getFillSymbol(); /* Symbole réellement utilisé */
		if (symbol != null) {
			Bus.get().publish(new OrbeMessageSymbol(symbol));
		}
	}

}
