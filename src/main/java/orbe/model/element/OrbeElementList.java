/*
 * Created on Nov 16, 2006
 */
package orbe.model.element;

import java.util.LinkedList;
import java.util.List;

/**
 * Liste d'élements gérés par une carte.
 * 
 * @param <E>
 *            Type d'élément
 * @author Damien Coraboeuf
 * @version $Id: OrbeElementList.java,v 1.5 2006/12/06 16:25:58 guinnessman Exp $
 */
public abstract class OrbeElementList<E extends OrbeElement> {

	/**
	 * Liste des éléments.
	 */
	private LinkedList<E> elements = new LinkedList<E>();

	public List<E> getElements() {
		return elements;
	}

	public void add(E e) {
		elements.add(e);
		onChange(elements);
	}

	/**
	 * Méthode appelée en cas de changement de la liste. Cela permet de faire un
	 * tri dans les classes descendantes.F
	 */
	protected void onChange(List<E> list) {
	}

	public void remove(E e) {
		elements.remove(e);
		onChange(elements);
	}

	public void restore(E e, int index) {
		elements.remove(e);
		elements.add(index, e);
	}

	public int moveUp(E e) {
		if (e != elements.getLast()) {
			int index = elements.indexOf(e);
			elements.remove(index);
			elements.add(index + 1, e);
			onChange(elements);
			return index;
		} else {
			return -1;
		}
	}

	public int moveLast(E e) {
		if (e != elements.getLast()) {
			int index = elements.indexOf(e);
			elements.remove(e);
			elements.addLast(e);
			onChange(elements);
			return index;
		} else {
			return -1;
		}
	}

	public int moveFirst(E e) {
		if (e != elements.getFirst()) {
			int index = elements.indexOf(e);
			elements.remove(e);
			elements.addFirst(e);
			onChange(elements);
			return index;
		} else {
			return -1;
		}
	}

	public int moveDown(E e) {
		if (e != elements.getFirst()) {
			int index = elements.indexOf(e);
			elements.remove(index);
			elements.add(index - 1, e);
			onChange(elements);
			return index;
		} else {
			return -1;
		}
	}

	public boolean contains(E e) {
		return elements.contains(e);
	}
}
