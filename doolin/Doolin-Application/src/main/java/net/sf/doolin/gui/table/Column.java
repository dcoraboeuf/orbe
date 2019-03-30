/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.table;

import java.util.HashMap;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import net.sf.doolin.util.Utils;
import net.sf.doolin.util.unit.ValueUnit;

import net.sf.doolin.gui.table.support.TableItemUpdatedMessage;

import org.apache.commons.lang.StringUtils;

public class Column {

	/**
	 * Predefined classes
	 */
	private static HashMap<String, Class> classes = new HashMap<String, Class>();
	static {
		classes.put("boolean", Boolean.class);
		classes.put("int", Integer.class);
		classes.put("string", String.class);
	}
	
	private String title;
	
	private String property;
	
	private String type;
	
	private Class columnClass;
	
	private ValueUnit width = null;
	
	private boolean resizeable = true;
	
	private boolean editable = false;
	
	private UpdatePolicy updatePolicy = UpdatePolicy.CELL;
	
	private TableCellRenderer renderer = new DefaultTableCellRenderer();
	
	private TableCellEditor editor = null;
	
	private Class<? extends TableItemUpdatedMessage> editionMessageType = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Class for the column
	 */
	public Class getColumnClass() {
		if (columnClass == null) {
			if (StringUtils.isBlank(type)) {
				return Object.class;
			} else {
				columnClass = classes.get(type);
				if (columnClass == null) {
					columnClass = Utils.forClass(type);
				}
			}
		}
		return columnClass;
	}

	public TableCellRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(TableCellRenderer renderer) {
		this.renderer = renderer;
	}

	public ValueUnit getWidth() {
		return width;
	}

	public void setWidth(ValueUnit width) {
		this.width = width;
	}

	public boolean isResizeable() {
		return resizeable;
	}

	public void setResizeable(boolean resizeable) {
		this.resizeable = resizeable;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public TableCellEditor getEditor() {
		return editor;
	}

	public void setEditor(TableCellEditor editor) {
		this.editor = editor;
	}

	public UpdatePolicy getUpdatePolicy() {
		return updatePolicy;
	}

	public void setUpdatePolicy(UpdatePolicy updatePolicy) {
		this.updatePolicy = updatePolicy;
	}

	public Class<? extends TableItemUpdatedMessage> getEditionMessageType() {
		return editionMessageType;
	}

	public void setEditionMessageType(Class<? extends TableItemUpdatedMessage> editionMessageType) {
		this.editionMessageType = editionMessageType;
	}

}
