/*
 * Created on Nov 24, 2006
 */
package orbe.hex;

import java.awt.geom.Point2D;

/**
 * Coordonnéee exprimée dans le référentiel HX.
 *
 * @author Damien Coraboeuf
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
