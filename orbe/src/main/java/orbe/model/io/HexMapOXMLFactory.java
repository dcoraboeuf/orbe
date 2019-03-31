/*
 * Created on Sep 18, 2007
 */
package orbe.model.io;

import orbe.model.hex.HexMap;
import orbe.model.impl.DefaultOrbeMap;
import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractTextOXMLInstanceFactory;

public class HexMapOXMLFactory extends AbstractTextOXMLInstanceFactory<HexMap> {

	@Override
	protected HexMap createInstance(String text, OXMLContext context) {
		DefaultOrbeMap orbeMap = (DefaultOrbeMap) context.instanceGet("Map");
		HexMap map = orbeMap.createHexMap();
		orbeMap.setHexMap(map);
		map.iterate(new HexInputIterator(text, orbeMap));
		return map;
	}

}
