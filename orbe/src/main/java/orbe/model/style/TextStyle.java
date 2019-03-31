/*
 * Created on Nov 16, 2006
 */
package orbe.model.style;

import java.awt.Color;
import java.awt.Font;

import net.sf.doolin.util.Utils;

/**
 * Définition de style pour du texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TextStyle.java,v 1.9 2006/12/06 10:19:19 guinnessman Exp $
 */
public class TextStyle implements Style<TextStyle> {

	private int id;

	private String name;

	private String fontName;

	/**
	 * La taille est exprim�e en % de la hauteur d'un hex
	 */
	private int fontSize;

	private boolean fontBold;

	private boolean fontItalic;

	private Color fontColor;

	private boolean shadow;

	private Color shadowColor = Color.WHITE;

	public boolean isFontBold() {
		return fontBold;
	}

	public void setFontBold(boolean fontBold) {
		this.fontBold = fontBold;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public boolean isFontItalic() {
		return fontItalic;
	}

	public void setFontItalic(boolean fontItalic) {
		this.fontItalic = fontItalic;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

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

	public Style createCopy() {
		TextStyle style = new TextStyle();
		Utils.copyProperties(style, this);
		return style;
	}

	/**
	 * Calculates the font style, as suitable for <code>{@link Font}</code>.
	 * 
	 * @return A font style.
	 */
	public int getFontStyle() {
		int style = Font.PLAIN;
		if (fontBold) {
			style |= Font.BOLD;
		}
		if (fontItalic) {
			style |= Font.ITALIC;
		}
		return style;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void copy(TextStyle style) {
		Utils.copyProperties(this, style);
	}

	/**
	 * Calcul de la fonte, avec une �chelle de 1.
	 */
	public Font getFont1() {
		return new Font(fontName, getFontStyle(), 1);
	}

	public boolean isShadow() {
		return shadow;
	}

	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	public Color getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}

}
