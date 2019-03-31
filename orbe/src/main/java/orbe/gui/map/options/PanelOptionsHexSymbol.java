/*
 * Created on Nov 6, 2006
 */
package orbe.gui.map.options;

public class PanelOptionsHexSymbol extends PanelCompoundOptions {

	public PanelOptionsHexSymbol() {
		addOption(new OptionCboHexSymbol());
		addOption(new OptionCboBrushSize());
	}

}
