/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree.model;

import java.util.List;

import javax.swing.tree.DefaultTreeModel;

import net.sf.doolin.gui.tree.Node;

import org.apache.commons.lang.StringUtils;

/**
 * Model for the Doolin GUI tree implementation.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUITreeModel.java,v 1.1 2007/08/01 15:58:48 guinnessman Exp $
 */
public class GUITreeModel extends DefaultTreeModel {

	private List<Node> nodes;

	private Object root;

	/**
	 * Default constructor. The resulting model contains no node.
	 */
	public GUITreeModel() {
		super(null);
	}

	/**
	 * Constructor from a list of node definitions.
	 * 
	 * @param nodes
	 *            List of node definitions
	 * @param root
	 *            Object associated with the root
	 */
	public GUITreeModel(List<Node> nodes, Object root) {
		super(null);
		this.nodes = nodes;
		this.root = root;
		init();
	}

	/**
	 * Initializes the model
	 */
	protected void init() {
		// Looks for the root definition
		Node rootNode = getRootNode();
		// Creates the root
		GUITreeNode rootTreeNode = new GUITreeNode(this, rootNode, root);
		// Set as root
		setRoot(rootTreeNode);
	}

	/**
	 * Looks for the root node definition. The root node definition is the node
	 * definition whose property <code>root</code> is set to <code>true</code>.
	 * 
	 * @return Root node definition
	 */
	protected Node getRootNode() {
		for (Node node : nodes) {
			if (node.isRoot()) {
				return node;
			}
		}
		throw new RuntimeException("Cannot find any root node.");
	}

	/**
	 * Looks for a node definition. The node definition to lok for is the one
	 * whose property <code>id</code> is equal to the given id.
	 * 
	 * @param id
	 *            Searched id
	 * @return Node definition
	 */
	public Node getNode(String id) {
		for (Node node : nodes) {
			if (StringUtils.equals(id, node.getId())) {
				return node;
			}
		}
		throw new RuntimeException("Cannot find any node with id = " + id);
	}

	/**
	 * Notifies that a node must be added in the model
	 * 
	 * @param node
	 *            Parent node
	 * @param id
	 *            ID of the node definition
	 * @param item
	 *            User object
	 * @return Node
	 */
	public GUITreeNode nodeAdded(GUITreeNode node, String id, Object item) {
		// Get the node definition
		Node defNode = getNode(id);
		// Creates the node
		GUITreeNode childNode = new GUITreeNode(this, defNode, item);
		// Adds it to the parent
		node.add(childNode);
		int index = getIndexOfChild(node, childNode);
		// Notifies the tree
		fireTreeNodesInserted(this, getPathToRoot(node), new int[] { index }, new Object[] { childNode });
		// Ok
		return childNode;
	}

	/**
	 * Notifies that the node must be deleted from the model
	 * 
	 * @param node
	 *            Node to be deleted
	 */
	public void nodeDelete(GUITreeNode node) {
		GUITreeNode parentNode = (GUITreeNode) node.getParent();
		int index = getIndexOfChild(parentNode, node);
		node.removeFromParent();
		fireTreeNodesRemoved(this, getPathToRoot(parentNode), new int[] { index }, new Object[] { node });
	}

}
