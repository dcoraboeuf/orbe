/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml.source;

import java.io.IOException;
import java.net.URL;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Builds a source from an URL.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class URLOXMLSource extends AbstractOXMLSource {

	private URL url;

	/**
	 * Constructor
	 * 
	 * @param url
	 *            URL to the document
	 */
	public URLOXMLSource(URL url) {
		this.url = url;
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.oxml.OXMLSource#close()
	 */
	public void close() throws IOException {
	}

	/**
	 * Parses the URL.
	 * 
	 * @see net.sf.doolin.oxml.OXMLSource#getSource()
	 */
	public Document getSource() throws IOException {
		try {
			return getBuilder().parse(url.toString());
		} catch (SAXException e) {
			IOException ioex = new IOException("Cannot parse the URL " + url);
			ioex.initCause(e);
			throw ioex;
		}
	}

}
