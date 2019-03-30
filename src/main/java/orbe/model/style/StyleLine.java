/*
 * Created on Nov 29, 2006
 */
package orbe.model.style;

import java.awt.Color;

import net.sf.doolin.util.Utils;

import orbe.model.Flagged;

/**
 * Style pour une ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: StyleLine.java,v 1.4 2006/12/05 16:01:48 guinnessman Exp $
 */
public class StyleLine extends Flagged implements Style<StyleLine> {

	/**
	 * ID du style
	 */
	private int id;

	/**
	 * Nom du style
	 */
	private String name;

	/**
	 * L'épaisseur est exprimée en % d'hexagone.
	 */
	private int thickness = 5;

	/**
	 * Couleur de la ligne
	 */
	private Color color = Color.BLACK;

	/**
	 * Transparence (50%)
	 */
	private boolean transparent;

	/**
	 * Type de ligne
	 */
	private LineType type;

	/**
	 * Pointillés
	 */
	private LineDotType dot = LineDotType.NONE;

	/**
	 * Graduation
	 */
	private LineGradType grad = LineGradType.NONE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void copy(StyleLine style) {
		Utils.copyProperties(this, style);
	}

	public Style createCopy() {
		StyleLine style = new StyleLine();
		style.copy(this);
		return style;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

	public LineType getType() {
		return type;
	}

	public void setType(LineType type) {
		this.type = type;
	}

	public LineDotType getDot() {
		return dot;
	}

	public void setDot(LineDotType dot) {
		this.dot = dot;
	}

	public LineGradType getGrad() {
		return grad;
	}

	public void setGrad(LineGradType grad) {
		this.grad = grad;
	}
}
