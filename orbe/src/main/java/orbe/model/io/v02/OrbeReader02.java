/*
 * Created on 3 oct. 06
 */
package orbe.model.io.v02;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.xml.DOMUtils;
import orbe.gui.map.scale.ScaleMath;
import orbe.hex.HXCorner;
import orbe.hex.HXGraphicConstants;
import orbe.hex.HXGraphics;
import orbe.hex.HXPoint;
import orbe.hex.HXPoint2D;
import orbe.model.ColorUtils;
import orbe.model.IErrors;
import orbe.model.OrbeSettings;
import orbe.model.PointDecimal;
import orbe.model.Scale;
import orbe.model.Unit;
import orbe.model.element.line.LineForm;
import orbe.model.element.line.OrbeEdgeLine;
import orbe.model.element.line.OrbeHexLine;
import orbe.model.element.line.OrbeLine;
import orbe.model.element.line.OrbePolyLine;
import orbe.model.element.text.OrbeText;
import orbe.model.hex.Hex;
import orbe.model.hex.HexMap;
import orbe.model.impl.DefaultOrbeMap;
import orbe.model.io.AbstractOrbeReader;
import orbe.model.style.DefaultHexTerrain;
import orbe.model.style.HexSymbol;
import orbe.model.style.HexTerrain;
import orbe.model.style.LineDotType;
import orbe.model.style.LineGradType;
import orbe.model.style.LineType;
import orbe.model.style.RepositoryHexSymbol;
import orbe.model.style.RepositoryHexTerrain;
import orbe.model.style.StyleLine;
import orbe.model.style.StyleLineFlags;
import orbe.model.style.TextStyle;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class OrbeReader02 extends AbstractOrbeReader<DefaultOrbeMap> implements StyleLine02Constants {

	public DefaultOrbeMap read(Class<DefaultOrbeMap> mapClass, File file) {
		DefaultOrbeMap map = new DefaultOrbeMap();

		// Initialisation des settings
		OrbeSettings settings = new OrbeSettings();
		settings.setTerrains(new RepositoryHexTerrain());
		settings.getScale().setUnit(Unit.PIXEL);
		settings.getScale().setValue(new BigDecimal(20));
		map.setSettings(settings);

		try {
			// DOM configuration
			DocumentBuilderFactory domBuildFactory = DocumentBuilderFactory.newInstance();
			domBuildFactory.setValidating(false);
			domBuildFactory.setNamespaceAware(false);
			domBuildFactory.setExpandEntityReferences(false);
			DocumentBuilder domBuilder = domBuildFactory.newDocumentBuilder();
			domBuilder.setEntityResolver(new EntityResolver() {

				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					if ("orbe".equals(publicId)) {
						return new InputSource(getClass().getResource("/orbe/model/io/v02/orbe.dtd").toString());
					} else {
						return null;
					}
				}

			});

			// DOM parsing
			Document dom = domBuilder.parse(file);

			// Conversion
			parse(dom, map);

			// Ok
			return map;

		} catch (Exception ex) {
			throw new CodeException(IErrors.IO_CANNOT_READ, ex, file, ex);
		}
	}

	private void parse(Document dom, DefaultOrbeMap map) {
		// Root
		Element eOrbe = dom.getDocumentElement();

		// Styles
		Element eStyles = DOMUtils.getElement(eOrbe, "styles");

		// Terrains
		parseTerrains(map, eStyles);

		// Styles de texte
		parseTextStyles(map, eStyles);

		// Styles de ligne
		parseStylesLine(map, eStyles);

		// Textes
		parseTextList(map, eOrbe);

		// Lignes
		parseLineList(map, eOrbe);

		// Hexmap
		Element eHexmap = DOMUtils.getElement(eOrbe, "hexmap");

		// Width & height
		int width = DOMUtils.getInt(eHexmap, "largeur", true, 0);
		int height = DOMUtils.getInt(eHexmap, "hauteur", true, 0);
		map.setWidth(width);
		map.setHeight(height);
		map.setHexMap(map.createHexMap());

		// Hexmap
		String hexmapData = DOMUtils.getText(eHexmap, "hexmap-data");
		parseHexMap(hexmapData, map);
	}

	private void parseStylesLine(DefaultOrbeMap map, Element eStyles) {
		Element eStylesLigne = DOMUtils.getElement(eStyles, "styles-ligne");
		List<Element> listStyleLigne = DOMUtils.getElements(eStylesLigne, "style-ligne");
		int styleLineId = 1;
		for (Element eStyleLigne : listStyleLigne) {
			StyleLine style = parseStyleLine(styleLineId, eStyleLigne);
			// Ok
			map.getSettings().getStyleLineList().addStyle(style);
			styleLineId++;
		}
	}

	private StyleLine parseStyleLine(int styleLineId, Element eStyleLigne) {
		// Donn�es
		String name = DOMUtils.getText(eStyleLigne, "nom");
		int typeCode = DOMUtils.getInt(eStyleLigne, "type", true, 0);
		int kindCode = DOMUtils.getInt(eStyleLigne, "kind", true, 0);
		int thickness = 5 * DOMUtils.getInt(eStyleLigne, "thick", true, 0);
		String colorHEX = DOMUtils.getText(eStyleLigne, "couleur");
		Color color = ColorUtils.fromHEX(colorHEX);
		boolean transparent = DOMUtils.getBoolean(eStyleLigne, "trans", true, false);
		int dotCode = DOMUtils.getInt(eStyleLigne, "dotted", true, 0);
		int gradCode = DOMUtils.getInt(eStyleLigne, "grad", true, 0);
		// Traduction des codes
		LineType type = toLineType(typeCode);
		LineDotType dot = toLineDotType(dotCode);
		LineGradType grad = toLineGradType(gradCode);
		// création du style
		StyleLine style = new StyleLine();
		style.setId(styleLineId);
		style.setName(name);
		style.setColor(color);
		style.setThickness(thickness);
		style.setTransparent(transparent);
		style.setType(type);
		style.setDot(dot);
		style.setGrad(grad);
		// Mettre un flag indiquant le type de ligne (CENTER, EDGE, FREE)
		switch (kindCode) {
		case KIND_HEX:
			style.setFlag(StyleLineFlags.FLAG_02_KIND_HEX, true);
			break;
		case KIND_EDGES:
			style.setFlag(StyleLineFlags.FLAG_02_KIND_EDGE, true);
			break;
		case KIND_POLY:
		case KIND_FREE:
		default:
			style.setFlag(StyleLineFlags.FLAG_02_KIND_OTHER, true);
			break;
		}
		// Ok
		return style;
	}

	private LineGradType toLineGradType(int gradCode) {
		LineGradType grad;
		switch (gradCode) {
		case GRAD_NO:
		default:
			grad = LineGradType.NONE;
			break;
		case GRAD_LEFT:
		case GRAD_RIGHT:
			grad = LineGradType.ONE;
			break;
		case GRAD_BOTH:
			grad = LineGradType.BOTH;
			break;
		}
		return grad;
	}

	private LineDotType toLineDotType(int dotCode) {
		LineDotType dot;
		switch (dotCode) {
		case DOTTED_NO:
		default:
			dot = LineDotType.NONE;
			break;
		case DOTTED_SHORT:
			dot = LineDotType.SHORT;
			break;
		case DOTTED_MEDIUM:
			dot = LineDotType.MEDIUM;
			break;
		case DOTTED_LONG:
			dot = LineDotType.LONG;
			break;
		case DOTTED_SQUARE:
			dot = LineDotType.SQUARE;
			break;
		case DOTTED_CIRCLE:
			dot = LineDotType.CIRCLE;
			break;
		}
		return dot;
	}

	private LineType toLineType(int typeCode) {
		LineType type;
		switch (typeCode) {
		case TYPE_RIVER:
			type = LineType.RIVER;
			break;
		case TYPE_ROAD:
			type = LineType.ROAD;
			break;
		default:
			type = LineType.OTHER;
			break;
		}
		return type;
	}

	private void parseLineList(DefaultOrbeMap map, Element eOrbe) {
		Element eLignes = DOMUtils.getElement(eOrbe, "lignes");
		List<Element> listLignes = DOMUtils.getElements(eLignes, "ligne");
		for (Element eLigne : listLignes) {
			OrbeLine line = parseLine(map, eLigne);
			map.getLineList().add(line);
		}
	}

	private OrbeLine parseLine(DefaultOrbeMap map, Element eLigne) {
		// Donn�es de ligne
		String line02Data = DOMUtils.getText(eLigne, "ligne-data");
		// Parsing de la ligne
		List<Point> lineData = parseLineData(line02Data);
		// R�f�rence au style
		String styleRef = DOMUtils.getText(eLigne, "ref-style");
		// Recherche du style
		StyleLine style = map.getSettings().getStyleLineList().getStyleByName(styleRef);
		// D�termination de la forme de la ligne
		LineForm form;
		if (style.getFlag(StyleLineFlags.FLAG_02_KIND_HEX)) {
			form = LineForm.HEX;
		} else if (style.getFlag(StyleLineFlags.FLAG_02_KIND_EDGE)) {
			form = LineForm.EDGE;
		} else {
			form = LineForm.POLY;
		}
		// création de la ligne
		OrbeLine line;
		switch (form) {
		case POLY:
			line = parsePolyLine(map, lineData);
			break;
		case HEX:
			line = parseHexLine(map, lineData);
			break;
		case EDGE:
			line = parseEdgeLine(map, lineData);
			break;
		default:
			throw new CodeException("Error.IO.02.UnknownLineForm", form);
		}
		// Style
		line.setStyle(style);
		// Ok
		return line;
	}

	private List<Point> parseLineData(String line02Data) {
		line02Data = StringUtils.trim(line02Data);
		StringTokenizer st = new StringTokenizer(line02Data, ";");
		ArrayList<Point> list = new ArrayList<Point>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			String[] s = StringUtils.split(token, ",");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			Point p = new Point(x, y);
			list.add(p);
		}
		return list;
	}

	/**
	 * Chaque point est exprim� en coordonn�es PX. Il faut retrouver le centre
	 * d'hex correspondant.
	 */
	private OrbeHexLine parseHexLine(DefaultOrbeMap map, List<Point> lineData) {
		OrbeHexLine line = new OrbeHexLine();
		// G�om�trie
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		// Pour chaque point
		for (Point p : lineData) {
			// référentiel PX
			PointDecimal px = new PointDecimal(p.x, p.y);
			// decalage 0.2
			px = shift02(map, px);
			// Centre d'hex
			HXPoint hx = hxg.locateHex(px);
			// Ok
			line.append(hx);
		}
		// Ok
		return line;
	}

	/**
	 * Chaque point est exprim� en coordonn�es PX. Il faut retrouver le coin
	 * d'hex correspondant.
	 */
	private OrbeEdgeLine parseEdgeLine(DefaultOrbeMap map, List<Point> lineData) {
		OrbeEdgeLine line = new OrbeEdgeLine();
		// G�om�trie
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		// Pour chaque point
		for (Point p : lineData) {
			// référentiel PX
			PointDecimal px = new PointDecimal(p.x, p.y);
			// decalage 0.2
			px = shift02(map, px);
			// Coin d'hex
			HXCorner c = hxg.locateCorner(px);
			// Ok
			line.append(c);
		}
		// Ok
		return line;
	}

	/**
	 * Chaque point est exprim� en coordonn�es PX. Il faut les transformer en
	 * coordonn�es PX.
	 */
	private OrbePolyLine parsePolyLine(DefaultOrbeMap map, List<Point> lineData) {
		OrbePolyLine line = new OrbePolyLine();
		// Pour chaque point
		for (Point p : lineData) {
			// référentiel PX
			PointDecimal px = new PointDecimal(p.x, p.y);
			// decalage 0.2
			px = shift02(map, px);
			// Conversion vers HX
			HXPoint2D hx = ScaleMath.scalePXToHX(map, px);
			// Ok
			line.append(hx);
		}
		// Ok
		return line;
	}

	private void parseTextList(DefaultOrbeMap map, Element eOrbe) {
		Element eTextes = DOMUtils.getElement(eOrbe, "textes");
		List<Element> listTexte = DOMUtils.getElements(eTextes, "texte");
		for (Element eTexte : listTexte) {
			OrbeText text = parseText(map, eTexte);
			// Adds the text
			map.getTextList().add(text);
		}
	}

	private OrbeText parseText(DefaultOrbeMap map, Element eTexte) {
		// Creates text instance
		OrbeText text = new OrbeText();
		// Valeur
		String value = DOMUtils.getText(eTexte, "value");
		text.setValue(value);
		// Position
		Element ePosition = DOMUtils.getElement(eTexte, "position");
		int ox = DOMUtils.getInt(ePosition, "x", true, 0);
		int oy = DOMUtils.getInt(ePosition, "y", true, 0);
		// Position PX
		PointDecimal px = new PointDecimal(ox, oy);
		// Prise en compte du decalage 0.2
		px = shift02(map, px);
		// HX
		HXPoint2D hx = ScaleMath.scalePXToHX(map, px);
		text.setPosition(hx);
		// Rotation
		int rotation = DOMUtils.getInt(eTexte, "rotation", true, 0);
		text.setRotation(rotation);
		// Style
		String styleName = DOMUtils.getText(eTexte, "ref-style");
		TextStyle style = map.getSettings().getTextStyles().getStyleByName(styleName);
		text.setStyle(style);
		return text;
	}

	/**
	 * Prise en compte du decalage vertical en 0.2. Le rapport <code>r</code>
	 * est calcul� comme suit:
	 * <p>
	 * <code>r = HauteurHex02 / HauteurHex20
	 * </p>
	 * <code>HauteurHex02</code> vaut 23, c'est l'arrondi entier de la hauteur exacte de l'hex. <code>HauteurHex20</code> est la hauteur r�elle de l'hex.
	 */
	private PointDecimal shift02(DefaultOrbeMap map, PointDecimal px) {
		Scale scale = map.getSettings().getScale();
		double pxByHex = scale.toPixels();
		double h20 = pxByHex * HXGraphicConstants.HXDIM_V_OFFSET2;
		double h02 = Math.round(h20);
		double r = h02 / h20;
		px.y /= r;
		return px;
	}

	private void parseTextStyles(DefaultOrbeMap map, Element eStyles) {
		Element eStylesTexte = DOMUtils.getElement(eStyles, "styles-texte");
		List<Element> listStyleTexte = DOMUtils.getElements(eStylesTexte, "style-texte");
		int styleTexteId = 1;
		for (Element eStyleTexte : listStyleTexte) {
			parseTextStyle(map, styleTexteId++, eStyleTexte);
		}
	}

	private void parseTextStyle(DefaultOrbeMap map, int styleTexteId, Element eStyleTexte) {
		// Creates the style instance
		TextStyle style = new TextStyle();
		// ID
		style.setId(styleTexteId);
		// Name
		String name = DOMUtils.getText(eStyleTexte, "nom");
		style.setName(name);
		// Font name
		Element eFont = DOMUtils.getElement(eStyleTexte, "font");
		String fontName = DOMUtils.getText(eFont, "name");
		style.setFontName(fontName);
		// Font size
		int fontSize = DOMUtils.getInt(eFont, "size", true, 0);
		HXGraphics hxg = new HXGraphics(map.getSettings().getScale());
		double hexHeight = hxg.getHexHeight();
		double fontRatio = fontSize / hexHeight;
		int fontSizePercent = (int)	 (fontRatio * 100);
		style.setFontSize(fontSizePercent);
		// Font style
		boolean fontBold = DOMUtils.getBoolean(eFont, "bold", true, false);
		boolean fontItalic = DOMUtils.getBoolean(eFont, "italic", true, false);
		style.setFontBold(fontBold);
		style.setFontItalic(fontItalic);
		// Font color
		String fontColorHEX = DOMUtils.getText(eFont, "couleur");
		Color fontColor = ColorUtils.fromHEX(fontColorHEX);
		style.setFontColor(fontColor);
		// Shadow color
		Element eStyleCadre = DOMUtils.getElement(eStyleTexte, "style-cadre");
		String shadowColorHEX = DOMUtils.getText(eStyleCadre, "shadow-color");
		Color shadowColor = ColorUtils.fromHEX(shadowColorHEX);
		style.setShadowColor(shadowColor);
		// Shadow
		Element eShadowOffset = DOMUtils.getElement(eStyleCadre, "shadow-offset");
		int shadowOffsetW = DOMUtils.getInt(eShadowOffset, "width", true, 0);
		int shadowOffsetH = DOMUtils.getInt(eShadowOffset, "height", true, 0);
		boolean shadow = (shadowOffsetH != 0) && (shadowOffsetW != 0);
		style.setShadow(shadow);
		// Ajout du style
		map.getSettings().getTextStyles().addStyle(style);
	}

	private void parseTerrains(DefaultOrbeMap map, Element eStyles) {
		Element eTerrains = DOMUtils.getElement(eStyles, "terrains");
		List<Element> listTerrains = DOMUtils.getElements(eTerrains, "terrain");
		for (Element eTerrain : listTerrains) {
			int id = Integer.parseInt(eTerrain.getAttribute("id"));
			String nom = DOMUtils.getText(eTerrain, "nom");
			Color color = ColorUtils.fromHEX(DOMUtils.getText(eTerrain, "couleur"));
			Element eRefSymbole = DOMUtils.getElement(eTerrain, "ref-symbole");
			DefaultHexTerrain hexTerrain = new DefaultHexTerrain();
			hexTerrain.setId(id);
			hexTerrain.setColor(color);
			hexTerrain.setName(nom);
			if (eRefSymbole != null) {
				int symbolId = Integer.parseInt(eRefSymbole.getAttribute("id"));
				HexSymbol symbol = RepositoryHexSymbol.getInstance().getSymbol(symbolId);
				hexTerrain.setSymbol(symbol);
			}
			map.getSettings().getTerrains().addTerrain(hexTerrain);
		}
	}

	private void parseHexMap(String hexmapData, DefaultOrbeMap map) {
		hexmapData = StringUtils.trimToEmpty(hexmapData);
		HexMap hexMap = map.getHexMap();
		int width = map.getWidth();
		int index = 0;
		// Parsing du texte
		StringTokenizer data = new StringTokenizer(hexmapData, "()");
		while (data.hasMoreTokens()) {
			int count = Integer.parseInt(data.nextToken());
			String token = data.nextToken();
			String[] tokens = StringUtils.split(token, '.');
			int terrainId = Integer.parseInt(tokens[0]);
			HexTerrain terrain = map.getSettings().getTerrains().getTerrain(terrainId);
			if (terrain == null) {
				throw new CodeException(IErrors.IO_02_UNKNOWN_TERRAIN, terrainId);
			}
			HexSymbol symbol = null;
			if (tokens.length > 1) {
				String symbolIdValue = tokens[1];
				if (StringUtils.isNotBlank(symbolIdValue)) {
					int symbolId = Integer.parseInt(symbolIdValue);
					symbol = RepositoryHexSymbol.getInstance().getSymbol(symbolId);
				}
			}
			for (int n = index; n < index + count; n++) {
				int i = n % width;
				int j = n / width;
				Hex hex = hexMap.getHex(i, j);
				hex.setTerrain(terrain);
				hex.setSymbol(symbol);
			}
			index += count;
		}
	}

}
