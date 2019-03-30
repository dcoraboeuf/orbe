/*
 * Created on Nov 17, 2006
 */
package orbe.gui.form;

import orbe.model.style.RepositoryStyle;
import orbe.model.style.Style;

/**
 * Edition de styles.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormStyles.java,v 1.1 2006/11/17 10:56:39 guinnessman Exp $
 */
public class FormStyles<S extends Style> {

	/**
	 * Liste gérée
	 */
	private RepositoryStyle<S> repository;

	/**
	 * Création à partir d'une liste qui n'est pas utilisée par l'édition.
	 */
	public FormStyles(RepositoryStyle<S> list) {
		repository = list.createCopy();
	}

	/**
	 * @return Liste des styles édités.
	 */
	public RepositoryStyle<S> getRepository() {
		return repository;
	}

}
