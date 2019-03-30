/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.gui.tree.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import net.sf.doolin.gui.field.helper.LabelInfo;
import net.sf.doolin.gui.tree.model.GUITreeNode;

/**
 * Tree renderer that delegates rendering to the inner nodes.
 * 
 * @see GUITreeNode#getLabelInfo()
 * @TODO Node should return a TreeCellRenderer instance
 * @author Damien Coraboeuf
 * @version $Id: GUITreeRenderer.java,v 1.1 2007/08/01 16:26:29 guinnessman Exp $
 */
public class GUITreeRenderer extends DefaultTreeCellRenderer {

	/**
	 * Render the cell
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		// Get the label info
		GUITreeNode node = (GUITreeNode) value;
		LabelInfo ti = node.getLabelInfo();
		// Do it
		super.getTreeCellRendererComponent(tree, ti.getText(), sel, expanded, leaf, row, hasFocus);
		// Set the icon
		Icon icon = ti.getIcon();
		setIcon(icon);
		// Ok
		return this;
	}

}
