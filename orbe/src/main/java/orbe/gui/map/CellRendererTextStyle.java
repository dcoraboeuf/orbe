/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import orbe.model.style.TextStyle;

/**
 * Cell renderer for text styles.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CellRendererTextStyle.java,v 1.1 2006/11/17 10:56:38 guinnessman Exp $
 */
public class CellRendererTextStyle extends DefaultListCellRenderer {

	private int fontSize;

	public CellRendererTextStyle() {
		fontSize = getFont().getSize();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Style & name
		TextStyle style = (TextStyle) value;
		String name = style.getName();
		// Default rendering
		super.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
		// Set font, color and style
		Font font = new Font(style.getFontName(), style.getFontStyle(), fontSize);
		setFont(font);
		setForeground(style.getFontColor());
		// Ok
		return this;
	}

}
