/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import net.sf.doolin.util.Utils;

import org.apache.commons.lang.StringUtils;

/**
 * Liste de styles.
 * 
 * @author Damien Coraboeuf
 * @version $Id: RepositoryStyle.java,v 1.5 2006/12/01 14:09:54 guinnessman Exp $
 */
public abstract class RepositoryStyle<S extends Style> {

	public static final int DEFAUL_STYLE_ID = 1;

	/**
	 * List of styles
	 */
	private HashMap<Integer, S> styles = new HashMap<Integer, S>();

	/**
	 * Actual class of the repository.
	 */
	private Class<? extends RepositoryStyle> actualClass;

	/**
	 * Liste vide
	 */
	public RepositoryStyle() {
		actualClass = getClass();
	}

	/**
	 * Ajout d'un style à la liste.
	 * 
	 * @param style
	 *            Style à ajouter.
	 */
	public void addStyle(S style) {
		styles.put(style.getId(), style);
	}

	/**
	 * Accès à un style.
	 * 
	 * @param id
	 *            ID du style
	 * @return Style correspondant ou <code>null</code> si non trouvé.
	 */
	public S getStyle(int id) {
		return styles.get(id);
	}

	/**
	 * Liste de tous les styles, triés par nom.
	 */
	public List<S> getStyles() {
		ArrayList<S> list = new ArrayList<S>(styles.values());
		Collections.sort(list, new Comparator<S>() {

			public int compare(S arg0, S arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}

		});
		return list;
	}

	/**
	 * Création d'une copie en profondeur de la liste des styles.
	 * 
	 * @return Liste dupliquée, ses éléments sont également de nouvelles
	 *         instances.
	 */
	public RepositoryStyle<S> createCopy() {
		@SuppressWarnings("unchecked")
		RepositoryStyle<S> list = Utils.newInstance(actualClass);
		for (S style : styles.values()) {
			@SuppressWarnings("unchecked")
			S newStyle = (S) style.createCopy();
			list.addStyle(newStyle);
		}
		return list;
	}

	/**
	 * Creates a default style and adds it to the repository.
	 */
	public S create() {
		S style = createDefault();
		int id = nextID();
		style.setId(id);
		addStyle(style);
		return style;
	}

	protected int nextID() {
		int max = 0;
		for (S style : styles.values()) {
			int id = style.getId();
			max = Math.max(max, id);
		}
		return max + 1;
	}

	/**
	 * Creates a default style without attching it to the repository.
	 */
	protected abstract S createDefault();

	/**
	 * Removes a style
	 */
	public void remove(S style) {
		styles.remove(style.getId());
	}

	/**
	 * Looks for a style using its name
	 */
	public S getStyleByName(String name) {
		for (S style : styles.values()) {
			if (StringUtils.equals(name, style.getName())) {
				return style;
			}
		}
		return null;
	}

}
