/*
 * Created on 3 oct. 06
 */
package orbe.model.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.gui.ext.template.Template;
import net.sf.doolin.gui.ext.template.TemplateEngine;
import net.sf.doolin.gui.ext.template.TemplateUtils;
import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.ValueHandler;
import net.sf.doolin.util.xml.DigesterUtils;
import orbe.model.IErrors;
import orbe.model.OrbeMap;
import orbe.model.io.v02.OrbeReader02;
import orbe.model.io.v20.OrbeReader20;

/**
 * Lecture et écriture de fichiers Orbe.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeIO.java,v 1.7 2006/12/01 14:09:55 guinnessman Exp $
 */
public class OrbeIO {

	/**
	 * Unique instance
	 */
	private static OrbeIO instance = null;

	/**
	 * Current version
	 */
	public static final String VERSION = "v20";

	/**
	 * Get the instance
	 */
	public static OrbeIO getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OrbeIO createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OrbeIO temp = new OrbeIO();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Registered versions
	 */
	private HashMap<String, OrbeReader> readers = new HashMap<String, OrbeReader>();

	/**
	 * Initialization
	 */
	private OrbeIO() {
		readers.put("0.2", new OrbeReader02());
		readers.put("2.0", new OrbeReader20());
	}

	/**
	 * Saves the map.
	 */
	public void save(OrbeMap map, File file) {
		try {
			// Gets the template path
			String templatePath = "/orbe/model/io/" + VERSION + "/OrbeTemplate.xml";
			// Gets the template
			Template template = TemplateUtils.getTemplateManager().getTemplateEngine(TemplateEngine.VELOCITY).getTemplate(templatePath);
			// Template parameters
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("orbe", map);
			context.put("orbeIOFormat", new OrbeIOFormat());
			// Writer to the file
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			try {
				// Renders the template
				template.generate(writer, context);
			} finally {
				writer.close();
			}

		} catch (Exception ex) {
			throw new CodeException(IErrors.IO_CANNOT_SAVE, ex, file, ex);
		}
	}

	/**
	 * Reads a map
	 */
	public <T extends OrbeMap> T read(Class<T> mapClass, File file) {
		// Simple read to get the version
		String version = readVersion(file);
		// Get the reader
		@SuppressWarnings("unchecked")
		OrbeReader<T> reader = readers.get(version);
		if (reader == null) {
			throw new CodeException(IErrors.IO_UNKNOWN_VERSION, version);
		}
		// Reads from the file
		return reader.read(mapClass, file);
	}

	protected String readVersion(File file) {
		try {
			DigesterUtils digesterUtils = DigesterUtils.createNonValidatingDigester();
			digesterUtils.ruleProperty("orbe/version", "setValue");
			digesterUtils.ruleProperty("OrbeMap/version", "setValue");

			ValueHandler<String> value = new ValueHandler<String>();
			digesterUtils.parse(file, value);
			String version = value.getValue();

			return version;
		} catch (Exception ex) {
			throw new CodeException(IErrors.IO_CANNOT_READ_VERSION, ex, file, ex);
		}
	}

}
