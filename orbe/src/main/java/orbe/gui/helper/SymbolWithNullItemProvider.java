/*
 * Created on Sep 19, 2007
 */
package orbe.gui.helper;

import java.util.ArrayList;
import java.util.List;

import orbe.model.style.HexSymbol;
import orbe.model.style.RepositoryHexSymbol;

import net.sf.doolin.gui.field.helper.ItemProvider;

public class SymbolWithNullItemProvider implements ItemProvider {

	public List getItems() {
		ArrayList<HexSymbol> list = new ArrayList<HexSymbol>();
		list.add(null);
		list.addAll(RepositoryHexSymbol.getInstance().getSymbols());
		return list;
	}

}
