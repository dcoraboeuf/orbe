/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.field;

import java.util.Collection;
import java.util.List;

import net.sf.doolin.gui.field.helper.DefaultLabelInfoProvider;
import net.sf.doolin.gui.field.helper.ItemProvider;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.field.support.MultipleSelectionSupport;

/**
 * This field allows the selection of items among a fixed list of items.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldMultipleSelection.java,v 1.2 2007/08/15 09:05:20 guinnessman Exp $
 */
public class FieldMultipleSelection extends AbstractSupportField<MultipleSelectionSupport> {

	private ItemProvider itemProvider;

	private LabelInfoProvider labelProvider = new DefaultLabelInfoProvider();

	private int visibleRows = 8;

	@Override
	protected MultipleSelectionSupport createSupport() {
		return createSupport(MultipleSelectionSupport.class);
	}

	/**
	 * @see MultipleSelectionSupport#getSelection()
	 * @see net.sf.doolin.gui.field.Field#getFieldData(java.lang.Object)
	 */
	public Object getFieldData(Object formData) {
		return getSupport().getSelection();
	}

	/**
	 * @see MultipleSelectionSupport#setSelection(Collection)
	 * @see net.sf.doolin.gui.field.Field#setFieldData(java.lang.Object,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public void setFieldData(Object formData, Object fieldData) {
		List collection = (List)fieldData;
		getSupport().setSelection(collection);
	}

	/**
	 * Returns the associated item provider
	 * 
	 * @return Item provider
	 */
	public ItemProvider getItemProvider() {
		return itemProvider;
	}

	/**
	 * Sets the associated item provider
	 * 
	 * @param itemProvider
	 *            Item provider
	 */
	public void setItemProvider(ItemProvider itemProvider) {
		this.itemProvider = itemProvider;
	}

	/**
	 * Returns the label provider
	 * 
	 * @return Label provider
	 */
	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	/**
	 * Sets the label provider
	 * 
	 * @param labelProvider
	 *            Label provider
	 */
	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	/**
	 * Returns the number of visible rows
	 * 
	 * @return Number of visible rows
	 */
	public int getVisibleRows() {
		return visibleRows;
	}

	/**
	 * Sets the number of visible rows
	 * 
	 * @param visibleRows
	 *            Number of visible rows
	 */
	public void setVisibleRows(int visibleRows) {
		this.visibleRows = visibleRows;
	}

}
