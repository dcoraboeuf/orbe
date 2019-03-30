/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.tree;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.tree.model.GUITreeNode;
import net.sf.doolin.gui.tree.support.TreeSupport;

/**
 * This action is suitable for a contextual action in a tree.
 * 
 * @author Damien Coraboeuf
 * @version $Id: GUITreeAction.java,v 1.2 2007/08/01 15:58:45 guinnessman Exp $
 */
public interface GUITreeAction extends Action {

	/**
	 * Sets the associated tree
	 * 
	 * @param tree
	 *            Tree the action is running in.
	 */
	void setTree(TreeSupport tree);

	/**
	 * Sets the associated node
	 * 
	 * @param node
	 *            Node the action is running for.
	 */
	void setNode(GUITreeNode node);

}
