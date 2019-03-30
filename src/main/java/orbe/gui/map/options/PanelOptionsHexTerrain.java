/*
 * Created on Nov 6, 2006
 */
package orbe.gui.map.options;

public class PanelOptionsHexTerrain extends PanelCompoundOptions {

	public PanelOptionsHexTerrain() {
		addOption(new OptionCboHexTerrain());
		addOption(new OptionCboBrushSize());
	}
}
