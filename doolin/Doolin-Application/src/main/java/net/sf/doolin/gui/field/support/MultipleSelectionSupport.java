/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.field.support;

import java.util.List;

import net.sf.doolin.gui.field.FieldMultipleSelection;

/**
 * Support for the <code>{@link FieldMultipleSelection}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: MultipleSelectionSupport.java,v 1.1 2007/08/07 16:47:10 guinnessman Exp $
 * @param <T>
 *            Type of item
 */
public interface MultipleSelectionSupport<T> extends FieldSupport<FieldMultipleSelection> {

	/**
	 * Returns the selection of items.
	 * 
	 * @return Collection of items
	 */
	List<T> getSelection();

	/**
	 * Sets the selection of items
	 * 
	 * @param collection
	 *            Collection of items
	 */
	void setSelection(List<T> collection);

	/**
	 * Sets the list of available items
	 * 
	 * @param items
	 *            List of items
	 */
	void setItems(List<T> items);

}
