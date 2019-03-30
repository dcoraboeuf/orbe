/*
 * Created on Nov 30, 2006
 */
package orbe.model.element.line;

import java.util.Collections;
import java.util.List;

import orbe.model.element.OrbeElementList;

/**
 * Liste de lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeLineList.java,v 1.4 2006/12/05 16:09:00 guinnessman Exp $
 */
public class OrbeLineList extends OrbeElementList<OrbeLine> {

	/**
	 * Classement par type
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onChange(List<OrbeLine> list) {
		Collections.sort(list);
	}

}
