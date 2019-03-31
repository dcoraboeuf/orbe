/*
 * Created on Sep 16, 2007
 */
package orbe.model.io.v20;

import java.io.IOException;

import net.sf.doolin.oxml.config.XMLOXMLConfig;

/**
 * OXML configuration.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class OrbeOXMLConfig extends XMLOXMLConfig {
	
	public OrbeOXMLConfig() throws IOException {
		setResource(getClass().getResource("OXML.v20.xml"));
	}

}
