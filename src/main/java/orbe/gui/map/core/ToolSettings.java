/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.core;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import orbe.gui.message.OrbeMessageHexBrushSize;
import orbe.gui.message.OrbeMessageLineForm;
import orbe.gui.message.OrbeMessageLineStyle;
import orbe.gui.message.OrbeMessageSymbol;
import orbe.gui.message.OrbeMessageTerrain;
import orbe.gui.message.OrbeMessageTextStyle;
import orbe.model.OrbeSettings;
import orbe.model.element.line.LineForm;
import orbe.model.style.HexSymbol;
import orbe.model.style.HexTerrain;
import orbe.model.style.RepositoryHexSymbol;
import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.RepositoryTextStyle;
import orbe.model.style.StyleLine;
import orbe.model.style.TextStyle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ToolSettings {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(ToolSettings.class);

	private int hexBrushSize = 0;

	private HexTerrain hexTerrain;

	private HexSymbol hexSymbol;
	
	private TextStyle textStyle;
	
	private StyleLine lineStyle;
	
	private LineForm lineForm;

	/**
	 * Default constructor.
	 */
	public ToolSettings(OrbeControler controler) {
		OrbeSettings settings = controler.getContext().getMap().getSettings();
		
		setHexBrushSize(0);
		hexSymbol = RepositoryHexSymbol.getInstance().getSymbols().get(0);
		hexTerrain = settings.getTerrains().getDefaultTerrain();
		textStyle = settings.getTextStyles().getStyle(RepositoryTextStyle.DEFAUL_STYLE_ID);
		lineStyle = settings.getStyleLineList().getStyle(RepositoryStyleLine.DEFAUL_STYLE_ID);
		lineForm = LineForm.POLY;

		Bus.get().subscribe(OrbeMessageHexBrushSize.class, new Subscriber<OrbeMessageHexBrushSize>() {

			public void receive(OrbeMessageHexBrushSize message) {
				Integer size = message.getValue();
				log.debug("Set hex brush size to " + size);
				setHexBrushSize(size);
			}

		});

		Bus.get().subscribe(OrbeMessageSymbol.class, new Subscriber<OrbeMessageSymbol>() {

			public void receive(OrbeMessageSymbol message) {
				hexSymbol = message.getValue();
			}

		});

		Bus.get().subscribe(OrbeMessageTerrain.class, new Subscriber<OrbeMessageTerrain>() {

			public void receive(OrbeMessageTerrain message) {
				hexTerrain = message.getValue();
			}

		});

		Bus.get().subscribe(OrbeMessageTextStyle.class, new Subscriber<OrbeMessageTextStyle>() {

			public void receive(OrbeMessageTextStyle message) {
				textStyle = message.getValue();
			}

		});

		Bus.get().subscribe(OrbeMessageLineStyle.class, new Subscriber<OrbeMessageLineStyle>() {

			public void receive(OrbeMessageLineStyle message) {
				lineStyle = message.getValue();
			}

		});
		
		Bus.get().subscribe(OrbeMessageLineForm.class, new Subscriber<OrbeMessageLineForm>() {
		
			public void receive(OrbeMessageLineForm message) {
				lineForm = message.getValue();
			}
		
		});
	}

	public int getHexBrushSize() {
		return hexBrushSize;
	}

	public void setHexBrushSize(int hexBrushSize) {
		this.hexBrushSize = hexBrushSize;
	}

	public HexSymbol getHexSymbol() {
		return hexSymbol;
	}

	public HexTerrain getHexTerrain() {
		return hexTerrain;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public LineForm getLineForm() {
		return lineForm;
	}

	public void setLineForm(LineForm lineForm) {
		this.lineForm = lineForm;
	}

	public StyleLine getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(StyleLine lineStyle) {
		this.lineStyle = lineStyle;
	}

}
