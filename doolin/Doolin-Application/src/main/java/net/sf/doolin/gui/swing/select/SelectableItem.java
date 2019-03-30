/*
 * Created on 9 avr. 2005
 */
package net.sf.doolin.gui.swing.select;

import org.apache.commons.lang.ObjectUtils;

/**
 * Item that can be selected
 * 
 * @author Damien Coraboeuf
 * @version $Id: SelectableItem.java,v 1.1 2007/08/07 16:47:08 guinnessman Exp $
 * @param <T>
 *            Type of item
 */
public class SelectableItem<T> {
	/**
	 * Item
	 */
	private T item;

	/**
	 * State
	 */
	private boolean selected;

	/**
	 * Constructor
	 * 
	 * @param t
	 *            Associated item
	 */
	public SelectableItem(T t) {
		item = t;
	}

	/**
	 * @return Returns the item.
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            The selected to set.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ObjectUtils.toString(item, "");
	}

}
