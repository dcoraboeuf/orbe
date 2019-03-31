/*
 * Created on Nov 30, 2006
 */
package orbe.model.element.line;

import java.util.List;

import orbe.hex.HXPoint;

/**
 * Ligne reliant les centres d'hex.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeHexLine.java,v 1.3 2006/12/04 14:33:40 guinnessman Exp $
 */
public class OrbeHexLine extends OrbeLine<HXPoint> {

	@Override
	public LineForm getForm() {
		return LineForm.HEX;
	}

	@Override
	public List<HXPoint> getPoints() {
		return super.getPoints();
	}

}
