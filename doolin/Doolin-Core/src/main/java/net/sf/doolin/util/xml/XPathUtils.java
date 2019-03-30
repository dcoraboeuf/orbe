/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.util.xml;

import javax.xml.transform.TransformerException;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.StringCodes;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;

/**
 * XPath utility class. It caches the XPath context so the XPath requests can be
 * more efficient.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class XPathUtils {

	/**
	 * Context used for XPath evaluations.
	 */
	private XPathContext xpathContext;

	/**
	 * Constructor.
	 */
	public XPathUtils() {
		xpathContext = new XPathContext(this);
	}

	/**
	 * Evaluates a boolean from an XPath expression.
	 * 
	 * @param root
	 *            Root node where to evaluate the XPath from.
	 * @param path
	 *            XPath expression.
	 * @return Result of the expression.
	 */
	public boolean evaluateBoolean(Node root, String path) {
		try {
			XPath xpath = new XPath(path, null, null, XPath.SELECT);
			XObject result = xpath.execute(xpathContext, root, null);
			boolean value = result.bool();
			return value;
		} catch (TransformerException e) {
			throw new CodeException("be.fgov.kszbcss.util.xml.XPathUtil.XPathError", e, new Object[] { path, e });
		}
	}

	/**
	 * Evaluates a string from an XPath expression.
	 * 
	 * @param root
	 *            Root node where to evaluate the XPath from.
	 * @param path
	 *            XPath expression.
	 * @return Result of the expression.
	 */
	public String evaluateString(Node root, String path) {
		try {
			XPath xpath = new XPath(path, null, null, XPath.SELECT);
			XObject result = xpath.execute(xpathContext, root, null);
			String value = result.str();
			return value;
		} catch (TransformerException e) {
			throw new CodeException(StringCodes.XPATHUTIL_XPATH_ERROR, e, new Object[] { path, e });
		}
	}

	/**
	 * Get a list of node using an XPath expression from a root node.
	 * 
	 * @param root
	 *            Root node where to evaluate the XPath from.
	 * @param path
	 *            XPath expression.
	 * @return List of nodes
	 */
	public NodeList selectNodeList(Node root, String path) {
		try {
			XPath xpath = new XPath(path, null, null, XPath.SELECT);
			XObject result = xpath.execute(xpathContext, root, null);
			return result.nodelist();
		} catch (TransformerException e) {
			throw new CodeException(StringCodes.XPATHUTIL_XPATH_ERROR, e, new Object[] { path, e });
		}
	}

	/**
	 * Get a single node using an XPath expression from a root node.
	 * 
	 * @param root
	 *            Root node where to evaluate the XPath from.
	 * @param path
	 *            XPath expression.
	 * @return List of nodes
	 */
	public Node selectSingleNode(Node root, String path) {
		try {
			XPath xpath = new XPath(path, null, null, XPath.SELECT);
			XObject result = xpath.execute(xpathContext, root, null);
			return result.nodeset().nextNode();
		} catch (TransformerException e) {
			throw new CodeException(StringCodes.XPATHUTIL_XPATH_ERROR, e, new Object[] { path, e });
		}
	}
}
