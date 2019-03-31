/*
 * Created on Oct 23, 2006
 */
package orbe.model.hex;

import java.awt.Color;

import orbe.model.Flagged;
import orbe.model.style.HexSymbol;
import orbe.model.style.HexTerrain;

/**
 * Hexagone et son contenu.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Hex.java,v 1.5 2006/11/17 16:55:59 guinnessman Exp $
 */
public class Hex extends Flagged {

	/**
	 * Terrain
	 */
	private HexTerrain terrain;

	/**
	 * Symbôle spécifique
	 */
	private HexSymbol symbol;

	public HexSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(HexSymbol symbol) {
		this.symbol = symbol;
	}

	public HexTerrain getTerrain() {
		return terrain;
	}

	public void setTerrain(HexTerrain terrain) {
		this.terrain = terrain;
	}

	public Color getFillColor() {
		return getTerrain().getColor();
	}

	public HexSymbol getFillSymbol() {
		if (symbol != null) {
			return symbol;
		} else {
			return terrain.getSymbol();
		}
	}

	public void copy(Hex hex) {
		this.symbol = hex.symbol;
		this.terrain = hex.terrain;
	}

}
