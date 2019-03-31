/*
 * Created on Nov 7, 2006
 */
package orbe.gui.map.options;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import orbe.gui.map.core.OrbeControler;

public abstract class PanelOptions extends JPanel {

	public PanelOptions(LayoutManager layoutManager) {
		super(layoutManager);
	}

	public abstract void setup(OrbeControler controler);

}
