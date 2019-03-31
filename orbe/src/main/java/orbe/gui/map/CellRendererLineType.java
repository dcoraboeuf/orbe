/*
 * Created on 5 dec. 06
 */
package orbe.gui.map;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;

import orbe.model.style.LineType;

/**
 * Cell renderer pour les types de lignes.
 * 
 * @see LineType
 * @author Damien Coraboeuf
 * @version $Id: CellRendererLineType.java,v 1.1 2006/12/05 14:49:36 guinnessman Exp $
 */
public class CellRendererLineType extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Type de ligne
		LineType type = (LineType) value;
		// Texte
		String text = GUIStrings.get("LineType." + type);
		super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
		// Ic�ne
		setIcon(GUIUtils.getIcon("IconLineType." + type, IconSize.SMALL));
		// Ok
		return this;
	}

}
