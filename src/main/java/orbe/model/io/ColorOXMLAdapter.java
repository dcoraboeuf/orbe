/*
 * Created on Sep 17, 2007
 */
package orbe.model.io;

import java.awt.Color;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractSimpleOXMLAdapter;
import net.sf.doolin.util.CodeException;
import orbe.model.ColorUtils;
import orbe.model.IErrors;

import org.apache.commons.lang.StringUtils;

public class ColorOXMLAdapter extends AbstractSimpleOXMLAdapter<Color> {

	@Override
	protected Color adapt(String value, OXMLContext context) {
		if (StringUtils.isBlank(value)) {
			throw new CodeException(IErrors.IO_XML_COLOR_BLANK);
		} else {
			Color color = ColorUtils.fromHEX(value);
			return color;
		}
	}

}
