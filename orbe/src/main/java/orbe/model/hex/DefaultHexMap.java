/*
 * Created on Oct 23, 2006
 */
package orbe.model.hex;

import orbe.hex.HXPoint;
import orbe.model.style.HexTerrain;

public class DefaultHexMap implements HexMap {

	private Hex[] hexs;

	private int width;

	public DefaultHexMap(int width, int height, HexTerrain defaultTerrain) {
		this.width = width;
		int length = width * height;
		hexs = new Hex[length];
		for (int i = 0; i < hexs.length; i++) {
			Hex hex = new Hex();
			hex.setTerrain(defaultTerrain);
			hexs[i] = hex;
		}
	}

	public Hex getHex(int i, int j) {
		int index = toIndex(i, j);
		return hexs[index];
	}

	private int toIndex(int i, int j) {
		return j * width + i;
	}

	public void iterate(HexIterator iterator) {
		int length = hexs.length;
		for (int index = 0; index < length; index++) {
			int i = index % width;
			int j = index / width;
			Hex hex = hexs[index];
			iterator.forHex(i, j, hex);
		}
	}

	public void setHex(int i, int j, Hex hex) {
		int index = toIndex(i, j);
		hexs[index] = hex;
	}

	public Hex getHex(HXPoint hxp) {
		return getHex(hxp.i, hxp.j);
	}

	public void setFlag(int flag, boolean b) {
		for (Hex hex : hexs) {
			hex.setFlag(flag, b);
		}
	}

}
