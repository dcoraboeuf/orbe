/*
 * Created on Nov 23, 2006
 */
package orbe.gui.message;

import orbe.model.style.TextStyle;

/**
 * Notification du changement du style de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageTextStyle.java,v 1.1 2006/11/23 08:53:00 guinnessman Exp $
 */
public class OrbeMessageTextStyle extends OrbeSimpleMessage<TextStyle> {

	public OrbeMessageTextStyle(TextStyle value) {
		super(value);
	}

}
