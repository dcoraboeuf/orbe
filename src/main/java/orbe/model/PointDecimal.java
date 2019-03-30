/*
 * Created on Oct 5, 2006
 */
package orbe.model;

import java.awt.geom.Point2D;

/**
 * Point expressed with big decimals.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PointDecimal.java,v 1.2 2006/10/19 19:44:52 guinnessman Exp $
 */
public class PointDecimal extends Point2D.Double {

	public PointDecimal() {
		super();
	}

	public PointDecimal(double x, double y) {
		super(x, y);
	}

}
