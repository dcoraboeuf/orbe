package net.sf.doolin.util;

import net.sf.doolin.util.xml.DOMUtils;
import net.sf.doolin.util.xml.DigesterUtils;
import net.sf.doolin.util.xml.XMLUtils;
import net.sf.doolin.util.xml.XPathUtils;

/**
 * Codes for strings used inside the Doolin Utils module.
 * 
 * @author Damien Coraboeuf
 * @version $Id: StringCodes.java,v 1.3 2007/08/14 11:48:23 guinnessman Exp $
 */
public interface StringCodes {

	/**
	 * Used when a resource is not found. Its value is {@value}
	 * 
	 * @see IOUtils#readProperties(String)
	 * @see IOUtils#readTextResource(Class, String, String)
	 * @see Utils#getResource(Class, String)
	 */
	String STRING_ERROR_RESOURCE_NOT_FOUND = "doolin.utils.error.ResourceNotFound";

	/**
	 * Used when a resource cannot be read. Its value is {@value}
	 * 
	 * @see IOUtils#readTextResource(Class, String, String)
	 */
	String STRING_ERROR_RESOURCE_IO_ERROR = "doolin.utils.error.ResourceIOError";

	/**
	 * Used when some property file cannot be opened. Its value is {@value}
	 * 
	 * @see IOUtils#readProperties(String)
	 */
	String STRING_ERROR_CANNOT_OPEN_PROPERTIES = "doolin.utils.error.CannotOpenProperties";

	/**
	 * Used when a password cannot be encoded. Its value is {@value}
	 * 
	 * @see PasswordUtils#encodePassword(char[])
	 * @see PasswordUtils#passwordToBytes(char[])
	 */
	String STRING_ERROR_CANNOT_ENCODE_PASSWORD = "doolin.utils.error.CannotEncodePassword";

	/**
	 * Used when a base64-encoded string cannot be decoded Its value is {@value}
	 * 
	 * @see CodecUtils#decodeBase64(String)
	 */
	String STRING_ERROR_CANNOT_DECODE_BASE64 = "doolin.utils.error.CannotDecodeBase64";

	/**
	 * Used when a string cannot be encoded into base64. Its value is {@value}
	 * 
	 * @see CodecUtils#encodeBase64(String)
	 */
	String STRING_ERROR_CANNOT_ENCODE_BASE64 = "doolin.utils.error.CannotEncodeBase64";

	/**
	 * Used when a password cannot be decoded. Its value is {@value}
	 * 
	 * @see PasswordUtils#decodePassword(String)
	 */
	String STRING_ERROR_CANNOT_DECODE_PASSWORD = "doolin.utils.error.CannotDecodePassword";

	/**
	 * Used when a ZIP compression cannot be performed. Its value is {@value}
	 * 
	 * @see ZIPUtils#zip(byte[])
	 */
	String STRING_ERROR_CANNOT_ZIP = "doolin.utils.error.CannotZip";

	/**
	 * Used when a ZIP uncompression cannot be performed. Its value is {@value}
	 * 
	 * @see ZIPUtils#unzip(byte[])
	 */
	String STRING_ERROR_CANNOT_UNZIP = "doolin.utils.error.CannotUnzip";

	/**
	 * Used when a HTTP date header cannnot be read. Its value is {@value}
	 * 
	 * @see RemoteStatus#RemoteStatus(java.net.URL)
	 */
	String STRING_REMOTESTATUS_DATE_PARSING_ERROR = "doolin.utils.RemoteStatus.DateParsingError";

	/**
	 * Used when a remote URL contains an HTTP error status code. Its value is
	 * {@value}
	 * 
	 * @see RemoteStatus#RemoteStatus(java.net.URL)
	 */
	String STRING_REMOTESTATUS_STATUS = "doolin.utils.RemoteStatus.Status";

	/**
	 * Used when a remote URL cannot be read. Its value is {@value}
	 * 
	 * @see RemoteStatus#RemoteStatus(java.net.URL)
	 */
	String STRING_REMOTESTATUS_IO = "doolin.utils.RemoteStatus.IO";

	/**
	 * Used when a new instance cannot be created from a class name. Its value
	 * is {@value}.
	 * 
	 * @see Utils#newInstance(Class)
	 * @see Utils#newInstance(String)
	 * @see Utils#newInstance(String, ParameterSet)
	 */
	String STRING_UTILS_NEW_INSTANCE_ERROR = "doolin.utils.Utils.NewInstanceError";

	/**
	 * Used when a class cannot be loaded from its value. Its value is {@value}.
	 * 
	 * @see Utils#forClass(String)
	 */
	String STRING_UTILS_FOR_CLASS_ERROR = "doolin.utils.Utils.ForClassError";

	/**
	 * Used when properties cannot be set on an object. Its value is {@value}.
	 * 
	 * @see Utils#setProperties(Object, ParameterSet)
	 */
	String STRING_UTILS_SET_PROPERTIES_ERROR = "doolin.utils.Utils.SetPropertiesError";

	/**
	 * Used when an integer element or attribute cannot be parsed from a DOM.
	 * Its value is {@value}.
	 * 
	 * @see DOMUtils#getInt(org.w3c.dom.Element, String, boolean, int)
	 * @see DOMUtils#getIntAttribute(org.w3c.dom.Element, String, boolean, int)
	 */
	String STRING_UTILS_XML_DOM_WRONG_INT_FORMAT = "doolin.utils.xml.DOMUtils.WrongIntFormat";

	/**
	 * Used when an element or attribute is mandatort in a DOM. Its value is
	 * {@value}.
	 * 
	 * @see DOMUtils#getInt(org.w3c.dom.Element, String, boolean, int)
	 * @see DOMUtils#getIntAttribute(org.w3c.dom.Element, String, boolean, int)
	 * @see DOMUtils#getBoolean(org.w3c.dom.Element, String, boolean, boolean)
	 */
	String STRING_UTILS_XML_DOM_MANDATORY = "doolin.utils.xml.DOMUtils.Mandatory";

	/**
	 * Used when a JAXP parser cannot be configured. Its value is {@value}.
	 * 
	 * @see XMLUtils#createSAXValidatingParser(java.net.URL)
	 * @see XMLUtils#createSAXNonValidatingParser()
	 */
	String STRING_XML_CANNOT_CONFIGURE_PARSER = "doolin.utils.xml.CannotConfigureParser";

	/**
	 * Error while reading an XML document.
	 * 
	 * @see DigesterUtils#parse(java.io.File, Object)
	 * @see DigesterUtils#parse(org.xml.sax.InputSource, Object)
	 * @see DigesterUtils#parse(java.net.URL, Object)
	 */
	String STRING_XML_IO_ERROR = "doolin.utils.xml.IOError";

	/**
	 * Error while parsing an XML document.
	 * 
	 * @see DigesterUtils#parse(org.xml.sax.InputSource, Object)
	 */
	String STRING_XML_PARSING_ERROR = "doolin.utils.xml.ParsingError";

	/**
	 * Error while evaluating an XPath expression.
	 * 
	 * @see XPathUtils
	 */
	String XPATHUTIL_XPATH_ERROR = "doolin.utils.XPathUtil.XPathError";
	
	/**
	 * Error when a method cannot be called by introspection.
	 */
	String STRING_UTILS_METHODCALL_ERROR = "doolin.utils.error.MethodCall";
}
