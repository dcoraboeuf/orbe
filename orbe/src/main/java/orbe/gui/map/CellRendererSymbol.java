/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import orbe.model.style.DefaultHexTerrain;
import orbe.model.style.HexSymbol;

public class CellRendererSymbol extends JPanel implements ListCellRenderer {

	public static final int CELL_WIDTH = 22;

	private PanelTerrain panelTerrain;

	private JLabel labelSymbol;

	private DefaultHexTerrain terrain;

	/**
	 * Initialisation.
	 */
	public CellRendererSymbol() {
		setPreferredSize(new Dimension(120, CELL_WIDTH));
		setMinimumSize(new Dimension(60, CELL_WIDTH));

		setLayout(new BorderLayout());

		panelTerrain = new PanelTerrain();
		panelTerrain.setPreferredSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		panelTerrain.setMinimumSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		panelTerrain.setMaximumSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		add(panelTerrain, BorderLayout.LINE_START);

		labelSymbol = new JLabel(" ");
		labelSymbol.setOpaque(true);
		labelSymbol.setBackground(SystemColor.window);
		labelSymbol.setForeground(SystemColor.windowText);
		add(labelSymbol, BorderLayout.CENTER);

		terrain = new DefaultHexTerrain();
		terrain.setColor(Color.LIGHT_GRAY);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		HexSymbol symbol = (HexSymbol) value;
		// Label
		if (symbol != null) {
			labelSymbol.setText(" " + symbol.getName());
		} else {
			labelSymbol.setText(" ");
		}
		// Icon
		terrain.setSymbol(symbol);
		panelTerrain.setTerrain(terrain);
		// Ok
		return this;
	}

}
