/*
 * Created on Sep 17, 2007
 */
package orbe.model.io;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractSimpleOXMLAdapter;
import orbe.hex.HXPoint2D;

import org.apache.commons.lang.StringUtils;

public class HXPoint2DOXMLAdapter extends AbstractSimpleOXMLAdapter<HXPoint2D> {

	@Override
	protected HXPoint2D adapt(String text, OXMLContext context) {
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
