/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree.model;

import java.awt.Font;
import java.util.Collection;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.field.helper.LabelInfo;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;
import net.sf.doolin.gui.service.SwingFactory;
import net.sf.doolin.gui.swing.SwingUtils;
import net.sf.doolin.gui.tree.Children;
import net.sf.doolin.gui.tree.GUITreeAction;
import net.sf.doolin.gui.tree.Node;
import net.sf.doolin.gui.tree.NodeAction;
import net.sf.doolin.gui.tree.support.SwingTreeSupport;

/**
 * Node implementation for the Doolin GUI tree.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUITreeNode.java,v 1.2 2007/08/10 16:54:39 guinnessman Exp $
 */
public class GUITreeNode extends DefaultMutableTreeNode {

	private Node def;

	/**
	 * Constructor for a node.
	 * 
	 * @param model
	 *            Owner
	 * @param def
	 *            Node definition
	 * @param bean
	 *            Associated user object
	 */
	public GUITreeNode(GUITreeModel model, Node def, Object bean) {
		super(bean);
		this.def = def;
		fill(model, def, bean);
	}

	/**
	 * Returns the associated node definition.
	 * 
	 * @return Node definition.
	 */
	public Node getDef() {
		return def;
	}

	/**
	 * Initializes a node
	 * 
	 * @param model
	 *            Owner
	 * @param def
	 *            Node definition
	 * @param bean
	 *            Associated bean
	 */
	protected void fill(GUITreeModel model, Node def, Object bean) {
		removeAllChildren();
		List<Children> childrenList = def.getChildren();
		if (childrenList == null || childrenList.isEmpty()) {
			setAllowsChildren(false);
		} else {
			setAllowsChildren(true);
			for (Children children : childrenList) {
				// Child definition
				Node childNode = model.getNode(children.getId());
				// Get the list of children
				Collection childBeanList = getChildBeanList(bean, children);
				if (childBeanList != null) {
					// Fills children
					for (Object childBean : childBeanList) {
						GUITreeNode childTreeNode = createChildTreeNode(model, childNode, childBean);
						add(childTreeNode);
					}
				}
			}
		}
	}

	/**
	 * Creates a node for a child.
	 * 
	 * @param model
	 *            Owner
	 * @param childNode
	 *            Definition for the child node
	 * @param childBean
	 *            Bean asscoiated to the child node
	 * @return Node for the child
	 */
	protected GUITreeNode createChildTreeNode(GUITreeModel model, Node childNode, Object childBean) {
		return new GUITreeNode(model, childNode, childBean);
	}

	/**
	 * Gets the list of beans which are associated to a children definition
	 * 
	 * @param bean
	 *            Object associated to the parent node
	 * @param children
	 *            Children definition
	 * @return List of objects that define the children
	 */
	protected Collection getChildBeanList(Object bean, Children children) {
		return children.getChildBeans(bean);
	}

	/**
	 * Get the label information for this node.
	 * 
	 * @return Label information for this node.
	 * @see Node#getLabelProvider()
	 * @see LabelInfoProvider#getLabelIcon(Object)
	 */
	public LabelInfo getLabelInfo() {
		return def.getLabelProvider().getLabelIcon(getUserObject());
	}

	/**
	 * Runs the default action associated with this node.
	 * 
	 * @param tree
	 *            Hosting tree
	 * @param view
	 *            Hosting view
	 */
	public void runDefaultAction(SwingTreeSupport tree, View view) {
		// Looks for a default action
		List<NodeAction> nodeActions = def.getNodeActions();
		if (nodeActions != null) {
			for (NodeAction nodeAction : nodeActions) {
				if (nodeAction.isDefault()) {
					Action action = nodeAction.getAction();
					// Setup of the action
					if (action instanceof GUITreeAction) {
						GUITreeAction treeAction = (GUITreeAction) action;
						treeAction.setTree(tree);
						treeAction.setNode(this);
					}
					action.setView(view);
					action.execute();
					break;
				}
			}
		}
	}

	/**
	 * Creates a contextual menu associated with this node.
	 * 
	 * @param tree
	 *            Hosting tree
	 * @param view
	 *            Hosting view
	 * @return Menu or <code>null</code> if no menu is appliable for this
	 *         node.
	 */
	public JPopupMenu getMenu(SwingTreeSupport tree, View view) {
		List<NodeAction> nodeActions = def.getNodeActions();
		if (nodeActions != null && !nodeActions.isEmpty()) {
			JPopupMenu menu = new JPopupMenu();
			// For each action
			boolean separator = true;
			for (NodeAction nodeAction : nodeActions) {
				Action action = nodeAction.getAction();
				if (action != null) {
					// Action setup
					if (action instanceof GUITreeAction) {
						GUITreeAction treeAction = (GUITreeAction) action;
						treeAction.setTree(tree);
						treeAction.setNode(this);
					}
					action.setView(view);
					// Swing action
					javax.swing.Action swingAction = GUIUtils.getService(SwingFactory.class).createSwingAction(action, view, null, IconSize.SMALL);
					// Ok
					JMenuItem menuItem = menu.add(swingAction);
					// Default action
					if (nodeAction.isDefault()) {
						SwingUtils.setFontStyle(menuItem, Font.BOLD);
					}
					// Separator handling
					separator = false;
				} else {
					// Separator
					if (!separator) {
						menu.addSeparator();
						separator = true;
					}
				}
			}
			// Ok
			return menu;
		} else {
			// No menu
			return null;
		}
	}

}
