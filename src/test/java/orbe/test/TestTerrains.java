/*
 * Created on Oct 9, 2006
 */
package orbe.test;

import java.awt.Color;
import java.util.List;

import junit.framework.TestCase;
import orbe.model.style.DefaultRepositoryHexTerrain;
import orbe.model.style.HexTerrain;
import orbe.model.style.RepositoryHexTerrain;

public class TestTerrains extends TestCase {

	public void testRepository() throws Exception {
		RepositoryHexTerrain rep = DefaultRepositoryHexTerrain.getDefaultRepository();
		assertNotNull(rep);

		List<HexTerrain> terrains = rep.getTerrains();
		assertNotNull(terrains);
		assertEquals(44, terrains.size());

		HexTerrain hexTerrain = rep.getTerrain(4);
		assertNotNull(hexTerrain);
		assertEquals(4, hexTerrain.getId());
		assertEquals("Montagnes", hexTerrain.getName());
		assertEquals(new Color(0x55, 0x55, 0x55), hexTerrain.getColor());
		assertNotNull(hexTerrain.getSymbol());
		assertEquals(13, hexTerrain.getSymbol().getId());
	}

}
