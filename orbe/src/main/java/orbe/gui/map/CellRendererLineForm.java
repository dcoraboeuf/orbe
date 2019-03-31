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

import orbe.model.element.line.LineForm;

/**
 * Cell renderer pour les formes de ligne.
 * 
 * @see LineForm
 * @author Damien Coraboeuf
 * @version $Id: CellRendererLineForm.java,v 1.1 2006/12/05 14:49:36 guinnessman Exp $
 */
public class CellRendererLineForm extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		// Forme de ligne
		LineForm form = (LineForm) value;
		// Texte
		String text = GUIStrings.get("LineForm." + form);
		super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
		// Ic�ne
		setIcon(GUIUtils.getIcon("IconLineForm." + form, IconSize.SMALL));
		// Ok
		return this;
	}

}
