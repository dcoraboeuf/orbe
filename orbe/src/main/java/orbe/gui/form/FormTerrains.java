/*
 * Created on Nov 13, 2006
 */
package orbe.gui.form;

import orbe.model.style.RepositoryHexTerrain;

/**
 * Edition d'une liste de terrains.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormTerrains.java,v 1.1 2006/11/13 15:59:23 guinnessman Exp $
 */
public class FormTerrains {

	/**
	 * Terrains gérés pour l'édition.
	 */
	private RepositoryHexTerrain terrains;

	/**
	 * création � partir d'une liste initiale (qui ne doit pas être modifi�e
	 * directement par l'�dition en cas d'annulation).
	 */
	public FormTerrains(RepositoryHexTerrain originalTerrains) {
		// création d'un duplicata
		terrains = new RepositoryHexTerrain(originalTerrains);
	}

	public RepositoryHexTerrain getTerrains() {
		return terrains;
	}

}
