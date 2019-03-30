/*
 * Created on Oct 26, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import orbe.model.style.HexSymbol;
import orbe.model.style.RepositoryHexSymbol;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SymbolRenderer {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(SymbolRenderer.class);

	/**
	 * Unique instance
	 */
	private static SymbolRenderer instance = null;

	/**
	 * Get the instance
	 */
	public static SymbolRenderer getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized SymbolRenderer createInstance() {
		if (instance != null) {
			return instance;
		} else {
			SymbolRenderer temp = new SymbolRenderer();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Icons indexed by ID
	 */
	private HashMap<Integer, ImageIcon> icons;

	/**
	 * Initialization
	 */
	private SymbolRenderer() {
		log.info("Loading of icons for symbols");
		icons = new HashMap<Integer, ImageIcon>();
		List<HexSymbol> symbols = RepositoryHexSymbol.getInstance().getSymbols();
		for (HexSymbol symbol : symbols) {
			String imageId = symbol.getImageId();
			log.debug("Loading image " + imageId + " for symbol " + symbol.getName() + " (" + symbol.getId() + ")");
			String imagePath = "/orbe/resources/symbol/" + imageId;
			URL imageURL = getClass().getResource(imagePath);
			if (imageURL == null) {
				log.error("Cannot find image resource: " + imagePath);
			} else {
				ImageIcon imageIcon = new ImageIcon(imageURL);
				icons.put(symbol.getId(), imageIcon);
			}
		}
	}

	public void drawSymbol(Graphics2D g, Rectangle bounds, HexSymbol symbol) {
		ImageIcon icon = getSymbolIcon(bounds, symbol);
		int iWidth = icon.getIconWidth();
		int iHeight = icon.getIconHeight();
		if (iWidth >= bounds.width || iHeight >= bounds.height) {
			// Does not draw the symbol
		} else {
			int x = bounds.x + (bounds.width - iWidth) / 2;
			int y = bounds.y + (bounds.height - iHeight) / 2;
			g.drawImage(icon.getImage(), x, y, null);
		}
	}

	public ImageIcon getSymbolIcon(Rectangle bounds, HexSymbol symbol) {
		int id = symbol.getId();
		return icons.get(id);
	}
}
