/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.field.helper;

import net.sf.doolin.gui.core.support.GUIUtils;

public class ExpressionLabelInfoProvider implements LabelInfoProvider {

	private String expression;

	private String variableName;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public LabelInfo getLabelIcon(Object item) {
		String text = GUIUtils.evaluate(expression, variableName, item);
		return new LabelInfo(text, null);
	}

}
