/*
 * Created on Sep 18, 2007
 */
package orbe.model.io;

import java.util.StringTokenizer;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.AbstractTextOXMLInstanceFactory;
import orbe.hex.HXCorner;

public class HXCornerOXMLInstanceFactory extends AbstractTextOXMLInstanceFactory<HXCorner> {

	@Override
	protected HXCorner createInstance(String text, OXMLContext context) {
		StringTokenizer st = new StringTokenizer(text, ",@");
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		int corner = Integer.parseInt(st.nextToken());
		return new HXCorner(i, j, corner);
	}

}
