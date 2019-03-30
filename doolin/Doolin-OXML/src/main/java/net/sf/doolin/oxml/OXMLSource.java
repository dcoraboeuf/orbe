/*
 * Created on Sep 16, 2007
 */
package net.sf.doolin.oxml;

import java.io.IOException;

import org.w3c.dom.Document;

/**
 * Defines the source for a DOM.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public interface OXMLSource {

	/**
	 * Closes the resources for this source.
	 * 
	 * @throws IOException
	 *             If the source cannot be closed
	 * 
	 */
	void close() throws IOException;

	/**
	 * Gets the XML source as a DOM
	 * 
	 * @return DOM
	 * @throws IOException
	 *             If the DOM cannot be read
	 */
	Document getSource() throws IOException;

}
