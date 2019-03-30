/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import orbe.gui.context.OrbeContext;

/**
 * Undoable edits have been changed.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageContextEdit.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public class OrbeMessageContextEdit extends OrbeSimpleMessage<OrbeContext> {

	public OrbeMessageContextEdit(OrbeContext value) {
		super(value);
	}

}
