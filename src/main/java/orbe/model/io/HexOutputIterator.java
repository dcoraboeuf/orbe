/*
 * Created on Sep 15, 2007
 */
package orbe.model.io;

import orbe.model.hex.Hex;
import orbe.model.hex.HexIterator;
import orbe.model.style.HexSymbol;

public class HexOutputIterator implements HexIterator {

	private StringBuffer buffer = new StringBuffer();

	private int index = 0;

	private int currentTerrainId = -1;

	private int currentSymbolId = -1;

	private int currentCount = 0;

	public String getString() {
		return buffer.toString();
	}

	public void flush() {
		if (index > 0) {
			buffer.append(";");
		}
		buffer.append(currentCount).append(",").append(currentTerrainId).append(",").append(currentSymbolId);
		index++;
	}

	public void forHex(int i, int j, Hex hex) {
		int terrainId = hex.getTerrain().getId();
		HexSymbol symbol = hex.getSymbol();
		int symbolId = symbol != null ? symbol.getId() : 0;
		if (terrainId == currentTerrainId && symbolId == currentSymbolId) {
			currentCount++;
		} else {
			if (currentTerrainId >= 0) {
				flush();
			}
			currentTerrainId = terrainId;
			currentSymbolId = symbolId;
			currentCount = 1;
		}
	}

}