/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import orbe.model.OrbeMap;

/**
 * Settings have changed.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageContextSettings.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public class OrbeMessageContextSettings extends OrbeSimpleMessage<OrbeMap> {

	public OrbeMessageContextSettings(OrbeMap value) {
		super(value);
	}

}
