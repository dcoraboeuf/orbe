/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import orbe.model.style.HexTerrain;

public class CellRendererTerrain extends JPanel implements ListCellRenderer {

	public static final int CELL_WIDTH = 22;

	private PanelTerrain panelTerrain;

	private JLabel labelTerrain;

	/**
	 * Initialisation.
	 */
	public CellRendererTerrain() {
		setPreferredSize(new Dimension(120, CELL_WIDTH));
		setMinimumSize(new Dimension(60, CELL_WIDTH));

		setLayout(new BorderLayout());

		panelTerrain = new PanelTerrain();
		panelTerrain.setPreferredSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		panelTerrain.setMinimumSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		panelTerrain.setMaximumSize(new Dimension(CELL_WIDTH, CELL_WIDTH));
		add(panelTerrain, BorderLayout.LINE_START);

		labelTerrain = new JLabel(" ");
		labelTerrain.setOpaque(true);
		labelTerrain.setBackground(SystemColor.window);
		labelTerrain.setForeground(SystemColor.windowText);
		add(labelTerrain, BorderLayout.CENTER);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		HexTerrain terrain = (HexTerrain) value;
		// Label
		if (terrain != null) {
			labelTerrain.setText(" " + terrain.getName());
		} else {
			labelTerrain.setText(" ");
		}
		// Selection
		if (isSelected) {
			labelTerrain.setBackground(list.getSelectionBackground());
			labelTerrain.setForeground(list.getSelectionForeground());
		} else {
			labelTerrain.setBackground(list.getBackground());
			labelTerrain.setForeground(list.getForeground());
		}
		// Focus
		Border border = null;
		if (cellHasFocus) {
			if (isSelected) {
				border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
			}
			if (border == null) {
				border = UIManager.getBorder("List.focusCellHighlightBorder");
			}
		} else {
			border = new EmptyBorder(1, 1, 1, 1);
		}
		setBorder(border);
		// Icon
		panelTerrain.setTerrain(terrain);
		// Ok
		return this;
	}

}
