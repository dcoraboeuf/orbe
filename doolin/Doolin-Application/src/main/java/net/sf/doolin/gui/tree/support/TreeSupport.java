/*
 * Created on Aug 1, 2007
 */
package net.sf.doolin.gui.tree.support;

import java.util.List;

import javax.swing.tree.TreeNode;

import net.sf.doolin.gui.field.support.FieldSupport;
import net.sf.doolin.gui.tree.FieldTree;
import net.sf.doolin.gui.tree.Node;
import net.sf.doolin.gui.tree.model.GUITreeModel;
import net.sf.doolin.gui.tree.model.GUITreeNode;

/**
 * Field tree support.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TreeSupport.java,v 1.1 2007/08/01 15:58:47 guinnessman Exp $
 */
public interface TreeSupport extends FieldSupport<FieldTree> {

	/**
	 * Gets the current selected node
	 * 
	 * @return Node or <code>null</code> if there is no selection.
	 */
	GUITreeNode getSelectedNode();

	/**
	 * Deletes a node from the tree
	 * 
	 * @param node
	 *            Node to be deleted
	 */
	void nodeDeleted(GUITreeNode node);

	/**
	 * Notifies that a node must be added in the model
	 * 
	 * @param node
	 *            Parent none
	 * @param id
	 *            ID of the node definition
	 * @param item
	 *            User object
	 */
	void nodeAdded(GUITreeNode node, String id, Object item);

	/**
	 * Notifies that a node must be changed in the model
	 * 
	 * @param node
	 *            Parent none
	 * @param id
	 *            ID of the node definition
	 * @param item
	 *            User object
	 * @see GUITreeModel#nodeChanged(TreeNode)
	 */
	void nodeUpdated(GUITreeNode node, String id, Object item);

	/**
	 * Initializes the tree model from a tree definition and a root bean.
	 * 
	 * @param treeNodes
	 *            List of node definitions
	 * @param root
	 *            Root bean
	 */
	void setTreeModel(List<Node> treeNodes, Object root);

}
