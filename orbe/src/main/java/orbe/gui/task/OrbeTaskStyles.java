/*
 * Created on Nov 17, 2006
 */
package orbe.gui.task;

import orbe.gui.map.core.OrbeControler;
import orbe.model.OrbeMap;
import orbe.model.style.RepositoryStyle;
import orbe.model.style.Style;

/**
 * Edition des styles de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeTaskTextStyles.java,v 1.1 2006/11/17 10:56:36 guinnessman
 *          Exp $
 */
public abstract class OrbeTaskStyles<S extends Style> extends OrbeTask {

	private RepositoryStyle<S> editedRepository;

	public OrbeTaskStyles(OrbeControler orbeControler, RepositoryStyle<S> repository) {
		super(orbeControler);
		this.editedRepository = repository;
	}

	protected abstract RepositoryStyle<S> getOriginalRepository();

	protected abstract void onRemove(RepositoryStyle<S> originalList, S style);

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		final RepositoryStyle<S> originalList = getOriginalRepository();
		// Sauvegarde de la liste
		RepositoryStyle<S> savedOriginalList = originalList.createCopy();
		// Modifications & ajouts
		for (S editedStyle : editedRepository.getStyles()) {
			int editedStyleId = editedStyle.getId();
			// Existing style ?
			S originalStyle = originalList.getStyle(editedStyleId);
			// Edited style ?
			if (originalStyle != null) {
				originalStyle.copy(editedStyle);
			}
			// New terrain
			else {
				originalList.addStyle(editedStyle);
			}
		}
		// Suppressions
		for (S originalStyle : originalList.getStyles()) {
			final int originalStyleId = originalStyle.getId();
			if (editedRepository.getStyle(originalStyleId) == null) {
				onRemove(originalList, originalStyle);
				// Suppression du référentiel
				originalList.remove(originalStyle);
			}
		}
		// Les settings ont chang�
		OrbeMap map = getControler().getContext().getMap();
		map.setSettings(map.getSettings());
		// La liste �dit�e devient la liste originale
		editedRepository = savedOriginalList;
	}

}
