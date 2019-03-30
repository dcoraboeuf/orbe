/*
 * Created on 2 mai 2006
 */
package net.sf.doolin.util.xml;

import java.util.ArrayList;
import java.util.List;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.StringCodes;
import net.sf.doolin.util.Utils;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Methods for exploring a DOM tree. This class, for performances reasons, does
 * not use any XPath expression.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DOMUtils.java,v 1.4 2007/07/31 15:32:50 guinnessman Exp $
 */
public class DOMUtils {

	/**
	 * List of childrens with a given tag name.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param namespaceURI
	 *            Namespace URI for the child name
	 * @param name
	 *            Name of the children to collect
	 * @return List of children (not <code>null</code> but can be empty)
	 */
	public static List<Element> getElements(Element eParent, String namespaceURI, String name) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				if (StringUtils.equals(namespaceURI, eChild.getNamespaceURI()) && StringUtils.equals(name, eChild.getLocalName())) {
					result.add(eChild);
				}
			}
		}
		return result;
	}

	/**
	 * List of childrens with a given tag name and namespace
	 * 
	 * @param eParent
	 *            Parent element
	 * @param name
	 *            Name of the children to collect
	 * @return List of children (not <code>null</code> but can be empty)
	 */
	public static List<Element> getElements(Element eParent, String name) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				if (isNameEqual(eChild, name)) {
					result.add(eChild);
				}
			}
		}
		return result;
	}

	/**
	 * List of all child elements
	 * 
	 * @param eParent
	 *            Parent element
	 * @return List of children (not <code>null</code> but can be empty)
	 */
	public static List<Element> getElements(Element eParent) {
		ArrayList<Element> result = new ArrayList<Element>();
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				result.add(eChild);
			}
		}
		return result;
	}

	/**
	 * Get the text from an child under an element.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param childName
	 *            Name of the child element (may be qualified)
	 * @return Text of the child element or <code>null</code> if the child
	 *         element does not exist or if it doesn't contain any text.
	 */
	public static String getText(Element eParent, String childName) {
		Element eChild = getElement(eParent, childName);
		if (eChild != null) {
			return getText(eChild);
		} else {
			return null;
		}
	}

	/**
	 * Get the text from an child under an element.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param namespaceURI
	 *            Namespace URI for the child name
	 * @param childName
	 *            Name of the child element (not qualified)
	 * @return Text of the child element or <code>null</code> if the child
	 *         element does not exist or if it doesn't contain any text.
	 */
	public static String getText(Element eParent, String namespaceURI, String childName) {
		Element eChild = getElement(eParent, namespaceURI, childName);
		if (eChild != null) {
			return getText(eChild);
		} else {
			return null;
		}
	}

	/**
	 * Get the text contained by an element.
	 * 
	 * @param eNode
	 *            Element to get the text from.
	 * @return Contained text. <code>null</code> if no text has been found.
	 */
	public static String getText(Element eNode) {
		return eNode.getTextContent();
	}

	/**
	 * Get a child element. If several elements exist with the same name, only
	 * the first one is returned.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param childName
	 *            Name of the child element to get (may be qualified)
	 * @return Child element or <code>null</code> if not found.
	 */
	public static Element getElement(Element eParent, String childName) {
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				if (isNameEqual(eChild, childName)) {
					return eChild;
				}
			}
		}
		return null;
	}

	/**
	 * This method compares the name (either local name or tag name) of an
	 * element
	 * 
	 * @param e
	 *            Element
	 * @param name
	 *            Name compared to
	 * @return Result of the comparison
	 */
	public static boolean isNameEqual(Element e, String name) {
		return StringUtils.equals(name, e.getLocalName()) || StringUtils.equals(name, e.getTagName());
	}

	/**
	 * Get a child element. If several elements exist with the same name, only
	 * the first one is returned.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param namespaceURI
	 *            Namespace URI for the child name
	 * @param childName
	 *            Name of the child element to get (may be qualified)
	 * @return Child element or <code>null</code> if not found.
	 */
	public static Element getElement(Element eParent, String namespaceURI, String childName) {
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				if (StringUtils.equals(namespaceURI, eChild.getNamespaceURI()) && StringUtils.equals(childName, eChild.getLocalName())) {
					return eChild;
				}
			}
		}
		return null;
	}

	/**
	 * Get the text of an attribute.
	 * 
	 * @param eNode
	 *            Parent element
	 * @param attName
	 *            Attribute name
	 * @param defaultValue
	 *            Default value to use when the value is not defined
	 * @return Text of the attribute.
	 */
	public static String getAttribute(Element eNode, String attName, String defaultValue) {
		return getAttribute(eNode, attName, false, defaultValue);
	}

	/**
	 * Get the text of an attribute.
	 * 
	 * @param eNode
	 *            Parent element
	 * @param attName
	 *            Attribute name
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value to use when the value is not defined
	 * @return Text of the attribute.
	 */
	public static String getAttribute(Element eNode, String attName, boolean mandatory, String defaultValue) {
		Attr att = eNode.getAttributeNode(attName);
		if (att == null) {
			if (mandatory) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, attName, getXPath(eNode));
			} else {
				return defaultValue;
			}
		} else {
			return att.getValue();
		}
	}

	/**
	 * Get a type attribute from an element.
	 * 
	 * @param eNode
	 *            Element to get the attribute from
	 * @param attName
	 *            Name of the attribute to get
	 * @param defaultClass
	 *            Default class to return if the attribute is not defined
	 * @return A class
	 */
	public static Class getClassAttribute(Element eNode, String attName, Class defaultClass) {
		String type = getAttribute(eNode, attName, null);
		if (type != null) {
			return Utils.forClass(type);
		} else {
			return defaultClass;
		}
	}

	/**
	 * Get the text of an attribute as an integer.
	 * 
	 * @param eNode
	 *            Parent element
	 * @param attName
	 *            Attribute name
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value to use when the value is not defined (and not
	 *            <code>mandatory</code>).
	 * @return Text of the attribute as an integer.
	 */
	public static int getIntAttribute(Element eNode, String attName, boolean mandatory, int defaultValue) {
		Attr att = eNode.getAttributeNode(attName);
		if (att == null) {
			if (mandatory) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, "@" + attName, eNode.getTagName());
			} else {
				return defaultValue;
			}
		} else {
			String attValue = att.getValue();
			try {
				return Integer.parseInt(attValue);
			} catch (NumberFormatException ex) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_WRONG_INT_FORMAT, "@" + attName, attValue);
			}
		}
	}

	/**
	 * Get the text of an attribute as a boolean.
	 * 
	 * @param eNode
	 *            Parent element
	 * @param attName
	 *            Attribute name
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value to use when the value is not defined (and not
	 *            <code>mandatory</code>).
	 * @return Text of the attribute as a boolean.
	 */
	public static boolean getBooleanAttribute(Element eNode, String attName, boolean mandatory, boolean defaultValue) {
		Attr att = eNode.getAttributeNode(attName);
		if (att == null) {
			if (mandatory) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, "@" + attName, eNode.getTagName());
			} else {
				return defaultValue;
			}
		} else {
			String attValue = att.getValue();
			return Boolean.valueOf(attValue).booleanValue();
		}
	}

	/**
	 * Get the first element child of an element.
	 * 
	 * @param eParent
	 *            Parent element
	 * @return Child element or <code>null</code> if there is no child
	 *         element.
	 */
	public static Element getFirstChildElement(Element eParent) {
		NodeList childList = eParent.getChildNodes();
		int count = childList.getLength();
		for (int i = 0; i < count; i++) {
			Node childNode = childList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eChild = (Element) childNode;
				return eChild;
			}
		}
		return null;
	}

	/**
	 * Get the text of a child element as an integer.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param childName
	 *            Child element name
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value to use when the value is not defined (and not
	 *            <code>mandatory</code>).
	 * @return Text of a child element as an integer.
	 */
	public static int getInt(Element eParent, String childName, boolean mandatory, int defaultValue) {
		Element eChild = getElement(eParent, childName);
		if (eChild == null) {
			if (mandatory) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, eParent.getTagName(), childName);
			} else {
				return defaultValue;
			}
		} else {
			String text = getText(eChild);
			if (StringUtils.isBlank(text)) {
				if (mandatory) {
					throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, eParent.getTagName(), childName);
				} else {
					return defaultValue;
				}
			} else {
				try {
					return Integer.parseInt(text);
				} catch (NumberFormatException ex) {
					throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_WRONG_INT_FORMAT, childName, text);
				}
			}
		}
	}

	/**
	 * Get the text of a child element as a boolean.
	 * 
	 * @param eParent
	 *            Parent element
	 * @param childName
	 *            Child element name
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value to use when the value is not defined (and not
	 *            <code>mandatory</code>).
	 * @return Text of a child element as a boolean.
	 */
	public static boolean getBoolean(Element eParent, String childName, boolean mandatory, boolean defaultValue) {
		Element eChild = getElement(eParent, childName);
		if (eChild == null) {
			if (mandatory) {
				throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, childName, eParent.getTagName());
			} else {
				return defaultValue;
			}
		} else {
			String text = getText(eChild);
			if (StringUtils.isBlank(text)) {
				if (mandatory) {
					throw new CodeException(StringCodes.STRING_UTILS_XML_DOM_MANDATORY, childName, eParent.getTagName());
				} else {
					return defaultValue;
				}
			} else {
				return Boolean.valueOf(text);
			}
		}
	}

	/**
	 * Gets the XPath for a node
	 * 
	 * @param node
	 *            Node to get the XPath to.
	 * @return XPath from the document root to the given node
	 */
	public static String getXPath(Node node) {
		Node parent = node.getParentNode();
		if (parent == null) {
			return "";
		} else if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
			return getXPath(node.getParentNode()) + "/@" + node.getNodeName();
		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			// Name of this element
			String name = e.getTagName();
			// Parent
			Node parentNode = e.getParentNode();
			// Path of the parent
			String parentPath = getXPath(parentNode);
			// Index indication
			String index = "";
			if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eParent = (Element) parentNode;
				// Get the list of elements of same name
				List sameElements = getElements(eParent, name);
				// Only one element
				if (sameElements.size() <= 1) {
					index = "";
				}
				// Several elements, get the position
				else {
					int position = sameElements.indexOf(e);
					index = "[" + position + "]";
				}
			}
			// Concatenation
			return parentPath + "/" + name + index;
		} else {
			return null;
		}
	}
}
