/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.tree;

import net.sf.doolin.gui.core.action.AbstractAction;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.field.FieldAction;
import net.sf.doolin.gui.tree.model.GUITreeNode;
import net.sf.doolin.gui.tree.support.TreeSupport;

/**
 * Utility ancestor for an action linked to a tree field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractFieldTreeAction.java,v 1.3 2007/08/02 19:31:15 guinnessman Exp $
 */
public abstract class AbstractFieldTreeAction extends AbstractAction implements FieldAction {

	private FieldTree fieldTree;

	/**
	 * @see net.sf.doolin.gui.field.FieldAction#setField(net.sf.doolin.gui.field.Field)
	 */
	public void setField(Field field) {
		fieldTree = (FieldTree) field;
	}

	/**
	 * Gets the associated tree
	 * 
	 * @return Tree support
	 */
	public TreeSupport getTree() {
		return fieldTree.getSupport();
	}

	/**
	 * Gets the current selected node
	 * 
	 * @return Selected node or <code>null</code> if none is selected
	 */
	public GUITreeNode getSelectedNode() {
		return getTree().getSelectedNode();
	}

	/**
	 * @return Returns the tree field.
	 */
	public FieldTree getFieldTree() {
		return fieldTree;
	}

}
