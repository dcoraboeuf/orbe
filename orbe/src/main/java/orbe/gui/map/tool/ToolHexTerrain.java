/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map.tool;

import net.sf.doolin.bus.Bus;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.map.options.PanelOptionsHexTerrain;
import orbe.gui.message.OrbeMessageTerrain;
import orbe.hex.HXPoint;
import orbe.model.hex.Hex;
import orbe.model.style.HexTerrain;

public class ToolHexTerrain extends AbstractToolHex {

	private PanelOptionsHexTerrain panelOptions;

	@Override
	public PanelOptions getPanelOptions() {
		if (panelOptions == null) {
			panelOptions = new PanelOptionsHexTerrain();
		}
		return panelOptions;
	}

	/**
	 * Default constructor.
	 */
	public ToolHexTerrain() {
	}

	public String getId() {
		return ITool.HEX_TERRAIN;
	}

	@Override
	protected void paintHex(Hex hex) {
		HexTerrain terrain = getToolSettings().getHexTerrain();
		hex.setSymbol(null);
		hex.setTerrain(terrain);
	}

	@Override
	protected void extract(HXPoint hxp, Hex hex) {
		HexTerrain terrain = hex.getTerrain();
		Bus.get().publish(new OrbeMessageTerrain(terrain));
	}

}
