/*
 * Created on Oct 9, 2006
 */
package orbe.model.style;

import java.awt.Color;
import java.net.URL;

import net.sf.doolin.util.ValueHandler;
import net.sf.doolin.util.xml.DigesterUtils;
import orbe.model.ColorUtils;

import org.apache.commons.digester.Digester;

public class DefaultRepositoryHexTerrain {

	/**
	 * Unique instance
	 */
	private static RepositoryHexTerrain defaultRepository = null;

	/**
	 * Get the instance
	 */
	public static RepositoryHexTerrain getDefaultRepository() {
		if (defaultRepository != null) {
			return defaultRepository;
		} else {
			return createDefaultRepository();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized RepositoryHexTerrain createDefaultRepository() {
		if (defaultRepository != null) {
			return defaultRepository;
		} else {
			RepositoryHexTerrain temp = new RepositoryHexTerrain();

			DigesterUtils digesterUtils = DigesterUtils.createNonValidatingDigester();
			digesterUtils.ruleObject("terrain", HexTerrainHelper.class, "setValue");
			digesterUtils.ruleProperty("nom", "setName");
			digesterUtils.ruleProperty("couleur", "setColor");

			Digester digester = digesterUtils.getDigester();
			digester.addCallMethod("terrains/terrain/ref-symbole", "setSymbol", 1, new Class[] { Integer.class });
			digester.addCallParam("terrains/terrain/ref-symbole", 0, "id");

			URL resource = DefaultRepositoryHexTerrain.class.getResource("/orbe/resources/terrain/Terrains.xml");

			digesterUtils.parse(resource, new TerrainHandler(temp));

			defaultRepository = temp;
			return defaultRepository;
		}
	}

	public static class TerrainHandler extends ValueHandler<HexTerrainHelper> {

		private RepositoryHexTerrain repository;

		public TerrainHandler(RepositoryHexTerrain repository) {
			this.repository = repository;
		}

		@Override
		public void setValue(HexTerrainHelper value) {
			DefaultHexTerrain terrain = value.getTerrain();
			repository.addTerrain(terrain);
		}

	}

	public static class HexTerrainHelper {
		private int id;

		private String name;

		private Color color;

		private HexSymbol symbol;

		public void setColor(String value) {
			color = ColorUtils.fromHEX(value);
		}

		public DefaultHexTerrain getTerrain() {
			DefaultHexTerrain terrain = new DefaultHexTerrain();
			terrain.setId(id);
			terrain.setName(name);
			terrain.setColor(color);
			terrain.setSymbol(symbol);
			return terrain;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSymbol(int id) {
			symbol = RepositoryHexSymbol.getInstance().getSymbol(id);
		}

	}

	public static RepositoryHexTerrain createRepository() {
		RepositoryHexTerrain repository = new RepositoryHexTerrain();
		RepositoryHexTerrain defaultRepository = getDefaultRepository();
		for (HexTerrain terrain : defaultRepository.getTerrains()) {
			HexTerrain customTerrain = new DefaultHexTerrain(terrain);
			repository.addTerrain(customTerrain);
		}
		return repository;
	}

}
