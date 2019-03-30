/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

import java.util.List;

import net.sf.doolin.gui.field.helper.DefaultLabelInfoProvider;
import net.sf.doolin.gui.field.helper.LabelInfoProvider;

/**
 * Node definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Node.java,v 1.3 2007/08/15 09:05:21 guinnessman Exp $
 */
public class Node {

	private String id;

	private boolean root;

	private List<Children> children;

	private LabelInfoProvider labelProvider = new DefaultLabelInfoProvider();

	private List<NodeAction> nodeActions;

	/**
	 * @return List of children definitions
	 */
	public List<Children> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            List of children definitions
	 */
	public void setChildren(List<Children> children) {
		this.children = children;
	}

	/**
	 * @return Node id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            Node id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Is this node a root node?
	 */
	public boolean isRoot() {
		return root;
	}

	/**
	 * @param root
	 *            Is this node a root node?
	 */
	public void setRoot(boolean root) {
		this.root = root;
	}

	/**
	 * @return Associated label provider
	 */
	public LabelInfoProvider getLabelProvider() {
		return labelProvider;
	}

	/**
	 * @param labelProvider
	 *            Associated label provider
	 */
	public void setLabelProvider(LabelInfoProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	/**
	 * @return List of actions associated to this node
	 */
	public List<NodeAction> getNodeActions() {
		return nodeActions;
	}

	/**
	 * @param nodeActions
	 *            List of actions associated to this node
	 */
	public void setNodeActions(List<NodeAction> nodeActions) {
		this.nodeActions = nodeActions;
	}

}
