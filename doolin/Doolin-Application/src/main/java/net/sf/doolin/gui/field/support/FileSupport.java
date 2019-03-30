/*
 * Created on Aug 6, 2007
 */
package net.sf.doolin.gui.field.support;

import java.io.File;

import net.sf.doolin.gui.field.FieldFile;

/**
 * This interface defines the support class for a file field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FileSupport.java,v 1.1 2007/08/07 16:47:10 guinnessman Exp $
 */
public interface FileSupport extends FieldSupport<FieldFile> {

	/**
	 * Sets the current selected file
	 * 
	 * @param f
	 *            File to select or <code>null</code> if an empty selection is
	 *            wished.
	 */
	void setFile(File f);

	/**
	 * Gets the current selected file
	 * 
	 * @return Selected file or <code>null</code> if none.
	 */
	File getFile();

}
