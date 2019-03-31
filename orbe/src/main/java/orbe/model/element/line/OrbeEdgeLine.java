/*
 * Created on 4 dec. 06
 */
package orbe.model.element.line;

import java.util.List;

import orbe.hex.HXCorner;

/**
 * Ligne de contour d'hexs.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeEdgeLine.java,v 1.2 2006/12/04 18:15:51 guinnessman Exp $
 */
public class OrbeEdgeLine extends OrbeLine<HXCorner> {

	@Override
	public LineForm getForm() {
		return LineForm.EDGE;
	}

	@Override
	public List<HXCorner> getPoints() {
		return super.getPoints();
	}

}
