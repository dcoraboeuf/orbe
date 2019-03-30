/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.core.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Factory bean for a list. This class extends an initial list by inserting new
 * elements at a given place.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ExtensibleListFactory.java,v 1.1 2007/07/31 15:33:06 guinnessman Exp $
 */
public class ExtensibleListFactory<T> implements DataFactory<List<T>> {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(ExtensibleListFactory.class);

	private Placeholder placeholder = Placeholder.LAST;

	private List<T> initialList = new ArrayList<T>();

	private DataFactory<List<T>> listFactory;

	public List<T> createData() {
		// Resulting list
		ArrayList<T> result = new ArrayList<T>();
		// Adds the initial list
		if (initialList != null) {
			result.addAll(initialList);
		}
		// Get the new elements
		List<T> newElements = listFactory.createData();
		if (newElements != null) {
			// Inserts them at the good location
			switch (placeholder) {
			case FIRST:
				result.addAll(0, newElements);
				break;
			case NULL:
				int index = result.indexOf(null);
				if (index < 0) {
					log.warn("No null element has been found in the initial list.");
					result.addAll(newElements);
				} else {
					result.remove(index);
					result.addAll(index, newElements);
				}
				break;
			default:
				result.addAll(newElements);
				break;
			}
		}
		// Ok
		return result;
	}

	public List<T> getInitialList() {
		return initialList;
	}

	public void setInitialList(List<T> initialList) {
		this.initialList = initialList;
	}

	public Placeholder getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(Placeholder placeholder) {
		this.placeholder = placeholder;
	}

	public DataFactory<List<T>> getListFactory() {
		return listFactory;
	}

	public void setListFactory(DataFactory<List<T>> listFactory) {
		this.listFactory = listFactory;
	}

}
