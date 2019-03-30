/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.csv;

/**
 * This interface allows the adaptation of a model value to a CSV string.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CSVAdapter.java,v 1.1 2007/08/14 14:09:22 guinnessman Exp $
 */
public interface CSVAdapter {

	/**
	 * Adapts a model value to a CSV string
	 * 
	 * @param value
	 *            Value to adapt
	 * @return Adapted value
	 */
	String getCSVValue(Object value);

}
