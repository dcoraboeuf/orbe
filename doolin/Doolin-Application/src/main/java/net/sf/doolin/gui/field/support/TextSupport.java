/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support;

import net.sf.doolin.gui.field.FieldText;

public interface TextSupport extends FieldSupport<FieldText> {

	void setText(String text);

	String getText();

}
