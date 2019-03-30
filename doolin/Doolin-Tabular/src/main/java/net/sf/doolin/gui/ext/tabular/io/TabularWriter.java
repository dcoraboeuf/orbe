/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.io;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.doolin.gui.ext.tabular.TabularData;

/**
 * Defines the writer for some tabular data.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TabularWriter.java,v 1.1 2007/08/14 14:09:21 guinnessman Exp $
 */
public interface TabularWriter {

	/**
	 * Writes the given tabular data into the given output stream
	 * 
	 * @param output
	 *            Output stream to write to
	 * @param data
	 *            Data to write
	 * @throws IOException
	 *             Thrown if an error occurs during the writing.
	 */
	void write(OutputStream output, TabularData data) throws IOException;

}
