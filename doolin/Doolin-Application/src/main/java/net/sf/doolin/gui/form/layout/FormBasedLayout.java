/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.form.layout;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import net.sf.doolin.gui.field.Field;

/**
 * Layout based on the <a
 * href="http://www.jgoodies.com/freeware/forms/index.html"
 * target="_blank">JGoodies form layout</a>.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormBasedLayout.java,v 1.3 2007/08/15 09:05:27 guinnessman Exp $
 */
public class FormBasedLayout extends AbstractLayout {

	private JPanel panel;

	private String columnSpecs;

	private String rowSpecs;

	private List<Field> fieldList;

	private String columnGroups;

	private String rowGroups;

	/**
	 * @see net.sf.doolin.gui.form.layout.Layout#init()
	 */
	public void init() {
		// Creates the layout
		FormLayout formLayout = new FormLayout(columnSpecs, rowSpecs);

		// Set columns and rows groups
		if (StringUtils.isNotBlank(columnGroups)) {
			int[][] groups = parseGroups(columnGroups);
			formLayout.setColumnGroups(groups);
		}
		if (StringUtils.isNotBlank(rowGroups)) {
			int[][] groups = parseGroups(rowGroups);
			formLayout.setRowGroups(groups);
		}

		// Creates the container
		panel = new JPanel(formLayout);

		// For each field
		for (Field field : fieldList) {
			// Init the field
			getForm().register(field);
			// Get the constraints
			CellConstraints cc = getCellConstraints(field);
			// Get the field component and adds it to the layout
			panel.add(field.getComponent(), cc);
		}
	}

	/**
	 * Parses a row or column group definition.
	 * 
	 * @param group
	 *            Group definition
	 * @return Group
	 */
	protected int[][] parseGroups(String group) {
		if (StringUtils.isBlank(group)) {
			return new int[0][];
		} else {
			String[] groups = StringUtils.split(group, "|");
			int[][] result = new int[groups.length][];
			for (int i = 0; i < groups.length; i++) {
				String groupTokens = groups[i];
				String[] groupTokenArray = StringUtils.split(groupTokens, ",");
				int[] groupArray = new int[groupTokenArray.length];
				for (int j = 0; j < groupTokenArray.length; j++) {
					String groupToken = groupTokenArray[j];
					int groupIndex = Integer.parseInt(groupToken);
					groupArray[j] = groupIndex;
				}
				result[i] = groupArray;
			}
			return result;
		}
	}

	/**
	 * Gets the cell constraints associated to a field.
	 * 
	 * @param field
	 *            Field to get fhe constraints from
	 * @return Cell constraints for the field
	 * @see Field#getConstaint()
	 */
	protected CellConstraints getCellConstraints(Field field) {
		// Gets the field constraint
		String value = (String) field.getConstraint();
		if (StringUtils.isBlank(value)) {
			throw new RuntimeException("Cell constraints must not be blank for field " + field.getDisplayName());
		}
		// Parses the constraints
		return new CellConstraints(value);
	}

	/**
	 * @see net.sf.doolin.gui.form.layout.Layout#getComponent()
	 */
	public JComponent getComponent() {
		return panel;
	}

	/**
	 * Returns the columnSpecs
	 * 
	 * @return String
	 */
	public String getColumnSpecs() {
		return columnSpecs;
	}

	/**
	 * Sets the columnSpecs
	 * 
	 * @param columnSpecs
	 *            String
	 */
	public void setColumnSpecs(String columnSpecs) {
		this.columnSpecs = columnSpecs;
	}

	/**
	 * Returns the rowSpecs
	 * 
	 * @return String
	 */
	public String getRowSpecs() {
		return rowSpecs;
	}

	/**
	 * Sets the rowSpecs
	 * 
	 * @param rowSpecs
	 *            String
	 */
	public void setRowSpecs(String rowSpecs) {
		this.rowSpecs = rowSpecs;
	}

	/**
	 * Returns the List of fields
	 * 
	 * @return List of fields
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}

	/**
	 * Sets the List of fields
	 * 
	 * @param fieldList
	 *            List of fields
	 */
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	/**
	 * Returns the <code>columnGroups</code> property.
	 * 
	 * @return <code>columnGroups</code> property.
	 */
	public String getColumnGroups() {
		return columnGroups;
	}

	/**
	 * Sets the <code>columnGroups</code> property.
	 * 
	 * @param columnGroups
	 *            <code>columnGroups</code> property.
	 */
	public void setColumnGroups(String columnGroups) {
		this.columnGroups = columnGroups;
	}

	/**
	 * Returns the <code>rowGroups</code> property.
	 * 
	 * @return <code>rowGroups</code> property.
	 */
	public String getRowGroups() {
		return rowGroups;
	}

	/**
	 * Sets the <code>rowGroups</code> property.
	 * 
	 * @param rowGroups
	 *            <code>rowGroups</code> property.
	 */
	public void setRowGroups(String rowGroups) {
		this.rowGroups = rowGroups;
	}

}
