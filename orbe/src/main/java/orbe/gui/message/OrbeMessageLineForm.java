/*
 * Created on Nov 30, 2006
 */
package orbe.gui.message;

import orbe.model.element.line.LineForm;

/**
 * Changement du type de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageLineForm.java,v 1.1 2006/11/30 17:09:32 guinnessman Exp $
 */
public class OrbeMessageLineForm extends OrbeSimpleMessage<LineForm> {

	public OrbeMessageLineForm(LineForm value) {
		super(value);
	}

}
