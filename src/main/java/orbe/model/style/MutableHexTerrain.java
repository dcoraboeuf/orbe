/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;

public interface MutableHexTerrain extends HexTerrain {

	void setName(String name);

	void setColor(Color color);

	void setSymbol(HexSymbol symbol);

}
