/*
 * Created on Oct 24, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import orbe.model.hex.Hex;

/**
 * Dessin du no de l'hex (utilisé en debug).
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayerHexID.java,v 1.1 2006/11/08 13:34:59 guinnessman Exp $
 */
public class LayerHexID extends AbstractLayerHex {

	private Font font;

	public LayerHexID() {
		font = new Font("SansSerif", Font.PLAIN, 12);
	}

	public Layer getId() {
		return Layer.HEX_ID;
	}

	@Override
	protected void drawHex(Graphics2D g, int i, int j, Hex hex, Shape shape) {
		Rectangle bounds = shape.getBounds();
		String text = i + "," + j;
		
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		
		Rectangle2D box = fm.getStringBounds(text, g);
		double x = bounds.x + (bounds.width - box.getWidth()) / 2;
		double y = bounds.y + (bounds.height + box.getHeight()) / 2;

		g.setColor(Color.RED);
		g.drawString(text, (float) x, (float) y);
	}

}
