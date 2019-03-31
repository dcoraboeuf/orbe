/*
 * Created on Nov 9, 2006
 */
package orbe.gui.map.view;

import java.awt.image.BufferedImage;

/**
 * in-memory image.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeBufferedImage.java,v 1.1 2006/11/09 15:17:16 guinnessman Exp $
 */
public class OrbeBufferedImage extends BufferedImage {

	/**
	 * Constructeur.
	 * 
	 * @param width
	 *            Largeur
	 * @param height
	 *            Hauteur
	 */
	public OrbeBufferedImage(int width, int height) {
		super(width, height, BufferedImage.TYPE_3BYTE_BGR);
	}

}
