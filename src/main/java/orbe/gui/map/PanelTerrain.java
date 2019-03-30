/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JPanel;

import orbe.gui.map.renderer.SymbolRenderer;
import orbe.model.style.HexSymbol;
import orbe.model.style.HexTerrain;

public class PanelTerrain extends JPanel {

	private HexTerrain terrain;

	public PanelTerrain() {
		setBackground(SystemColor.desktop);
	}

	public HexTerrain getTerrain() {
		return terrain;
	}

	public void setTerrain(HexTerrain terrain) {
		this.terrain = terrain;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		if (terrain != null) {
			// Couleur
			Rectangle zone = g.getClipBounds();
			g.setColor(terrain.getColor());
			g.fill(zone);
			// Symbole
			HexSymbol symbol = terrain.getSymbol();
			if (symbol != null) {
				SymbolRenderer.getInstance().drawSymbol(g, getBounds(), symbol);
			}
		}
	}

}
