/*
 * Created on Oct 4, 2006
 */
package orbe.model;

import java.awt.Color;
import java.io.Serializable;

public class Grid implements Serializable {

	private static final long serialVersionUID = 1;

	private boolean transparent = true;

	private Color color = Color.GRAY;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

}
