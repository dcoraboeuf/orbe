/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field;

import javax.swing.ListCellRenderer;

import org.apache.commons.lang.Validate;

import net.sf.doolin.gui.annotation.Configurable;
import net.sf.doolin.gui.field.helper.DefaultFieldDataAdapter;
import net.sf.doolin.gui.field.helper.DefaultLabelInfoProvider;
import net.sf.doolin.gui.field.helper.FieldDataAdapter;
import net.sf.doolin.gui.field.helper.ItemProvider;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.field.support.ComboSupport;

public class FieldCombo extends AbstractSupportField<ComboSupport> {

	private ItemProvider itemProvider;

	private LabelInfoProvider labelProvider = new DefaultLabelInfoProvider();

	private ListCellRenderer cellRenderer;

	private FieldDataAdapter fieldDataAdapter = new DefaultFieldDataAdapter();

	@Override
	protected void init() {
		super.init();
		Validate.notNull(itemProvider, "itemProvider must be set for FieldCombo.");
	}

	@Override
	protected ComboSupport createSupport() {
		return createSupport(ComboSupport.class);
	}

	public Object getFieldData(Object formData) {
		Object selectedItem = getSupport().getSelectedItem();
		@SuppressWarnings("unchecked")
		Object modelValue = fieldDataAdapter.convertFieldToModel(selectedItem);
		return modelValue;
	}

	public void setFieldData(Object formData, Object fieldData) {
		getSupport().setSelectedItem(fieldData, fieldDataAdapter);
	}

	public ItemProvider getItemProvider() {
		return itemProvider;
	}

	public void setItemProvider(ItemProvider itemProvider) {
		this.itemProvider = itemProvider;
	}

	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	public ListCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	/**
	 * Sets an optional cell renderer for the combo. If set, it overrides the
	 * <code>labelProvider</code> property.
	 * 
	 * @param cellRenderer
	 *            Cell renderer
	 */
	public void setCellRenderer(ListCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}

	public FieldDataAdapter getFieldDataAdapter() {
		return fieldDataAdapter;
	}

	/**
	 * Sets an optional field data adapter for conversion between the field data
	 * (as present in the combo box) and the model data (as defined as
	 * input/output of the field)
	 * 
	 * @param fieldDataAdapter
	 *            Field data adapter
	 */
	@Configurable(mandatory = false, description = "Field adata adapter", defaultsTo = "net.sf.doolin.gui.field.helper.DefaultFieldDataAdapter")
	public void setFieldDataAdapter(FieldDataAdapter fieldDataAdapter) {
		this.fieldDataAdapter = fieldDataAdapter;
	}

}
