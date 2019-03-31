/*
 * Created on Nov 30, 2006
 */
package orbe.model.element.line;

/**
 * Types de lignes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LineForm.java,v 1.3 2006/12/04 18:15:51 guinnessman Exp $
 */
public enum LineForm {
	/**
	 * Ligne libre
	 */
	POLY,
	/**
	 * Ligne de centres d'hexs
	 */
	HEX,
	/**
	 * Ligne de contour d'hexs
	 */
	EDGE;
}
