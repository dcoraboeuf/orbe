/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.tree;

import net.sf.doolin.gui.core.action.AbstractAction;
import net.sf.doolin.gui.tree.model.GUITreeNode;
import net.sf.doolin.gui.tree.support.TreeSupport;

/**
 * Utility ancestor for a tree context action.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractGUITreeAction.java,v 1.2 2007/08/01 15:58:45
 *          guinnessman Exp $
 * @param <T>
 *            Type of user object associated with the node.
 */
public abstract class AbstractGUITreeAction<T> extends AbstractAction implements GUITreeAction {

	private TreeSupport tree;

	private GUITreeNode node;

	/**
	 * @return Returns the node.
	 */
	public GUITreeNode getNode() {
		return node;
	}

	/**
	 * @param node
	 *            The node to set.
	 */
	public void setNode(GUITreeNode node) {
		this.node = node;
	}

	/**
	 * @return Returns the tree.
	 */
	public TreeSupport getTree() {
		return tree;
	}

	/**
	 * @param tree
	 *            The tree to set.
	 */
	public void setTree(TreeSupport tree) {
		this.tree = tree;
	}

	/**
	 * Gets the associated user object.
	 * 
	 * @return Object associated with the node.
	 */
	@SuppressWarnings("unchecked")
	public T getNodeObject() {
		return (T) node.getUserObject();
	}

}
