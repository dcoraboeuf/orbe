/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import orbe.model.style.HexTerrain;

/**
 * Change of the terrain.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageTerrain.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public class OrbeMessageTerrain extends OrbeSimpleMessage<HexTerrain> {

	public OrbeMessageTerrain(HexTerrain value) {
		super(value);
	}

}
