/*
 * Created on Sep 16, 2007
 */
package net.sf.doolin.oxml.source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * DOM source from a file
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class FileOXMLSource extends StreamOXMLSource {

	/**
	 * Constructs a source from an XML file.
	 * 
	 * @param f
	 *            XML file
	 * @throws IOException
	 *             If the file cannot be read
	 */
	public FileOXMLSource(File f) throws IOException {
		super(new BufferedInputStream(new FileInputStream(f)));
	}

	/**
	 * Closes the opened stream.
	 * 
	 * @see #closeStream()
	 * @see net.sf.doolin.oxml.source.StreamOXMLSource#close()
	 */
	@Override
	public void close() throws IOException {
		closeStream();
	}

}
