/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml;

import java.io.IOException;

import net.sf.doolin.oxml.action.OXMLAction;

import org.w3c.dom.Document;

/**
 * OXML reader.
 * 
 * @param <T>
 *            Expected type of the root object
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class OXMLReader<T> {

	private OXMLConfig config;

	/**
	 * Initialization of the reader.
	 * 
	 * @param cfg
	 *            Configuration to use
	 */
	public OXMLReader(OXMLConfig cfg) {
		config = cfg;
	}

	/**
	 * Reads an XML source and returns the root object.
	 * 
	 * @param source
	 *            XML source
	 * @return Root object
	 * @throws IOException
	 *             If the source cannot be read.
	 */
	public T read(OXMLSource source) throws IOException {
		try {
			// Gets the DOM source
			Document dom = source.getSource();
			// Get the root action of the configuration
			OXMLAction action = config.getRootAction();
			// Creates the OXMLContext
			OXMLContext<T> context = new OXMLContext<T>(dom);
			// Executes the action
			action.process(context);
			// Ok
			return context.getTargetRoot();
		} finally {
			// Closes the source
			source.close();
		}
	}

}
