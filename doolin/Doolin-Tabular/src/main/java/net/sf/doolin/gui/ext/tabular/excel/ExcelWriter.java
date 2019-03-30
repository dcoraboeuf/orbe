/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.doolin.gui.ext.tabular.TabularData;
import net.sf.doolin.gui.ext.tabular.io.TabularWriter;
import net.sf.doolin.gui.ext.tabular.model.TabularColumn;
import net.sf.doolin.gui.ext.tabular.model.TabularModel;

/**
 * Writes to an Excel workbook.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ExcelWriter.java,v 1.1 2007/08/14 14:35:27 guinnessman Exp $
 */
public class ExcelWriter implements TabularWriter {

	public void write(OutputStream output, TabularData data) throws IOException {
		TabularModel model = data.getModel();
		// Creates the workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Creates a sheet
		HSSFSheet sheet = workbook.createSheet();

		// Freezes the first row
		sheet.createFreezePane(0, 1, 0, 1);

		// 1 - Titles
		HSSFRow row = sheet.createRow(0);
		short cellIndex = 0;
		for (TabularColumn column : model.getColumns()) {
			String title = column.getTitle();
			// Creates the cell
			HSSFCell cell = row.createCell(cellIndex++);
			// Value
			cell.setCellValue(title);
		}

		// N - Rows
		int rowCount = data.getRowCount();
		int columnCount = data.getColumnCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			// New row
			row = sheet.createRow(rowIndex + 1);
			// For each column
			cellIndex = 0;
			for (int col = 0; col < columnCount; col++) {
				// Data for this cell
				Object value = data.getValue(rowIndex, col);
				// Creates the cell
				HSSFCell cell = row.createCell(cellIndex++);

				// Sets the value
				if (value != null) {
					if (value instanceof Boolean) {
						cell.setCellValue((Boolean) value);
					} else if (value instanceof Double) {
						cell.setCellValue((Double) value);
					} else if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof Date) {
						cell.setCellValue((Date) value);
						// Set the format
						HSSFCellStyle style = workbook.createCellStyle();
						style.setDataFormat((short) 0xF);
						cell.setCellStyle(style);
					} else {
						String string = ObjectUtils.toString(value, "");
						cell.setCellValue(string);
					}
				}

			}
		}

		// Writes the workbook
		workbook.write(output);
	}

}
