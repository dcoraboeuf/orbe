/*
 * Created on 21 janv. 2006
 */
package net.sf.doolin.util.xml;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.StringCodes;

/**
 * Utility methods for XML parsing.
 * 
 * @author Damien Coraboeuf
 * @version $Id: XMLUtils.java,v 1.1 2007/08/14 11:48:23 guinnessman Exp $
 */
public class XMLUtils {

	/**
	 * Creates a SAX parser that validates against a schema.
	 * 
	 * @param schemaURL
	 *            URL to the XSD file
	 * @return A validating SAX parser
	 * @throws CodeException
	 *             If the parser cannot be configured
	 */
	public static SAXParser createSAXValidatingParser(URL schemaURL) {
		try {
			// Creates a SAX parser factory
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			// Get access to the schema
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaURL);
			parserFactory.setSchema(schema);
			parserFactory.setNamespaceAware(true);
			// Creates a SAX parser
			SAXParser parser = parserFactory.newSAXParser();
			return parser;
		} catch (Exception e) {
			throw new CodeException(StringCodes.STRING_XML_CANNOT_CONFIGURE_PARSER, e, e);
		}
	}

	/**
	 * Creates a simple non validating SAX parser
	 * 
	 * @return A validating SAX parser
	 * @throws CodeException
	 *             If the parser cannot be configured
	 */
	public static SAXParser createSAXNonValidatingParser() {
		try {
			// Creates a SAX parser factory
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(false);
			// Creates a SAX parser
			SAXParser parser = parserFactory.newSAXParser();
			return parser;
		} catch (Exception e) {
			throw new CodeException(StringCodes.STRING_XML_CANNOT_CONFIGURE_PARSER, e);
		}
	}
}
