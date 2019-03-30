/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import orbe.model.style.HexSymbol;

/**
 * Change of the symbol.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageSymbol.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public class OrbeMessageSymbol extends OrbeSimpleMessage<HexSymbol> {

	public OrbeMessageSymbol(HexSymbol value) {
		super(value);
	}

}
