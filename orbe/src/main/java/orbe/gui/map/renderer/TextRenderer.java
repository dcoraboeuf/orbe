/*
 * Created on Nov 20, 2006
 */
package orbe.gui.map.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.math.BigDecimal;

import javax.swing.JTextField;

import orbe.gui.jmx.OrbeManager;
import orbe.gui.map.core.ViewSettings;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint2D;
import orbe.model.OrbeMap;
import orbe.model.PointDecimal;
import orbe.model.element.text.OrbeText;
import orbe.model.style.TextStyle;

/**
 * Rendu du texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TextRenderer.java,v 1.12 2006/12/06 10:19:19 guinnessman Exp $
 */
public class TextRenderer {

	/**
	 * Get the zone that is concerned by any refreshing need.
	 * 
	 * @param text
	 *            Text
	 * @param map
	 *            Map
	 * @param viewSettings
	 *            View settings
	 * @param g
	 *            Graphic environment
	 * @return Zone to refresh
	 */
	public static Rectangle getRefreshingScreenZone(OrbeText text, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		// Zone en référentiel PX
		Double zone = getPXZone(text, map, viewSettings, g);
		// Mise à l'échelle
		Rectangle rect = ScaleMath.scalePXToScreen(map, viewSettings, zone);
		// Ok
		return rect;
	}

	/**
	 * Get the zone that contains strictly the text.
	 * 
	 * @param text
	 *            Text
	 * @param map
	 *            Map
	 * @param viewSettings
	 *            View settings
	 * @param g
	 *            Graphic environment
	 * @return Zone that contains the text.
	 */
	public static Shape getTextScreenZone(OrbeText text, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		// Zone en référentiel PX
		Double zone = getPlainPXZone(text, map, viewSettings, g);
		// Transforms it to the screen
		AffineTransform t = getScreenTransform(text, map, viewSettings);
		Shape shape = t.createTransformedShape(zone);
		// Ok
		return shape;
	}

	public static Double getPXZone(OrbeText text, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		Double zone = getPlainPXZone(text, map, viewSettings, g);

		// Rotation
		AffineTransform t = getPXTransform(text, map, viewSettings);
		Shape shape = t.createTransformedShape(zone);
		Rectangle2D r = shape.getBounds2D();
		zone = new Double(r.getX(), r.getY(), r.getWidth(), r.getHeight());

		// Ok
		return zone;
	}

	/**
	 * Boite du texte, calcul�e sans rotation
	 * 
	 * @param text
	 *            Texte de r�f�rence.
	 * @param map
	 *            Carte.
	 * @param viewSettings
	 *            Settings de la vue.
	 * @param g
	 *            Environnement graphique
	 * @return Boite dans le référentiel PX.
	 */
	protected static Double getPlainPXZone(OrbeText text, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		PointDecimal px = getTextPXPosition(text, map, viewSettings);
		// Font pour le texte
		Font font = getFont(text, map, viewSettings);
		// Font metrics pour l'environnement graphique
		FontMetrics fm = g.getFontMetrics(font);

		// Dimensions en référentiel PX
		double x = px.x;
		double y = px.y - fm.getAscent();
		double width = fm.stringWidth(text.getValue());
		double height = fm.getAscent() + fm.getDescent() + fm.getLeading();
		Double zone = new Double(x, y, width, height);
		return zone;
	}

	public static PointDecimal getTextPXPosition(OrbeText text, OrbeMap map, ViewSettings viewSettings) {
		// Position du texte en HX
		HXPoint2D hx = text.getPosition();
		// Conversion en PX
		PointDecimal px = ScaleMath.scaleHXToPX(map, hx);
		return px;
	}

	/**
	 * Calcul de la transformation � appliquer dans le référentiel pour les
	 * objets relatifs au texte.
	 * 
	 * @param text
	 *            Texte de r�f�rence.
	 * @param map
	 *            Carte.
	 * @param viewSettings
	 *            Settings de la vue.
	 * @return Transformation.
	 */
	public static AffineTransform getPXTransform(OrbeText text, OrbeMap map, ViewSettings viewSettings) {
		int rotation = text.getRotation();
		if (rotation == 0) {
			// No transformation
			return new AffineTransform();
		} else {
			PointDecimal px = getTextPXPosition(text, map, viewSettings);
			double theta = rotation * Math.PI / 180.0;
			// Rotate around the reference
			return AffineTransform.getRotateInstance(theta, px.x, px.y);
		}
	}

