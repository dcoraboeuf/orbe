/*
 * Created on 10 oct. 06
 */
package orbe.test;

import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;
import net.sf.doolin.gui.Application;
import orbe.hex.HXCorner;
import orbe.hex.HXPoint;
import orbe.hex.HXPoint2D;
import orbe.model.ColorUtils;
import orbe.model.OrbeMap;
import orbe.model.OrbeMapConfig;
import orbe.model.OrbeMapFactory;
import orbe.model.OrbeSettings;
import orbe.model.Scale;
import orbe.model.Unit;
import orbe.model.element.line.OrbeEdgeLine;
import orbe.model.element.line.OrbeHexLine;
import orbe.model.element.line.OrbeLine;
import orbe.model.element.line.OrbeLineList;
import orbe.model.element.line.OrbePolyLine;
import orbe.model.element.text.OrbeText;
import orbe.model.element.text.OrbeTextList;
import orbe.model.hex.Hex;
import orbe.model.hex.HexMap;
import orbe.model.impl.DefaultOrbeMap;
import orbe.model.io.OrbeIO;
import orbe.model.style.LineDotType;
import orbe.model.style.LineGradType;
import orbe.model.style.LineType;
import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.RepositoryTextStyle;
import orbe.model.style.StyleLine;
import orbe.model.style.TextStyle;

import org.apache.commons.io.FileUtils;

