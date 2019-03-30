/*
 * Created on 18 aoutt 07
 */
package net.sf.doolin.gui.form;

import net.sf.doolin.gui.field.Field;

/**
 * Defines an object that contains fields.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldContainer.java,v 1.1 2007/08/18 17:19:48 guinnessman Exp $
 */
public interface FieldContainer {


	/**
	 * Returns the field by its name
	 * 
	 * @param fieldName
	 *            Field name
	 * @return Field
	 */
	Field getFieldByName(String fieldName);

}