	/**
	 * Calcul de la transformation � appliquer � l'�cran pour les objets
	 * relatifs au texte.
	 * 
	 * @param text
	 *            Texte de r�f�rence.
	 * @param map
	 *            Carte.
	 * @param viewSettings
	 *            Settings de la vue.
	 * @return Transformation.
	 */
	protected static AffineTransform getScreenTransform(OrbeText text, OrbeMap map, ViewSettings viewSettings) {
		// Transformation dans le référentiel PX
		AffineTransform transform = getPXTransform(text, map, viewSettings);
		// Application du zoom
		BigDecimal zoom = viewSettings.getZoom();
		transform.scale(zoom.doubleValue(), zoom.doubleValue());
		// Ok
		return transform;
	}

	public static Font getFont(OrbeText text, OrbeMap map, ViewSettings viewSettings) {
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		// Style
		TextStyle style = text.getStyle();
		// Font initiale (taille 1)
		Font font = style.getFont1();
		// Facteur d'�chelle
		int percent = style.getFontSize();
		double factor = percent / 100.0;
		factor *= hxg.getHexHeight();
		factor *= viewSettings.getZoom().doubleValue();
		// Mise � l'�chelle de la police
		font = font.deriveFont((float) factor);
		// Style pour la police
		font = font.deriveFont(style.getFontStyle());
		// Ok
		return font;
	}

	public static void render(OrbeText text, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		String value = text.getValue();
		// Style
		TextStyle style = text.getStyle();
		// Position du texte
		PointDecimal px = getTextPXPosition(text, map, viewSettings);
		// Font pour le texte
		Font font = getFont(text, map, viewSettings);

		// Dimensions en référentiel PX
		double x = px.x;
		double y = px.y;

		// Dimensions en référentiel screen
		int ix = ScaleMath.scalePXToScreen(map, viewSettings, x);
		int iy = ScaleMath.scalePXToScreen(map, viewSettings, y);

		// Environnement graphique
		g.setFont(font);

		// Ok
		AffineTransform oldT = g.getTransform();
		try {
			AffineTransform t = getPXTransform(text, map, viewSettings);
			g.setTransform(t);
			// Ombre
			if (style.isShadow()) {
				g.setColor(style.getShadowColor());
				g.drawString(value, ix + 1, iy + 1);
			}
			// Texte
			g.setColor(style.getFontColor());
			g.drawString(value, ix, iy);
		} finally {
			g.setTransform(oldT);
		}

		// Deboguage de la zone de rendu ?
		if (OrbeManager.getInstance().isLayerTextDebug()) {
			AffineTransform t = getScreenTransform(text, map, viewSettings);
			// Etendue du rafraichissement
			Rectangle rbox = getRefreshingScreenZone(text, map, viewSettings, g);
			g.setColor(Color.GRAY);
			g.draw(rbox);
			// Contour de la boite
			Double box = getPlainPXZone(text, map, viewSettings, g);
			Shape tBox = t.createTransformedShape(box);
			g.setColor(Color.BLUE);
			g.draw(tBox);
			// Support du texte
			GeneralPath path = new GeneralPath();
			path.moveTo((float) px.x, (float) px.y);
			path.lineTo((float) (px.x + box.width), (float) px.y);
			Shape tPath = t.createTransformedShape(path);
			g.setColor(Color.WHITE);
			g.draw(tPath);
		}
	}

	/**
	 * Configure la taille d'un composant pour l'�dition
	 * 
	 * @param field
	 *            Composant � configurer
	 * @param editedText
	 *            Texte �dit�
	 * @param map
	 *            Carte
	 * @param viewSettings
	 *            Settings courants pour la vue
	 * @param g
	 *            Environnement graphique
	 */
	public static void setPosition(JTextField field, OrbeText editedText, OrbeMap map, ViewSettings viewSettings, Graphics2D g) {
		Double pxZone = getPlainPXZone(editedText, map, viewSettings, g);
		// Transforms the zone for the screen (without any rotation)
		Rectangle zone = ScaleMath.scalePXToScreen(map, viewSettings, pxZone);
		// Emplacement
		field.setLocation(zone.x, zone.y);
		// Ajout de la largeur d'un caract�re
		Font font = getFont(editedText, map, viewSettings);
		FontMetrics fm = g.getFontMetrics(font);
		zone.width += fm.charWidth('m');
		// Taille
		field.setSize(zone.width, zone.height);
	}

}
