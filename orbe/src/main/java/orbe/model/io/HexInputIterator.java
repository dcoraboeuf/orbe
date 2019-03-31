/*
 * Created on Sep 18, 2007
 */
package orbe.model.io;

import java.util.StringTokenizer;

import orbe.model.OrbeMap;
import orbe.model.hex.Hex;
import orbe.model.hex.HexIterator;
import orbe.model.style.HexSymbol;
import orbe.model.style.HexTerrain;
import orbe.model.style.RepositoryHexSymbol;

public class HexInputIterator implements HexIterator {

	private int count = 0;

	private int terrainId;

	private int symbolId;

	private OrbeMap orbeMap;

	private StringTokenizer st;

	public HexInputIterator(String text, OrbeMap orbeMap) {
		this.orbeMap = orbeMap;
		this.st = new StringTokenizer(text, ";");
	}

	public void forHex(int i, int j, Hex hex) {
		if (count > 0) {
			setHex(hex);
			count--;
		} else if (st.hasMoreElements()) {
			String token = st.nextToken();
			StringTokenizer hst = new StringTokenizer(token, ",");
			count = Integer.parseInt(hst.nextToken());
			terrainId = Integer.parseInt(hst.nextToken());
			symbolId = Integer.parseInt(hst.nextToken());

			setHex(hex);
			count--;
		} else {
			throw new IllegalStateException("More hexs than expected");
		}
	}

	private void setHex(Hex hex) {
		HexTerrain terrain = orbeMap.getSettings().getTerrains().getTerrain(terrainId);
		hex.setTerrain(terrain);
		if (symbolId > 0) {
			HexSymbol symbol = RepositoryHexSymbol.getInstance().getSymbol(symbolId);
			hex.setSymbol(symbol);
		} else {
			hex.setSymbol(null);
		}
	}
}