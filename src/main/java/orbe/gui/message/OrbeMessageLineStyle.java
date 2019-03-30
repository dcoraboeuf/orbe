/*
 * Created on Nov 23, 2006
 */
package orbe.gui.message;

import orbe.model.style.StyleLine;

/**
 * Notification du changement du style de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageLineStyle.java,v 1.1 2006/11/30 17:09:32 guinnessman Exp $
 */
public class OrbeMessageLineStyle extends OrbeSimpleMessage<StyleLine> {

	public OrbeMessageLineStyle(StyleLine value) {
		super(value);
	}

}
