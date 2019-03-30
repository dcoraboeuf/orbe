/*
 * Created on Sep 18, 2007
 */
package orbe.model.io;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractTextOXMLInstanceFactory;
import orbe.hex.HXPoint;

import org.apache.commons.lang.StringUtils;

public class HXPointOXMLInstanceFactory extends AbstractTextOXMLInstanceFactory<HXPoint> {

	@Override
	protected HXPoint createInstance(String text, OXMLContext context) {
		if (StringUtils.isBlank(text)) {
			return null;
		} else {
			String[] tokens = StringUtils.split(text, ",");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			return new HXPoint(x, y);
		}
	}

}
