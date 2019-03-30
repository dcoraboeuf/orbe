/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import net.sf.doolin.util.ValueHandler;
import net.sf.doolin.util.xml.DigesterUtils;

public class RepositoryHexSymbol {

	/**
	 * Unique instance
	 */
	private static RepositoryHexSymbol instance = null;

	/**
	 * Get the instance
	 */
	public static RepositoryHexSymbol getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized RepositoryHexSymbol createInstance() {
		if (instance != null) {
			return instance;
		} else {
			RepositoryHexSymbol temp = new RepositoryHexSymbol();
			instance = temp;
			return instance;
		}
	}

	/**
	 * Liste des symboles
	 */
	private HashMap<Integer, HexSymbol> symboles;

	/**
	 * Initialization
	 */
	private RepositoryHexSymbol() {
		DigesterUtils digesterUtils = DigesterUtils.createNonValidatingDigester();
		digesterUtils.ruleObject("symbole", DefaultHexSymbol.class, "setValue");
		digesterUtils.ruleProperty("nom", "setName");
		digesterUtils.ruleProperty("image", "setImageId");

		URL resource = getClass().getResource("/orbe/resources/symbol/Symboles.xml");

		symboles = new HashMap<Integer, HexSymbol>();
		digesterUtils.parse(resource, new SymbolHandler());
	}

	public List<HexSymbol> getSymbols() {
		ArrayList<HexSymbol> list = new ArrayList<HexSymbol>(symboles.values());
		Collections.sort(list, new Comparator<HexSymbol>() {

			public int compare(HexSymbol o1, HexSymbol o2) {
				return o1.getId() - o2.getId();
			}

		});
		return list;
	}

	public HexSymbol getSymbol(int id) {
		return symboles.get(id);
	}

	public class SymbolHandler extends ValueHandler<HexSymbol> {

		@Override
		public void setValue(HexSymbol value) {
			super.setValue(value);
			symboles.put(value.getId(), value);
		}

	}

}
