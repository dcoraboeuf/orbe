/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.config;

import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import net.sf.doolin.oxml.action.SequenceOXMLAction;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Configuration based upon an XML file that complies with the
 * <code>http://doolin-guif.sourceforge.net/schema/oxml</code> schema.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class XMLOXMLConfig extends AbstractOXMLConfig {

	/**
	 * Default constructor. The created instance needs to be initialized.
	 * 
	 * @see #setResource(URL)
	 */
	public XMLOXMLConfig() {
	}

	/**
	 * Constructor from a resource path.
	 * 
	 * @param url
	 *            Path to the resource
	 * @throws IOException
	 *             If a parsing error occurs
	 * @see #setResource(URL)
	 */
	public XMLOXMLConfig(URL url) throws IOException {
		setResource(url);
	}

	/**
	 * Sets the configuration from a resource path.
	 * 
	 * @param url
	 *            Resource path to an XML file that complies with the
	 *            <code>http://doolin-guif.sourceforge.net/schema/oxml</code>
	 *            schema.
	 * @throws IOException
	 *             If the configuration cannot be parsed
	 */
	protected void setResource(URL url) throws IOException {
		if (url == null) {
			throw new IOException("Cannot parse XML document from null URL");
		}
		// Parses the configuration
		Document dom = parse(url);
		// Root
		Element eOXML = dom.getDocumentElement();
		// Creates a sequence
		SequenceOXMLAction sequence = new SequenceOXMLAction();
		// Parses it
		sequence.parse(eOXML);
		// Ok
		setRootAction(sequence);
	}

	/**
	 * Internal parsing method
	 * 
	 * @param url
	 *            URL to parse
	 * @return Parsed document
	 * @throws IOException
	 *             If a parsing error occurs
	 */
	protected Document parse(URL url) throws IOException {
		try {
			return getBuilder().parse(url.toString());
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			IOException ioex = new IOException("Cannot parse " + url);
			ioex.initCause(ex);
			throw ioex;
		}
	}

	/**
	 * Creates a document builder for the parsing.
	 * 
	 * @return Document builder
	 * @throws IOException
	 *             If the DOM builder cannot be configured
	 */
	protected DocumentBuilder getBuilder() throws IOException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(getClass().getResource("/net/sf/doolin/oxml/OXML.xsd"));
			factory.setSchema(schema);
			// factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder;
		} catch (Exception ex) {
			IOException ioex = new IOException("Cannot get a suitable parser");
			ioex.initCause(ex);
			throw ioex;
		}
	}

}
