/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

public enum Layer {
	BACKGROUND, HEX_COLOR, HEX_SYMBOL, HEX_ID(false), GRID, LINE, TEXT;

	/**
	 * Default visibility
	 */
	private boolean defaultVisible = false;

	/**
	 * Visible by default
	 */
	private Layer() {
		defaultVisible = true;
	}

	/**
	 * Configirable visibility
	 */
	private Layer(boolean visible) {
		defaultVisible = visible;
	}

	/**
	 * @return Default visibility
	 */
	public boolean isDefaultVisible() {
		return defaultVisible;
	}
}
