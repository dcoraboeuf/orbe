/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import net.sf.doolin.util.xml.XPathUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Execution context
 * 
 * @param <T>
 *            Expected type of the root object
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class OXMLContext<T> {

	private Document dom;

	private T root;

	private XPathUtils xpath;

	private Stack<Node> nodeStack;

	private Map<String, Object> instanceMap;

	private Stack<Object> instanceStack;

	/**
	 * Constructor.
	 * 
	 * @param document
	 *            Source document
	 */
	public OXMLContext(Document document) {
		dom = document;
		xpath = new XPathUtils();
		nodeStack = new Stack<Node>();
		nodeStack.push(dom);
		instanceMap = new HashMap<String, Object>();
		instanceStack = new Stack<Object>();
	}

	/**
	 * Registers an instance.
	 * 
	 * @param id
	 *            ID for the instance
	 * @param instance
	 *            Instance to register
	 */
	public void instancePut(String id, Object instance) {
		instanceMap.put(id, instance);
	}

	/**
	 * Gets a registered instance.
	 * 
	 * @param id
	 *            ID for the instance
	 * @return Registered instance or <code>null</code> if not registered.
	 */
	public Object instanceGet(String id) {
		return instanceMap.get(id);
	}

	/**
	 * Returns the generated target.
	 * 
	 * @return Target
	 */
	public T getTargetRoot() {
		return root;
	}

	/**
	 * Returns a list of nodes from the current node using an XPath expression.
	 * 
	 * @param path
	 *            XPath expression
	 * @return Node list
	 * @see XPathUtils#selectNodeList(org.w3c.dom.Node, String)
	 */
	public NodeList getNodeList(String path) {
		Node node = nodeStack.peek();
		return xpath.selectNodeList(node, path);
	}

	/**
	 * Returns the XPath utility.
	 * 
	 * @return XPath utility.
	 */
	public XPathUtils getXpath() {
		return xpath;
	}

	/**
	 * Pushes a node onto the stack.
	 * 
	 * @param node
	 *            Node to push
	 * @see Stack#push(Object)
	 */
	public void nodePush(Node node) {
		nodeStack.push(node);
	}

	/**
	 * Pops a node from the stack
	 * 
	 * @return Popped node
	 * @see Stack#pop()
	 */
	public Node nodePop() {
		return nodeStack.pop();
	}

	/**
	 * Peeks a node from the stack
	 * 
	 * @return Top node
	 * @see Stack#peek()
	 */
	public Node nodePeek() {
		return nodeStack.peek();
	}

	/**
	 * Pushes an instance onto the stack.
	 * 
	 * @param instance
	 *            Instance to push
	 * @see Stack#push(Object)
	 */
	public void instancePush(Object instance) {
		instanceStack.push(instance);
	}

	/**
	 * Pops an instance from the stack
	 * 
	 * @return Popped instance
	 * @see Stack#pop()
	 */
	public Object instancePop() {
		return instanceStack.pop();
	}

	/**
	 * Peeks an instance from the stack
	 * 
	 * @return Top instance
	 * @see Stack#peek()
	 */
	public Object instancePeek() {
		return instanceStack.peek();
	}

	/**
	 * Sets an instance as being the root.
	 * 
	 * @param instance
	 *            Instance
	 */
	public void setRoot(T instance) {
		if (root == null) {
			root = instance;
		} else {
			throw new IllegalStateException("The root instance has already been set.");
		}
	}

}
