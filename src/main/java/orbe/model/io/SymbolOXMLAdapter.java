/*
 * Created on Sep 17, 2007
 */
package orbe.model.io;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.OXMLAdapter;
import net.sf.doolin.util.xml.DOMUtils;
import orbe.model.style.HexSymbol;
import orbe.model.style.RepositoryHexSymbol;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SymbolOXMLAdapter implements OXMLAdapter {

	public Object adapt(Node currentNode, String path, OXMLContext context) {
		Node symbolNode = context.getXpath().selectSingleNode(currentNode, path);
		if (symbolNode == null) {
			return null;
		} else {
			int id = DOMUtils.getIntAttribute((Element) symbolNode, "id", true, 0);
			HexSymbol symbol = RepositoryHexSymbol.getInstance().getSymbol(id);
			return symbol;
		}
	}

}
