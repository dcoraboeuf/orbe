/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;

public interface HexTerrain {

	int getId();
	
	String getName();
	
	Color getColor();
	
	HexSymbol getSymbol();

	void copy(HexTerrain editedTerrain);
	
}

