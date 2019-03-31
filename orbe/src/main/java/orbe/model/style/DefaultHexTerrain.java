/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;
import java.io.Serializable;

import orbe.model.ColorUtils;

public class DefaultHexTerrain implements MutableHexTerrain, Serializable {

	private static final long serialVersionUID = 1;

	private int id;

	private String name;

	private Color color;

	private transient HexSymbol symbol;

	public DefaultHexTerrain() {
	}

	public DefaultHexTerrain(HexTerrain terrain) {
		this.id = terrain.getId();
		this.name = terrain.getName();
		this.color = terrain.getColor();
		this.symbol = terrain.getSymbol();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HexSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(HexSymbol symbol) {
		this.symbol = symbol;
	}

	public void copy(HexTerrain editedTerrain) {
		id = editedTerrain.getId();
		name = editedTerrain.getName();
		color = editedTerrain.getColor();
		symbol = editedTerrain.getSymbol();
	}

	@Override
	public String toString() {
		return new StringBuffer().append("(").append(id).append(",").append(name).append(",").append(ColorUtils.toHEX(color)).append(",").append(symbol).append(")").toString();
	}

}