public class TestIO extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		if (!Application.isReady()) {
			Application.launch(new String[] { "/orbe/test/Application.xml" });
		}
	}

	public void testIO() throws Exception {
		OrbeMapConfig config = new OrbeMapConfig();
		OrbeMap map = OrbeMapFactory.getInstance().createMap(config);

		OrbeText text = new OrbeText();
		text.setPosition(new HXPoint2D(100, 200));
		text.setValue("Test");
		text.setStyle(map.getSettings().getTextStyles().getStyle(RepositoryTextStyle.DEFAUL_STYLE_ID));
		map.getTextList().add(text);

		OrbePolyLine line = new OrbePolyLine();
		line.setStyle(map.getSettings().getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));
		line.append(new HXPoint2D(10, 12));
		line.append(new HXPoint2D(15, 20));
		map.getLineList().add(line);

		OrbeHexLine hxLine = new OrbeHexLine();
		hxLine.setStyle(map.getSettings().getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));
		hxLine.append(new HXPoint(5, 6));
		hxLine.append(new HXPoint(7, 8));
		map.getLineList().add(hxLine);

		OrbeEdgeLine eLine = new OrbeEdgeLine();
		eLine.setStyle(map.getSettings().getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));
		eLine.append(new HXCorner(5, 6, 0));
		eLine.append(new HXCorner(10, 11, 4));
		map.getLineList().add(eLine);

		File file = File.createTempFile("Test", ".orbe");
		OrbeIO.getInstance().save(map, file);

		String content = FileUtils.readFileToString(file, "UTF-8");
		System.out.println(content);

		read(file);
	}

	protected void read(File file) throws Exception {
		OrbeMap map = OrbeIO.getInstance().read(DefaultOrbeMap.class, file);

		assertNotNull(map);
		assertTrue(map instanceof DefaultOrbeMap);

		assertEquals("2.0", map.getVersion());
		assertEquals(40, map.getWidth());
		assertEquals(60, map.getHeight());

		OrbeSettings settings = map.getSettings();
		assertNotNull(settings);
		Scale scale = settings.getScale();
		assertEquals(new BigDecimal(20), scale.getValue());
		assertEquals(Unit.PIXEL, scale.getUnit());

		OrbeTextList textList = map.getTextList();
		assertNotNull(textList);
		List<OrbeText> texts = textList.getElements();
		assertNotNull(texts);
		assertEquals(1, texts.size());
		OrbeText text = texts.get(0);
		assertEquals("Test", text.getValue());
		assertEquals(100, text.getPosition().x, 0.1);
		assertEquals(200, text.getPosition().y, 0.1);
		assertEquals(RepositoryTextStyle.DEFAUL_STYLE_ID, text.getStyle().getId());
		// V�rification de la r�f�rence
		assertTrue(text.getStyle() == settings.getTextStyles().getStyle(RepositoryTextStyle.DEFAUL_STYLE_ID));

		OrbeLineList lineList = map.getLineList();
		assertNotNull(lineList);
		List<OrbeLine> lines = lineList.getElements();
		assertNotNull(lines);
		assertEquals(3, lines.size());

		OrbeLine line = lines.get(0);
		assertTrue(line instanceof OrbePolyLine);
		OrbePolyLine polyLine = (OrbePolyLine) line;
		List<HXPoint2D> points = polyLine.getPoints();
		assertNotNull(points);
		assertEquals(2, points.size());
		assertEquals(new HXPoint2D(10, 12), points.get(0));
		assertEquals(new HXPoint2D(15, 20), points.get(1));
		assertNotNull(polyLine.getStyle());
		assertEquals(RepositoryStyleLine.DEFAUL_STYLE_ID, polyLine.getStyle().getId());
		assertTrue(polyLine.getStyle() == settings.getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));

		line = lines.get(1);
		assertTrue(line instanceof OrbeHexLine);
		OrbeHexLine hexLine = (OrbeHexLine) line;
		List<HXPoint> hxs = hexLine.getPoints();
		assertNotNull(hxs);
		assertEquals(2, hxs.size());
		assertEquals(new HXPoint(5, 6), hxs.get(0));
		assertEquals(new HXPoint(7, 8), hxs.get(1));
		assertNotNull(hexLine.getStyle());
		assertEquals(RepositoryStyleLine.DEFAUL_STYLE_ID, hexLine.getStyle().getId());
		assertTrue(hexLine.getStyle() == settings.getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));

		line = lines.get(2);
		assertTrue(line instanceof OrbeEdgeLine);
		OrbeEdgeLine edgeLine = (OrbeEdgeLine) line;
		List<HXCorner> edges = edgeLine.getPoints();
		assertNotNull(edges);
		assertEquals(2, edges.size());
		assertEquals(new HXCorner(5, 6, 0), edges.get(0));
		assertEquals(new HXCorner(10, 11, 4), edges.get(1));
		assertNotNull(edgeLine.getStyle());
		assertEquals(RepositoryStyleLine.DEFAUL_STYLE_ID, edgeLine.getStyle().getId());
		assertTrue(edgeLine.getStyle() == settings.getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID));

		HexMap hxMap = map.getHexMap();
		assertNotNull(hxMap);

		Hex hex = hxMap.getHex(1, 1);
		assertNotNull(hex);
		assertNull(hex.getSymbol());
		assertNotNull(hex.getTerrain());
		assertEquals(1, hex.getTerrain().getId());
	}

	public void testOldVersion() throws Exception {
		File file = new File("samples/firmellan.orbe");
		assertTrue(file.exists());

		OrbeMap map = OrbeIO.getInstance().read(DefaultOrbeMap.class, file);
		assertNotNull(map);
		assertTrue(map instanceof DefaultOrbeMap);

		assertEquals("2.0", map.getVersion());
		assertEquals(225, map.getWidth());
		assertEquals(318, map.getHeight());

		HexMap hxMap = map.getHexMap();
		assertNotNull(hxMap);

		Hex hex = hxMap.getHex(224, 317);
		assertNotNull(hex);
		assertNull(hex.getSymbol());
		assertNotNull(hex.getTerrain());
		assertEquals(1, hex.getTerrain().getId());

		OrbeSettings settings = map.getSettings();
		assertNotNull(settings);
		Scale scale = settings.getScale();
		assertEquals(new BigDecimal(20), scale.getValue());
		assertEquals(Unit.PIXEL, scale.getUnit());

		// Styles de texte
		RepositoryTextStyle textStyles = settings.getTextStyles();
		assertNotNull(textStyles);
		List<TextStyle> styles = textStyles.getStyles();
		assertNotNull(styles);
		assertEquals(5, styles.size());
		TextStyle style = styles.get(0);
		assertEquals(1, style.getId());
		assertEquals("Pays", style.getName());
		assertEquals("Lucida Casual CE", style.getFontName());
		assertEquals(new Color(0, 0, 0), style.getFontColor());
		assertEquals(103, style.getFontSize());
		assertEquals(ColorUtils.fromHEX("99CC00"), style.getShadowColor());
		assertEquals(false, style.isFontBold());
		assertEquals(false, style.isFontItalic());
		assertEquals(true, style.isShadow());

		// Styles de ligne
		RepositoryStyleLine styleLineList = settings.getStyleLineList();
		assertNotNull(styleLineList);
		List<StyleLine> stylesLine = styleLineList.getStyles();
		assertNotNull(stylesLine);
		assertEquals(10, stylesLine.size());
		StyleLine styleLine = styleLineList.getStyle(1);
		assertEquals(1, styleLine.getId());
		assertEquals("Rivière", styleLine.getName());
		assertEquals(10, styleLine.getThickness());
		assertEquals(LineType.RIVER, styleLine.getType());
		assertEquals(ColorUtils.fromHEX("3399FF"), styleLine.getColor());
		assertEquals(LineDotType.NONE, styleLine.getDot());
		assertEquals(LineGradType.NONE, styleLine.getGrad());
		styleLine = styleLineList.getStyle(2);
		assertEquals(2, styleLine.getId());
		assertEquals("Route", styleLine.getName());
		assertEquals(10, styleLine.getThickness());
		assertEquals(LineType.ROAD, styleLine.getType());
		assertEquals(ColorUtils.fromHEX("663300"), styleLine.getColor());
		assertEquals(LineDotType.CIRCLE, styleLine.getDot());
		assertEquals(LineGradType.NONE, styleLine.getGrad());

		// Textes
		OrbeTextList textList = map.getTextList();
		assertNotNull(textList);
		List<OrbeText> texts = textList.getElements();
		assertNotNull(texts);
		assertEquals(455, texts.size());
		// Lookup for "Tour de Glace"
		OrbeText text = null;
		for (OrbeText t : texts) {
			if ("Tour de Glace".equals(t.getValue())) {
				text = t;
				break;
			}
		}
		assertNotNull(text);
		assertEquals(54.1, text.getPosition().x, 0.1);
		assertEquals(9.7, text.getPosition().y, 0.1);
		assertEquals(0, text.getRotation());
		TextStyle textStyle = text.getStyle();
		assertNotNull(textStyle);
		assertEquals("Standard", textStyle.getName());

		// TODO Complete tests for old version
	}

}
