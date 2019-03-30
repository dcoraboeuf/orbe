/*
 * Created on Sep 16, 2007
 */
package net.sf.doolin.oxml.source;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.doolin.oxml.OXMLSource;

/**
 * Utility ancestor for the setup of the parser.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractOXMLSource implements OXMLSource {

	/**
	 * Creates a document builder for the parsing.
	 * 
	 * @return Document builder
	 * @throws IOException
	 *             If the DOM builder cannnot be configured
	 */
	protected DocumentBuilder getBuilder() throws IOException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder;
		} catch (Exception ex) {
			IOException ioex = new IOException("Cannot get a suitable parser");
			ioex.initCause(ex);
			throw ioex;
		}
	}

}
