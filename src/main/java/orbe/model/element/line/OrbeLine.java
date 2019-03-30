/*
 * Created on Nov 30, 2006
 */
package orbe.model.element.line;

import java.util.LinkedList;
import java.util.List;

import orbe.model.element.OrbeElement;
import orbe.model.style.LineType;
import orbe.model.style.StyleLine;

/**
 * Définition abstraite d'une ligne.
 * 
 * @TODO Définir une implémentation pour les lignes de contour d'hex
 * 
 * @param
 * <P>
 * Type de point.
 * @author Damien Coraboeuf
 * @version $Id: OrbeLine.java,v 1.5 2006/12/05 15:30:39 guinnessman Exp $
 */
public abstract class OrbeLine<P> extends OrbeElement implements Comparable<OrbeLine> {

	/**
	 * Style
	 */
	private StyleLine style;

	/**
	 * Inversion de graduation
	 */
	private boolean gradInverse;

	/**
	 * Liste des points
	 */
	private LinkedList<P> points = new LinkedList<P>();

	/**
	 * Ajout d'un point à la fin de la ligne
	 * 
	 * @param p
	 *            Point à ajouter
	 */
	public void append(P p) {
		points.addLast(p);
	}

	/**
	 * @return Liste des points
	 */
	public List<P> getPoints() {
		return points;
	}

	/**
	 * Forme de ligne
	 */
	public abstract LineForm getForm();

	/**
	 * Dernier point
	 */
	public P getLastPoint() {
		return points.getLast();
	}

	public StyleLine getStyle() {
		return style;
	}

	public void setStyle(StyleLine style) {
		this.style = style;
	}

	public void forEachSegment(SegmentIterator<P> segmentIterator) {
		int size = points.size();
		if (size > 1) {
			for (int i = 0; i < size - 1; i++) {
				P pa = points.get(i);
				P pb = points.get(i + 1);
				boolean goesOn = segmentIterator.onSegment(pa, pb);
				if (!goesOn) {
					break;
				}
			}
		}
	}

	public int compareTo(OrbeLine o) {
		LineType thisType = this.getStyle().getType();
		LineType oType = o.getStyle().getType();
		return thisType.compareTo(oType);
	}

	public boolean isGradInverse() {
		return gradInverse;
	}

	public void setGradInverse(boolean gradInverse) {
		this.gradInverse = gradInverse;
	}

}
