/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree.support;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.swing.AbstractSwingFieldSupport;
import net.sf.doolin.gui.tree.FieldTree;
import net.sf.doolin.gui.tree.Node;
import net.sf.doolin.gui.tree.model.GUITreeModel;
import net.sf.doolin.gui.tree.model.GUITreeNode;
import net.sf.doolin.gui.tree.renderer.GUITreeRenderer;

/**
 * Implementation of a tree, specific to the Doolin GUI framework.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingTreeSupport.java,v 1.4 2007/08/15 09:05:28 guinnessman Exp $
 */
public class SwingTreeSupport extends AbstractSwingFieldSupport<FieldTree> implements TreeSupport {

	private JTree tree;

	private GUITreeModel treeModel;

	private TreeCellRenderer renderer = new GUITreeRenderer();

	/**
	 * Executes the default action on 2-click
	 * 
	 * @param e
	 *            Triggering event
	 */
	protected void doubleClick(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int row = tree.getRowForLocation(x, y);
		if (row >= 0) {
			TreePath treePath = tree.getPathForRow(row);
			tree.setSelectionPath(treePath);
			GUITreeNode treeNode = (GUITreeNode) treePath.getLastPathComponent();
			// Get the default action
			treeNode.runDefaultAction(this, getField().getForm().getView());
		}
	}

	/**
	 * Shows a popup window
	 * 
	 * @param e
	 *            Triggering event
	 */
	protected void popup(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int row = tree.getRowForLocation(x, y);
		if (row < 0) {
			// Empty selection
			tree.clearSelection();
			// Menu for an empty selection
			JPopupMenu popupMenu = getMenu();
			if (popupMenu != null) {
				popupMenu.show(tree, x, y);
			}
		} else {
			TreePath treePath = tree.getPathForRow(row);
			tree.setSelectionPath(treePath);
			GUITreeNode treeNode = (GUITreeNode) treePath.getLastPathComponent();
			// Get the menu
			JPopupMenu popupMenu = treeNode.getMenu(this, getField().getForm().getView());
			if (popupMenu != null) {
				popupMenu.show(tree, x, y);
			}
		}
	}

	/**
	 * Get the menu for an empty selection. By default, returns
	 * <code>null</code>.
	 * 
	 * @return Menu to be displayed
	 */
	protected JPopupMenu getMenu() {
		return null;
	}

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
	public void nodeAdded(GUITreeNode node, String id, Object item) {
		GUITreeNode childNode = treeModel.nodeAdded(node, id, item);
		TreeNode[] nodes = treeModel.getPathToRoot(childNode);
		TreePath treePath = new TreePath(nodes);
		tree.makeVisible(treePath);
		tree.setSelectionPath(treePath);
	}

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
	public void nodeUpdated(GUITreeNode node, String id, Object item) {
		node.setUserObject(item);
		treeModel.nodeChanged(node);
	}

	/**
	 * Notifies that the node must be deleted from the model
	 * 
	 * @param node
	 *            Node to be deleted
	 * @see GUITreeModel#nodeDelete(GUITreeNode)
	 */
	public void nodeDeleted(GUITreeNode node) {
		treeModel.nodeDelete(node);
	}

	/**
	 * Gets the current selected node if any.
	 * 
	 * @return Selected node or <code>null</code> if no node is selected.
	 */
	public GUITreeNode getSelectedNode() {
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			return (GUITreeNode) path.getLastPathComponent();
		} else {
			return null;
		}
	}

	public JComponent getComponent() {
		return tree;
	}

	@Override
	protected void build() {
		// Creates the tree
		tree = new JTree();
		// General properties
		tree.setRootVisible(getField().isRootVisible());
		// Single selection
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// Renderer
		tree.setCellRenderer(renderer);
		// Listener for clicks
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup(e);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					doubleClick(e);
				}
			}
		});
	}

	/**
	 * Creates an instance of a <code>GUITreeModel</code> to support the model
	 * of this tree.
	 * 
	 * @see GUITreeModel
	 * @see net.sf.doolin.gui.tree.support.TreeSupport#setTreeModel(java.util.List,
	 *      java.lang.Object)
	 */
	public void setTreeModel(List<Node> treeNodes, Object root) {
		treeModel = new GUITreeModel(treeNodes, root);
		tree.setModel(treeModel);
	}

	/**
	 * @return Returns the renderer.
	 */
	public TreeCellRenderer getRenderer() {
		return renderer;
	}

	/**
	 * @param renderer
	 *            The renderer to set.
	 */
	public void setRenderer(TreeCellRenderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.field.support.FieldSupport#setValidationError(net.sf.doolin.gui.core.validation.ValidationError)
	 */
	public void setValidationError(ValidationError validationError) {
	}

	public void bindEditEvent(final EventAction eventAction) {
		treeModel.addTreeModelListener(new TreeModelListener() {
		
			public void treeStructureChanged(TreeModelEvent e) {
				edit();
			}
		
			public void treeNodesRemoved(TreeModelEvent e) {
				edit();
			}
		
			public void treeNodesInserted(TreeModelEvent e) {
				edit();
			}
		
			public void treeNodesChanged(TreeModelEvent e) {
				edit();
			}
			
			protected void edit() {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
