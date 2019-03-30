/*
 * Created on Sep 18, 2007
 */
package orbe.model.io;

import org.apache.commons.lang.StringUtils;

import orbe.hex.HXPoint2D;
import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractTextOXMLInstanceFactory;

public class HXPoint2DOXMLInstanceFactory extends AbstractTextOXMLInstanceFactory<HXPoint2D> {

	@Override
	protected HXPoint2D createInstance(String text, OXMLContext context) {
		if (StringUtils.isBlank(text)) {
			return null;
		} else {
			String[] tokens = StringUtils.split(text, ",");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			return new HXPoint2D(x, y);
		}
	}

}
