/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import net.sf.doolin.gui.field.AbstractSupportField;
import net.sf.doolin.gui.tree.support.TreeSupport;

/**
 * Tree field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FieldTree.java,v 1.3 2007/08/15 09:05:21 guinnessman Exp $
 */
public class FieldTree extends AbstractSupportField<TreeSupport> {

	private JScrollPane scrollPane;

	private List<Node> treeNodes = new ArrayList<Node>();

	private boolean rootVisible = true;

	private Object root;

	@Override
	protected TreeSupport createSupport() {
		return createSupport(TreeSupport.class);
	}

	@Override
	protected void init() {
		super.init();
		// Creates the scrollpane
		scrollPane = new JScrollPane(getSupport().getComponent());
	}

	public JComponent getComponent() {
		return scrollPane;
	}

	@Override
	public JComponent getFocusableComponent() {
		return getSupport().getComponent();
	}

	public Object getFieldData(Object formData) {
		return root;
	}

	public void setFieldData(Object formData, Object fieldData) {
		root = fieldData;
		// Creates the model
		getSupport().setTreeModel(treeNodes, root);
	}

	/**
	 * @return List of node definitions
	 */
	public List<Node> getTreeNodes() {
		return treeNodes;
	}

	/**
	 * @param treeNodes
	 *            List of node definitions
	 */
	public void setTreeNodes(List<Node> treeNodes) {
		this.treeNodes = treeNodes;
	}

	/**
	 * @return <code>true</code> if the root is visible
	 * @see JTree#isRootVisible()
	 */
	public boolean isRootVisible() {
		return rootVisible;
	}

	/**
	 * @param rootVisible
	 *            <code>true</code> if the root is visible
	 * @see JTree#setRootVisible(boolean)
	 */
	public void setRootVisible(boolean rootVisible) {
		this.rootVisible = rootVisible;
	}

}
