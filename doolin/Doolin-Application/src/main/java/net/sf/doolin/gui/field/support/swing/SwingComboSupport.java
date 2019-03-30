/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import org.apache.commons.lang.ObjectUtils;

import net.sf.doolin.gui.field.FieldCombo;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.helper.FieldDataAdapter;
import net.sf.doolin.gui.field.support.ComboSupport;
import net.sf.doolin.gui.swing.LabelCellRenderer;

/**
 * Support for the combo field, based on a combo box.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingComboSupport.java,v 1.2 2007/08/07 16:47:05 guinnessman
 *          Exp $
 */
public class SwingComboSupport extends AbstractSwingInfoFieldSupport<FieldCombo, JComboBox> implements ComboSupport {

	private JComboBox cbo;

	@Override
	protected JComboBox createComponent() {
		cbo = new JComboBox();
		// TODO Editable
		// List of items
		List items = getField().getItemProvider().getItems();
		for (Object item : items) {
			cbo.addItem(item);
		}
		// Custom renderer
		if (getField().getCellRenderer() != null) {
			cbo.setRenderer(getField().getCellRenderer());
		}
		// Label renderer
		else {
			cbo.setRenderer(new LabelCellRenderer(getField().getLabelProvider()));
		}
		// Ok
		return cbo;
	}

	public Object getSelectedItem() {
		return cbo.getSelectedItem();
	}

	public void setSelectedItem(Object fieldData, FieldDataAdapter adapter) {
		cbo.setSelectedIndex(-1);
		int count = cbo.getItemCount();
		for (int i = 0; i < count; i++) {
			Object item = cbo.getItemAt(i);
			@SuppressWarnings("unchecked")
			Object modelItem = adapter.convertFieldToModel(item);
			if (ObjectUtils.equals(fieldData, modelItem)) {
				cbo.setSelectedIndex(i);
			}
		}
	}
	
	public void bindEditEvent(final EventAction eventAction) {
		cbo.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
