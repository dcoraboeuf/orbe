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
	 * Création à partir d'une liste initiale (qui ne doit pas être modifiée
	 * directement par l'édition en cas d'annulation).
	 */
	public FormTerrains(RepositoryHexTerrain originalTerrains) {
		// Création d'un duplicata
		terrains = new RepositoryHexTerrain(originalTerrains);
	}

	public RepositoryHexTerrain getTerrains() {
		return terrains;
	}

}
