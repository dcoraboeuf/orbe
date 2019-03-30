/*
 * Created on Sep 20, 2007
 */
package net.sf.doolin.gui.field.helper;

/**
 * This interface is used to convert a model value to/from a field data.
 * 
 * @param <F>
 *            Field data type
 * @param <M>
 *            Model data type
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface FieldDataAdapter<F, M> {

	M convertFieldToModel(F fieldData);

	F convertModelToField(M modelValue);

}
