/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class RepositoryHexTerrain implements Serializable {

	public static final int DEFAUL_TERRAIN_ID = 1;

	private static final long serialVersionUID = 1;

	private HashMap<Integer, HexTerrain> terrains = new HashMap<Integer, HexTerrain>();

	/**
	 * Liste de terrains vierges.
	 */
	public RepositoryHexTerrain() {
	}

	/**
	 * Duplication d'une liste de terrains.
	 */
	public RepositoryHexTerrain(RepositoryHexTerrain originalTerrains) {
		this();
		for (HexTerrain originalTerrain : originalTerrains.getTerrains()) {
			HexTerrain newTerrain = new DefaultHexTerrain(originalTerrain);
			addTerrain(newTerrain);
		}
	}

	public List<HexTerrain> getTerrains() {
		ArrayList<HexTerrain> list = new ArrayList<HexTerrain>(terrains.values());
		Collections.sort(list, new Comparator<HexTerrain>() {

			public int compare(HexTerrain arg0, HexTerrain arg1) {
				return arg0.getId() - arg1.getId();
			}

		});
		return list;
	}

	public void setTerrains(List<HexTerrain> list) {
		for (HexTerrain terrain : list) {
			terrains.put(terrain.getId(), terrain);
		}
	}

	public HexTerrain getTerrain(int id) {
		return terrains.get(id);
	}

	public void addTerrain(HexTerrain terrain) {
		terrains.put(terrain.getId(), terrain);
	}

	public HexTerrain getDefaultTerrain() {
		return getTerrain(DEFAUL_TERRAIN_ID);
	}

	public void removeTerrain(int id) {
		if (id != DEFAUL_TERRAIN_ID) {
			terrains.remove(id);
		}
	}

	public HexTerrain create() {
		// Creates an ID
		int id = getNextID();
		// Creates the terrain
		DefaultHexTerrain terrain = new DefaultHexTerrain();
		terrain.setId(id);
		terrain.setColor(Color.WHITE);
		terrain.setName("");
		terrain.setSymbol(null);
		// Ok
		addTerrain(terrain);
		return terrain;
	}

	private int getNextID() {
		int max = 0;
		for (HexTerrain terrain : terrains.values()) {
			int id = terrain.getId();
			max = Math.max(max, id);
		}
		return max + 1;
	}

}
