/*
 * Created on 3 oct. 06
 */
package orbe.model.io;

import java.io.File;

import orbe.model.OrbeMap;

/**
 * Reads a map for a given version.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeReader.java,v 1.2 2006/11/09 11:22:38 guinnessman Exp $
 */
public interface OrbeReader<T extends OrbeMap> {

	T read (Class<T> mapClass, File file);
	
}
