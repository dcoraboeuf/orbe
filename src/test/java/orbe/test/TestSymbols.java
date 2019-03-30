/*
 * Created on Oct 9, 2006
 */
package orbe.test;

import java.util.List;

import orbe.model.style.HexSymbol;
import orbe.model.style.RepositoryHexSymbol;
import junit.framework.TestCase;

public class TestSymbols extends TestCase {

	public void testRepository() throws Exception {
		RepositoryHexSymbol rep = RepositoryHexSymbol.getInstance();
		assertNotNull(rep);

		List<HexSymbol> symbols = rep.getSymbols();
		assertNotNull(symbols);
		assertEquals(30, symbols.size());

		HexSymbol hexSymbol = rep.getSymbol(18);
		assertNotNull(hexSymbol);
		assertEquals(18, hexSymbol.getId());
		assertEquals("Forêt", hexSymbol.getName());
		// TODO Test de l'image du symbole
		// TODO Test du type de symbole
	}

}
