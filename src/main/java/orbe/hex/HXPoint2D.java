/*
 * Created on Nov 24, 2006
 */
package orbe.hex;

import java.awt.geom.Point2D;

/**
 * Coordonnée exprimée dans le référentiel HX.
 * 
 * @author Damien Coraboeuf
 * @version $Id: HXPoint2D.java,v 1.4 2006/12/04 10:46:21 guinnessman Exp $
 */
public class HXPoint2D extends Point2D.Double {

	public HXPoint2D() {
		super();
	}

	public HXPoint2D(HXPoint p) {
		super(p.i, p.j);
	}

	public HXPoint2D(HXPoint2D p) {
		super(p.x, p.y);
	}

	public HXPoint2D(double x, double y) {
		super(x, y);
	}

}
