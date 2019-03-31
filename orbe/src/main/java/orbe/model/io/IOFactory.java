/*
 * Created on 10 oct. 06
 */
package orbe.model.io;

import orbe.model.style.DefaultHexTerrain;
import orbe.model.style.HexTerrain;

public class IOFactory {

	public static HexTerrain createTerrain() {
		return new DefaultHexTerrain();
	}

}
