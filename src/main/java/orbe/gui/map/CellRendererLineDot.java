/*
 * Created on 5 déc. 06
 */
package orbe.gui.map;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;

import orbe.model.style.LineDotType;

/**
 * Cell renderer pour les pointillés de lignes.
 * 
 * @see LineDotType
 * @author Damien Coraboeuf
 * @version $Id: CellRendererLineDot.java,v 1.1 2006/12/05 14:49:36 guinnessman Exp $
 */
public class CellRendererLineDot extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Type de pointillé
		LineDotType dot = (LineDotType) value;
		// Texte
		String text = GUIStrings.get("LineDot." + dot);
		super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
		// Icône
		setIcon(GUIUtils.getIcon("IconLineDot." + dot, IconSize.SMALL));
		// Ok
		return this;
	}

}
