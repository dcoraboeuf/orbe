/*
 * Created on Aug 14, 2007
 */
package net.sf.doolin.gui.ext.tabular.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.ext.tabular.TabularData;
import net.sf.doolin.gui.ext.tabular.io.TabularWriter;
import net.sf.doolin.gui.ext.tabular.model.TabularColumn;
import net.sf.doolin.gui.ext.tabular.model.TabularModel;

/**
 * Writer for CSV format.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CSVWriter.java,v 1.1 2007/08/14 14:09:22 guinnessman Exp $
 */
public class CSVWriter implements TabularWriter {

	private String encoding = "UTF-8";

	private String separator = ",";

	private static final Map<Class, CSVAdapter> defaultAdapters = new HashMap<Class, CSVAdapter>();
	static {
		defaultAdapters.put(boolean.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(byte.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(short.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(int.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(long.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(float.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(double.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(BigDecimal.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Boolean.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Byte.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Short.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Integer.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Long.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Float.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(Double.class, new PrimitiveCSVAdapter());
		defaultAdapters.put(String.class, new StringCSVAdapter());
	}

	private CSVAdapter defaultAdapter = new StringCSVAdapter();

	private Map<Class, CSVAdapter> adapters = new HashMap<Class, CSVAdapter>();

	public void write(OutputStream output, TabularData data) throws IOException {
		// Creates a writer wrapper, using specified encoding
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output, encoding)));
		try {

			// 1 - Columns
			List<String> line = new ArrayList<String>();
			TabularModel model = data.getModel();
			for (TabularColumn column : model.getColumns()) {
				String name = column.getTitle();
				line.add(name);
			}
			writeLine(writer, line);

			// N - Rows
			int rowCount = data.getRowCount();
			int columnCount = data.getColumnCount();
			for (int row = 0; row < rowCount; row++) {
				// New line
				line = new ArrayList<String>();
				// For each column
				for (int col = 0; col < columnCount; col++) {
					// Data for this cell
					Object value = data.getValue(row, col);
					// Adapt for CSV
					String csvValue = getCSVValue(value);
					// Appends it to the line
					line.add(csvValue);
				}
				// Writes the line
				writeLine(writer, line);
			}

		} finally {
			// Ok
			writer.flush();
		}
	}

	/**
	 * Adapts a value for CSV
	 * 
	 * @param value
	 *            Value to adapt
	 * @return Adapted value
	 */
	protected String getCSVValue(Object value) {
		if (value == null) {
			return "";
		} else {
			// Adapter
			CSVAdapter adapter;
			// Current adapter
			Class type = value.getClass();
			adapter = adapters.get(type);
			// Default adapters
			if (adapter == null) {
				adapter = defaultAdapters.get(type);
			}
			// Default adapters
			if (adapter == null) {
				adapter = defaultAdapter;
			}
			// Adapt
			String string = adapter.getCSVValue(value);
			// Ok
			return string;
		}
	}

	/**
	 * Writes a line in the CSV destination.
	 * 
	 * @param writer
	 *            Writer to write to
	 * @param line
	 *            Line to write
	 */
	protected void writeLine(PrintWriter writer, List<String> line) {
		String wholeLine = StringUtils.join(line.iterator(), separator);
		writer.println(wholeLine);
	}

	/**
	 * Returns the <code>encoding</code> property. It defaults to UTF-8.
	 * 
	 * @return <code>encoding</code> property.
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the <code>encoding</code> property. It defaults to UTF-8.
	 * 
	 * @param encoding
	 *            <code>encoding</code> property.
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Returns the <code>separator</code> property. It defaults to ",".
	 * 
	 * @return <code>separator</code> property.
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * Sets the <code>separator</code> property. It defaults to ",".
	 * 
	 * @param separator
	 *            <code>separator</code> property.
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * Returns the <code>defaultAdapter</code> property.
	 * 
	 * @return <code>defaultAdapter</code> property.
	 */
	public CSVAdapter getDefaultAdapter() {
		return defaultAdapter;
	}

	/**
	 * Sets the <code>defaultAdapter</code> property.
	 * 
	 * @param defaultAdapter
	 *            <code>defaultAdapter</code> property.
	 */
	public void setDefaultAdapter(CSVAdapter defaultAdapter) {
		this.defaultAdapter = defaultAdapter;
	}

}
