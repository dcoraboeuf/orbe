/*
 * Created on Nov 30, 2006
 */
package orbe.model.element.line;

import java.util.List;

import orbe.hex.HXPoint2D;

/**
 * Ligne de points sans contraintes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbePolyLine.java,v 1.3 2006/12/04 14:33:40 guinnessman Exp $
 */
public class OrbePolyLine extends OrbeLine<HXPoint2D> {

	@Override
	public LineForm getForm() {
		return LineForm.POLY;
	}

	@Override
	public List<HXPoint2D> getPoints() {
		return super.getPoints();
	}

}
