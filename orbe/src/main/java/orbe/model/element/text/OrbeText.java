/*
 * Created on Nov 16, 2006
 */
package orbe.model.element.text;

import orbe.hex.HXPoint2D;
import orbe.model.element.OrbeElement;
import orbe.model.style.TextStyle;

/**
 * Texte g�r� par la carte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeText.java,v 1.7 2006/12/01 14:09:55 guinnessman Exp $
 */
public class OrbeText extends OrbeElement {
	
	/**
	 * Style
	 */
	private TextStyle style;

	/**
	 * Valeur du texte
	 */
	private String value;

	/**
	 * Rotation (indiqu�e en degr�s). L'axe � 0� est l'axe horizontal
	 * gauche-&gt;droite, et la direction positive est le sens trigonom�trique
	 * (dans le référentiel de l'image, c'est-�-dire avec les Y orient�s vers le
	 * bas). Sur l'�cran, le mouvement trigonom�trique appara�t dans le sens des
	 * aiguilles d'une montre.
	 */
	private int rotation;

	/**
	 * Position du texte (exprim� dans le référentiel HX)
	 */
	private HXPoint2D position;

	public HXPoint2D getPosition() {
		return position;
	}

	public void setPosition(HXPoint2D position) {
		this.position = position;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public TextStyle getStyle() {
		return style;
	}

	public void setStyle(TextStyle style) {
		this.style = style;
	}

}
