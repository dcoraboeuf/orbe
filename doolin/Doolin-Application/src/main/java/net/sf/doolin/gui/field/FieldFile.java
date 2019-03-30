/*
 * Created on Aug 6, 2007
 */
package net.sf.doolin.gui.field;

import java.io.File;

import net.sf.doolin.gui.field.support.FileSupport;

/**
 * This field allows the input of a file or a directory.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldFile.java,v 1.2 2007/08/15 09:05:20 guinnessman Exp $
 */
public class FieldFile extends AbstractSupportField<FileSupport> {

	private boolean directory = false;

	private Mode mode;

	private String filter;

	private String filterDescription;

	@Override
	protected FileSupport createSupport() {
		return createSupport(FileSupport.class);
	}

	/**
	 * @see FileSupport#getFile()
	 * @see net.sf.doolin.gui.field.Field#getFieldData(java.lang.Object)
	 */
	public Object getFieldData(Object formData) {
		return getSupport().getFile();
	}

	/**
	 * @see FileSupport#setFile(File)
	 * @see net.sf.doolin.gui.field.Field#setFieldData(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setFieldData(Object formData, Object fieldData) {
		File file = (File) fieldData;
		getSupport().setFile(file);
	}

	/**
	 * Browsing mode.
	 * 
	 * @author Damien Coraboeuf
	 * @version $Id: FieldFile.java,v 1.2 2007/08/15 09:05:20 guinnessman Exp $
	 */
	public static enum Mode {
		/**
		 * Saving mode
		 */
		SAVE,
		/**
		 * Opening mode
		 */
		OPEN;
	}

	/**
	 * Gets the directory mode.
	 * 
	 * @return <code>true</code> if directories are browsed instead of files.
	 */
	public boolean isDirectory() {
		return directory;
	}

	/**
	 * Sets the directory mode.
	 * 
	 * @param directory
	 *            <code>true</code> if directories are browsed instead of
	 *            files
	 */
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * Returns the browsing mode.
	 * 
	 * @return Browsing mode.
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Sets the browsing mode
	 * 
	 * @param mode
	 *            Browsing mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * Gets the filter. It is an expression string where $formData stands for
	 * the form data.
	 * 
	 * @return Filter expression.
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the filter. It is an expression string where $formData stands for
	 * the form data.
	 * 
	 * @param filter
	 *            Filter expression.
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * Gets the filter description. It is an expression string where $formData
	 * stands for the form data.
	 * 
	 * @return Filter descriptionexpression.
	 */
	public String getFilterDescription() {
		return filterDescription;
	}

	/**
	 * Sets the filter description. It is an expression string where $formData
	 * stands for the form data.
	 * 
	 * @param filterDescription
	 *            Filter descriptionexpression.
	 */
	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}

}
