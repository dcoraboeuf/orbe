/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.field;

import java.util.HashMap;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.field.support.StaticSupport;
import net.sf.doolin.gui.service.ExpressionEvaluator;

public class FieldStatic extends AbstractSupportField<StaticSupport> {

	public void setFieldData(Object formData, Object fieldData) {
		// Evaluation context
		HashMap<String, Object> context = new HashMap<String, Object>();
		context.put("formData", formData);
		context.put("fieldData", fieldData);
		// Evaluates text
		ExpressionEvaluator expressionEvaluator = Application.getApplication().getService(ExpressionEvaluator.class);
		String value = expressionEvaluator.evaluate(text, context);
		// Set the text
		getSupport().setText(value);
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	protected void init() {
		super.init();
		setReadOnly(true);
	}
	
	@Override
	protected StaticSupport createSupport() {
		return createSupport(StaticSupport.class);
	}

	public Object getFieldData(Object formData) {
		return null;
	}

}
