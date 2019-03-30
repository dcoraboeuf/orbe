/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Iterator;

import net.sf.doolin.bus.Bus;
import orbe.gui.jmx.OrbeManager;
import orbe.gui.map.core.OrbeRenderer;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.message.OrbeViewRefreshEnd;
import orbe.gui.message.OrbeViewRefreshStart;
import orbe.model.OrbeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TiledOrbeView extends BufferedOrbeView {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(TiledOrbeView.class);

	/**
	 * Default size of tiles
	 */
	public static final int TILE_SIZE = 128;

	/**
	 * Liste des sections
	 */
	private ImageTile[][] tiles;

	/**
	 * Initialisation
	 */
	public TiledOrbeView(OrbeMap map, ViewSettings settings) {
		super(map, settings);
		// Taille totale
		Dimension size = getDisplaySize(map);
		// Liste des tiles
		tiles = ImageTile.getTiles(size.width, size.height, TILE_SIZE);
	}

	/**
	 * Calcul des sections concernées par le dessin
	 * 
	 * @return Liste des sections
	 * @param clip
	 *            Zone de clip
	 */
	protected Iterator<ImageTile> getTiles(Rectangle2D.Double clip) {
		// Liste
		ArrayList<ImageTile> liste = new ArrayList<ImageTile>();
		// Parcours des sections
		int nheight = tiles.length;
		for (int j = 0; j < nheight; j++) {
			int nwidth = tiles[j].length;
			for (int i = 0; i < nwidth; i++) {
				// Test
				if (clip.intersects(tiles[j][i].getZone())) {
					liste.add(tiles[j][i]);
				}
			}
		}
		return liste.iterator();
	}

	@Override
	public void paint(OrbeMap map, Graphics2D g, Double zone) {
		// Liste des zones à dessiner
		Iterator<ImageTile> tiles = getTiles(zone);
		// Une MAJ a t-elle lieu ?
		boolean maj = false;
		try {
			// Parcours des zones
			while (tiles.hasNext()) {
				// Tile concernée
				ImageTile tile = tiles.next();
				// Tile à rafraîchir ?
				if (tile.isToUpdate()) {
					if (!maj) {
						maj = true;
						// Publish (for status bar) the fact that the image is
						// refreshed.
						startRefresh();
					}
					refreshTile(map, tile, zone);
				}
			}
			// Ok
			super.paint(map, g, zone);
		} finally {
			// Fin
			if (maj) {
				// Publish (for status bar) the fact that the image is finished to be refreshed.
				finishRefresh();
			}
		}
	}

	protected void finishRefresh() {
		Bus.get().publish(new OrbeViewRefreshEnd());
	}

	protected void startRefresh() {
		Bus.get().publish(new OrbeViewRefreshStart());
	}

	public void refresh(OrbeMap map, Double zone) {
		boolean debug = OrbeManager.getInstance().getLayeredOrbeRendererDebug();
		if (debug) {
			log.debug("REFRESH " + zone);
		}
		long start = System.currentTimeMillis();
		try {
			// Publish the refresh state
			startRefresh();
			// Liste des zones à dessiner
			Iterator tiles = getTiles(zone);
			// Parcours des zones
			while (tiles.hasNext()) {
				// Tile concernée
				ImageTile tile = (ImageTile) tiles.next();
				// Tile à rafraîchir ?
				refreshTile(map, tile, zone);
			}
		} finally {
			// Publish the refresh state
			finishRefresh();
			if (debug) {
				long end = System.currentTimeMillis();
				log.debug("Refresh has taken " + (end - start) + " ms");
			}
		}
	}

	/**
	 * Redessin d'une section
	 * 
	 * @param tile
	 *            Section à redessiner
	 * @param zone
	 *            Zone de clip
	 */
	protected void refreshTile(OrbeMap map, ImageTile tile, Rectangle2D.Double zone) {

		if (isDebugEnabled()) {
			log.debug("Refreshing tile " + tile);
		}

		// Graphics environment
		Graphics2D g = (Graphics2D) getImage().getGraphics();
		Shape old = g.getClip();
		try {
			// Zone to render
			/*
			 * If the tile is considered as updated, only the intersection of
			 * the tile and the required zone is considered. If the tile must be
			 * updated, the full tile must be drawn.
			 */
			Rectangle2D.Double clip = new Rectangle2D.Double();
			if (tile.isToUpdate()) {
				Rectangle2D.intersect(tile.getZone(), tile.getZone(), clip);
			} else {
				Rectangle2D.intersect(zone, tile.getZone(), clip);
			}
			g.setClip(clip);
			// Render for the tile
			getRenderer().paint(map, getViewSettings(), g, clip);
			// Debug
			if (isDebugEnabled()) {
				Rectangle r = tile.getZone();
				g.setPaintMode();
				g.setStroke(new BasicStroke());
				g.setColor(Color.red);
				g.drawLine(r.x, r.y, r.x + r.width, r.y);
				g.drawLine(r.x, r.y, r.x, r.y + r.height);
				// Texte
				g.setFont(new Font("Serif", Font.BOLD, 12));
				Point id = tile.getId();
				g.drawString(id.x + "," + id.y, r.x + 5, r.y + 18);
			}
			// Fin debug
			if (tile.isToUpdate()) {
				tile.setToUpdate(false);
			}
		} finally {
			g.setClip(old);
		}
	}

	protected boolean isDebugEnabled() {
		return log.isDebugEnabled() && OrbeManager.getInstance().getTiledOrbeViewDebug();
	}

	/**
	 * Intersection d'une section et de zones de rafraîchissement
	 */
	protected static class RefreshTile {
		/**
		 * Section
		 */
		private ImageTile tile;

		/**
		 * Liste des zones à rafraîchir
		 */
		private ArrayList<Rectangle2D.Double> zones = new ArrayList<Rectangle2D.Double>();

		/**
		 * Initialisation
		 */
		protected RefreshTile(ImageTile tile) {
			this.tile = tile;
		}

		/**
		 * Accès à la section
		 */
		protected ImageTile getTile() {
			return tile;
		}

		/**
		 * Ajout d'une zone
		 */
		protected void addZone(Rectangle2D.Double zone) {
			zones.add(zone);
		}

		/**
		 * Liste des zones : liste d'instances de la classe
		 * <code>java.awt.Rectangle</code>
		 */
		protected Iterator<Rectangle2D.Double> getZones() {
			return zones.iterator();
		}
	}

	@Override
	public void setRenderer(OrbeRenderer renderer) {
		super.setRenderer(renderer);
		// Invalidation des tiles
		for (ImageTile[] t : tiles) {
			for (ImageTile tile : t) {
				tile.setToUpdate(true);
			}
		}
	}

}
