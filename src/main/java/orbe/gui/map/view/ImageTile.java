/*
 * Created on 9 nov. 06
 */
package orbe.gui.map.view;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Définition d'une section de dessin
 * 
 * @author Damien Coraboeuf
 * @version $Id: ImageTile.java,v 1.1 2006/11/09 20:16:12 guinnessman Exp $
 */
public class ImageTile {
	/**
	 * Construction d'une liste de sections
	 * 
	 * @return Liste des sections
	 * @param width
	 *            Largeur de la zone totale
	 * @param height
	 *            Hauteur de la zone totale
	 * @param size
	 *            Taille d'une section
	 */
	public static ImageTile[][] getTiles(int width, int height, int size) {
		// Nombre de sections en largeur
		int nwidth = (width + size - 1) / size;
		// Nombre de sections en hauteur
		int nheight = (height + size - 1) / size;
		// Nombre de sections
		ImageTile[][] tiles = new ImageTile[nheight][];
		// Parcours des lignes
		for (int j = 0; j < nheight; j++) {
			tiles[j] = new ImageTile[nwidth];
			// Parcours des colonnes
			for (int i = 0; i < nwidth; i++) {
				// Gauche
				int left = i * size;
				// Haut
				int top = j * size;
				// Droite
				int right = left + size;
				if (right > width)
					right = width;
				// Bas
				int bottom = top + size;
				if (bottom > height)
					bottom = height;
				// Création
				tiles[j][i] = new ImageTile(new Point(i, j), left, top, right, bottom);
			}
		}
		// Ok
		return tiles;
	}

	/**
	 * Affiche une description de la liste des sections sur la console
	 * 
	 * @param tiles
	 *            Liste des sections
	 * @param all
	 *            <ul>
	 *            <li><code>true</code> - pour toutes les infos
	 *            <li><code>false</code> - pour l'affichage d'une grille des
	 *            mises à jour
	 *            </ul>
	 */
	public static void list(ImageTile[][] tiles, boolean all) {
		if (all) {
			// Parcours des sections
			int nheight = tiles.length;
			for (int j = 0; j < nheight; j++) {
				int nwidth = tiles[j].length;
				for (int i = 0; i < nwidth; i++) {
					System.out.print("(" + j + "," + i + ") ");
					if (tiles[j][i].isToUpdate())
						System.out.print("invalid ");
					else
						System.out.print("ok      ");
					System.out.println(tiles[j][i].getZone());
				}
			}
			// Ok
			System.out.println();
		} else {
			// Parcours des sections
			int nheight = tiles.length;
			for (int j = 0; j < nheight; j++) {
				int nwidth = tiles[j].length;
				for (int i = 0; i < nwidth; i++) {
					if (i == 0)
						System.out.println();
					if (tiles[j][i].isToUpdate())
						System.out.print('X');
					else
						System.out.print('O');
				}
			}
			// Ok
			System.out.println();
		}
	}

	/**
	 * Identifiant. Position (X, Y)
	 */
	private Point id;

	/**
	 * La section a-t-elle besoin d'être mise à jour ?
	 */
	private boolean toUpdate = true;

	/**
	 * Zone de la section
	 */
	private Rectangle zone;

	/**
	 * Initialisation d'une section
	 */
	public ImageTile(Point id, int left, int top, int right, int bottom) {
		this.id = id;
		this.zone = new Rectangle(left, top, right - left, bottom - top);
	}

	/**
	 * Identifiant - Position (X, Y)
	 */
	public Point getId() {
		return id;
	}

	/**
	 * Zone de la section
	 */
	public Rectangle getZone() {
		return zone;
	}

	/**
	 * La section a-t-elle besoin d'être mise à jour ?
	 */
	public boolean isToUpdate() {
		return toUpdate;
	}

	/**
	 * La section a-t-elle besoin d'être mise à jour ?
	 */
	public void setToUpdate(boolean newToUpdate) {
		toUpdate = newToUpdate;
	}

	/**
	 * Returns a String that represents the value of this object.
	 * 
	 * @return a string representation of the receiver
	 */
	public String toString() {
		return "(" + id.x + "," + id.y + ")" + (toUpdate ? "toUpdate" : "ok");
	}
}