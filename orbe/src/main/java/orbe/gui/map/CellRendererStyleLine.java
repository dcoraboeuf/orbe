/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import orbe.model.style.StyleLine;

/**
 * Cell renderer for line styles.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CellRendererStyleLine.java,v 1.1 2006/11/29 16:45:30 guinnessman Exp $
 */
public class CellRendererStyleLine extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Style & name
		StyleLine style = (StyleLine) value;
		String name = style.getName();
		// Default rendering
		super.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
		// Set color
		setForeground(style.getColor());
		// Ok
		return this;
	}

}
