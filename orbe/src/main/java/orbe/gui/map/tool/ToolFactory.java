/*
 * Created on Nov 6, 2006
 */
package orbe.gui.map.tool;

import java.util.HashMap;

public class ToolFactory {

	/**
	 * Unique instance
	 */
	private static ToolFactory instance = null;

	/**
	 * Get the instance
	 */
	public static ToolFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized ToolFactory createInstance() {
		if (instance != null) {
			return instance;
		} else {
			ToolFactory temp = new ToolFactory();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Outils
	 */
	private HashMap<String, Tool> tools = new HashMap<String, Tool>();

	/**
	 * Initialization
	 */
	private ToolFactory() {
		registerTool(new ToolZoom());
		registerTool(new ToolHexSymbol());
		registerTool(new ToolHexTerrain());
		registerTool(new ToolHexPaint());
		registerTool(new ToolText());
		registerTool(new ToolLine());
		registerTool(new ToolHand());
	}

	public void registerTool(Tool tool) {
		tools.put(tool.getId(), tool);
	}

	public Tool getTool(String id) {
		return tools.get(id);
	}

}
