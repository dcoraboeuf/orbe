/*
 * Created on Dec 1, 2006
 */
package orbe.gui.map.renderer.stroke;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import orbe.model.style.LineGradType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Trait utilisé pour une graduation.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GradStroke.java,v 1.2 2006/12/04 14:33:42 guinnessman Exp $
 */
public class GradStroke implements Stroke {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(GradStroke.class);

	private float thickness;

	private boolean both;

	private boolean inverse;

	public GradStroke(float thickness, LineGradType grad, boolean inverse) {
		this.thickness = thickness;
		this.both = (grad == LineGradType.BOTH);
		this.inverse = inverse;
	}

	public Shape createStrokedShape(Shape p) {
		GeneralPath path = new GeneralPath();

		// Forme de base
		BasicStroke stroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		Shape basic = stroke.createStrokedShape(p);
		path.append(basic, false);

		// Itérateur sur le chemin
		PathIterator pi = p.getPathIterator(null, thickness);
		float[] co = new float[6];
		float totalLength = 0;
		float gradInterval = thickness * 3;
		float gradLength = thickness * 2;
		int gradNumber = 0;
		Point2D.Float from = null;
		while (!pi.isDone()) {
			// Get current segment
			int type = pi.currentSegment(co);
			// Start a subpath
			if (type == PathIterator.SEG_MOVETO) {
				from = new Point2D.Float(co[0], co[1]);
			}
			// Ends a segment
			else if (type == PathIterator.SEG_LINETO) {
				// Checks the state
				if (from == null) {
					log.error("No previous point has been found.");
				} else {
					float xa = from.x;
					float ya = from.y;
					float xb = co[0];
					float yb = co[1];
					// Length of this segment
					float segmentLength = getLength(xa, ya, xb, yb);
					// Grads on this segment
					boolean ok = true;
					while (ok) {
						// Position of the grad
						float gradPosition = gradNumber * gradInterval;
						// Offset of the grad on the segment
						float gradOffset = gradPosition - totalLength;
						// If offset still in the segment, draw it and progress
						if (gradOffset < segmentLength) {
							// Coefficient of the grad position
							float coef = gradOffset / segmentLength;
							// Position of the graduation
							float xg = xa + coef * (xb - xa);
							float yg = ya + coef * (yb - ya);
							// Longueur du segment de graduation
							coef = gradLength / segmentLength;
							float dxl = coef * (xb - xa);
							float dyl = coef * (yb - ya);
							// Point de départ
							float xn = xg;
							float yn = yg;
							if (both || !inverse) {
								xn -= dyl;
								yn += dxl;
							}
							// Point d'arriv�e
							float xm = xg;
							float ym = yg;
							if (both || inverse) {
								xm += dyl;
								ym -= dxl;
							}
							// Segment de la graduation
							GeneralPath grad = new GeneralPath();
							grad.moveTo(xn, yn);
							grad.lineTo(xm, ym);
							// Uses the basic stroke to draw this segment
							Shape gradShape = stroke.createStrokedShape(grad);
							// Appends this shape to the total
							path.append(gradShape, false);
							// Grad progress
							gradNumber++;
						}
						// Else, leave this segment
						else {
							ok = false;
						}
					}
					// Increases the total length
					totalLength += segmentLength;
					// Ok
					from = new Point2D.Float(xb, yb);
				}
			}
			// Ends a subpath
			else if (type == PathIterator.SEG_CLOSE) {
				from = null;
			}
			// No other type is expected
			else {
				log.error("No other type is expected.");
			}
			// Continue on the path
			pi.next();
		}

		// Ok
		return path;
	}

	private float getLength(float xa, float ya, float xb, float yb) {
		float dx = xb - xa;
		float dy = yb - ya;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("GradStroke");
		frame.setSize(400, 300);
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel content = new JPanel() {

			@Override
			protected void paintComponent(Graphics g1) {
				setBackground(Color.WHITE);
				super.paintComponent(g1);

				Graphics2D g = (Graphics2D) g1;

				GradStroke stroke = new GradStroke(8, LineGradType.ONE, false);
				g.setStroke(stroke);
				g.setColor(Color.BLUE);
				g.draw(createPath());

				g.setStroke(new BasicStroke());
				g.setColor(Color.WHITE);
				g.draw(createPath());
			}

			private GeneralPath createPath() {
				GeneralPath path = new GeneralPath();
				path.moveTo(100, 50);
				path.lineTo(300, 50);
				path.lineTo(150, 100);
				path.lineTo(100, 250);
				path.lineTo(250, 150);
				return path;
			}

		};
		frame.setContentPane(content);

		frame.setVisible(true);
	}

}
