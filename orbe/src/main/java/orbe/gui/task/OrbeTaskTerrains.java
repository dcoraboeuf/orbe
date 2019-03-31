/*
 * Created on Nov 13, 2006
 */
package orbe.gui.task;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.model.OrbeMap;
import orbe.model.hex.Hex;
import orbe.model.hex.HexIterator;
import orbe.model.style.HexTerrain;
import orbe.model.style.RepositoryHexTerrain;

public class OrbeTaskTerrains extends OrbeTask {

	private RepositoryHexTerrain editedTerrains;

	public OrbeTaskTerrains(OrbeControler orbeControler, RepositoryHexTerrain editedTerrains) {
		super(orbeControler);
		this.editedTerrains = editedTerrains;
	}

	@Override
	public void process() {
		OrbeMap map = getControler().getContext().getMap();
		final RepositoryHexTerrain originalList = map.getSettings().getTerrains();
		// Sauvegarde de la liste
		RepositoryHexTerrain savedOriginalList = new RepositoryHexTerrain(originalList);
		// Modifications & ajouts
		for (HexTerrain editedTerrain : editedTerrains.getTerrains()) {
			int editedTerrainId = editedTerrain.getId();
			// Existing terrain ?
			HexTerrain originalTerrain = originalList.getTerrain(editedTerrainId);
			// Edited terrain ?
			if (originalTerrain != null) {
				originalTerrain.copy(editedTerrain);
			}
			// New terrain
			else {
				originalList.addTerrain(editedTerrain);
			}
		}
		// Suppressions
		for (HexTerrain originalTerrain : originalList.getTerrains()) {
			final int originalTerrainId = originalTerrain.getId();
			if (editedTerrains.getTerrain(originalTerrainId) == null) {
				// MAJ de la carte des hexs
				map.getHexMap().iterate(new HexIterator() {

					public void forHex(int i, int j, Hex hex) {
						HexTerrain hexTerrain = hex.getTerrain();
						if (hexTerrain.getId() == originalTerrainId) {
							hex.setTerrain(originalList.getDefaultTerrain());
						}
					}

				});
				// Suppression du référentiel
				originalList.removeTerrain(originalTerrainId);
			}
		}
		// Les settings ont chang�
		map.setSettings(map.getSettings());
		// La liste �dit�e devient la liste originale
		editedTerrains = savedOriginalList;
	}

	@Override
	public String getPresentationName() {
		return GUIStrings.get("OrbeTaskTerrains.name");
	}

}
