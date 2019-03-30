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

import orbe.model.style.LineGradType;

/**
 * Cell renderer pour les graduations de lignes.
 * 
 * @see LineGradType
 * @author Damien Coraboeuf
 * @version $Id: CellRendererLineGrad.java,v 1.1 2006/12/05 14:49:36 guinnessman Exp $
 */
public class CellRendererLineGrad extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Type de graduation
		LineGradType grad = (LineGradType) value;
		// Texte
		String text = GUIStrings.get("LineGrad." + grad);
		super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
		// Icône
		setIcon(GUIUtils.getIcon("IconLineGrad." + grad, IconSize.SMALL));
		// Ok
		return this;
	}

}
