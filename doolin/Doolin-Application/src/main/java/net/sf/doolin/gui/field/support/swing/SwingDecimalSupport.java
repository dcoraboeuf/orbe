/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import net.sf.doolin.gui.field.FieldDecimal;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.DecimalSupport;

import org.apache.commons.lang.ObjectUtils;

/**
 * Support implementation for the <code>{@link DecimalSupport}</code> field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class SwingDecimalSupport extends AbstractSwingInfoFieldSupport<FieldDecimal, JFormattedTextField> implements DecimalSupport {

	private JFormattedTextField field;

	@Override
	protected JFormattedTextField createComponent() {
		// Format to be used
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		int fractionDigits = getField().getFractionDigits();
		int integerDigits = getField().getIntegerDigits();
		// Fraction digits
		decimalFormat.setMaximumFractionDigits(fractionDigits);
		if (getField().isFractionShowAll()) {
			decimalFormat.setMinimumFractionDigits(fractionDigits);
		}
		// Integer digits
		decimalFormat.setMaximumIntegerDigits(integerDigits);
		// Field
		field = new JFormattedTextField(decimalFormat);
		field.setHorizontalAlignment(SwingConstants.TRAILING);
		field.setColumns(integerDigits + 1 + fractionDigits);
		// Ok
		return field;
	}

	public BigDecimal getValue() {
		Object value = field.getValue();
		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		} else {
			String text = ObjectUtils.toString(value, "0");
			return new BigDecimal(text);
		}
	}

	public void setValue(BigDecimal value) {
		field.setValue(value);
	}
	
	public void bindEditEvent(final EventAction eventAction) {
		field.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
