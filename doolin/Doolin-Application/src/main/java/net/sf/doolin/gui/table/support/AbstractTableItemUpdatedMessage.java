/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.gui.table.support;

import net.sf.doolin.gui.table.Column;

/**
 * @param <T>
 *            Type of the edited item
 * @author Damien Coraboeuf
 * @version $Id: AbstractTableItemUpdatedMessage.java,v 1.1 2007/08/14 11:48:55 guinnessman Exp $
 */
public abstract class AbstractTableItemUpdatedMessage<T> implements TableItemUpdatedMessage {
	
	private Column column;
	
	private T item;

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public T getItem() {
		return item;
	}

	@SuppressWarnings("unchecked")
	public void setItem(Object item) {
		this.item = (T) item;
	}

}
