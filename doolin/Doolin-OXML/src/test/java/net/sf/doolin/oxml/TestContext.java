/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml;

import java.net.URL;
import java.util.EmptyStackException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import net.sf.doolin.util.xml.XPathUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Tests of the {@link OXMLContext} class.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class TestContext extends TestCase {

	private OXMLContext<Object> context;

	private Document sourceDocument;

	/**
	 * Setups the test by creating the context.
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Gets the source document
		sourceDocument = createSourceDocument();
		// Creates the context
		context = new OXMLContext<Object>(sourceDocument);
	}

	/**
	 * Parses the sample source document
	 * 
	 * @return Sample source document
	 * @throws Exception
	 *             If a parsing error occurs
	 */
	protected Document createSourceDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		URL url = getClass().getResource("SourceDocument.xml");
		return builder.parse(url.toString());
	}

	/**
	 * @see OXMLContext#getNodeList(String)
	 */
	public void testGetNodeList() {
		NodeList nodeList = context.getNodeList("/contactList/contact");
		assertNotNull(nodeList);
		assertEquals(1, nodeList.getLength());
	}

	/**
	 * @see OXMLContext#getTargetRoot()
	 * @see OXMLContext#setRoot(Object)
	 */
	public void testTargetRoot() {
		Object targetRoot = context.getTargetRoot();
		assertNull(targetRoot);
		Object myRoot = new Object();
		context.setRoot(myRoot);
		targetRoot = context.getTargetRoot();
		assertEquals(myRoot, targetRoot);
	}

	/**
	 * @see OXMLContext#getXpath()
	 */
	public void testGetXPath() {
		XPathUtils xpath = context.getXpath();
		assertNotNull(xpath);
	}

	/**
	 * @see OXMLContext#instanceGet(String)
	 * @see OXMLContext#instancePut(String, Object)
	 */
	public void testGetPutInstances() {
		assertNull(context.instanceGet("id"));
		Object myInstance = new Object();
		context.instancePut("id", myInstance);
		Object instance = context.instanceGet("id");
		assertNotNull(instance);
		assertEquals(myInstance, instance);
	}

	/**
	 * @see OXMLContext#instancePeek()
	 * @see OXMLContext#instancePop()
	 * @see OXMLContext#instancePush(Object)
	 */
	public void testInstanceStack() {
		Object instance;
		try {
			instance = context.instancePeek();
			fail("Instance stack is empty");
		} catch (EmptyStackException ex) {
			// Ok
		}
		Object myInstance = new Object();
		context.instancePush(myInstance);
		instance = context.instancePeek();
		assertEquals(myInstance, instance);
		instance = context.instancePop();
		assertEquals(myInstance, instance);
		try {
			instance = context.instancePeek();
			fail("Instance stack is empty");
		} catch (EmptyStackException ex) {
			// Ok
		}
	}

	/**
	 * @see OXMLContext#nodePeek()
	 * @see OXMLContext#nodePop()
	 * @see OXMLContext#nodePush(org.w3c.dom.Node)
	 */
	public void testNodeStack() {
		Node instance = context.nodePeek();
		assertEquals(sourceDocument, instance);
		Node myInstance = sourceDocument.getDocumentElement();
		context.nodePush(myInstance);
		instance = context.nodePeek();
		assertEquals(myInstance, instance);
		instance = context.nodePop();
		assertEquals(myInstance, instance);
		instance = context.nodePeek();
		assertEquals(sourceDocument, instance);
	}

}
