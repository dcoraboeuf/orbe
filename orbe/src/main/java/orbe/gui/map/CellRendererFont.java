/*
 * Created on Nov 17, 2006
 */
package orbe.gui.map;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Cell renderer for fonts.
 * 
 * @author Damien Coraboeuf
 * @version $Id: CellRendererFont.java,v 1.1 2006/11/17 11:45:03 guinnessman Exp $
 */
public class CellRendererFont extends JPanel implements ListCellRenderer {

	private static final String TEXT = "ABCDEFabcdef";

	private JLabel labelName;

	private JLabel labelDemo;

	/**
	 * Initialisation.
	 */
	public CellRendererFont() {
		setLayout(new GridLayout(2, 1));

		labelName = new JLabel();
		labelName.setOpaque(true);
		add(labelName);

		labelDemo = new JLabel(TEXT);
		labelDemo.setBackground(SystemColor.window);
		labelDemo.setForeground(SystemColor.windowText);
		labelDemo.setOpaque(true);
		add(labelDemo);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Font font = (Font) value;

		if (font == null) {
			labelName.setText("-------------");
			labelDemo.setFont(getFont());
			labelDemo.setText(" ");
		} else {
			labelName.setText(font.getName());
			labelDemo.setFont(font.deriveFont(labelName.getFont().getSize2D()));
			labelDemo.setText(TEXT);
		}

		// Selection
		if (isSelected) {
			labelName.setBackground(list.getSelectionBackground());
			labelName.setForeground(list.getSelectionForeground());
		} else {
			labelName.setBackground(list.getBackground());
			labelName.setForeground(list.getForeground());
		}
		// Focus
		Border border = null;
		if (cellHasFocus) {
			if (isSelected) {
				border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
			}
			if (border == null) {
				border = UIManager.getBorder("List.focusCellHighlightBorder");
			}
		} else {
			border = new EmptyBorder(1, 1, 1, 1);
		}
		setBorder(border);

		// Ok
		return this;
	}
}
