/*
 * Created on Nov 7, 2006
 */
package orbe.gui.map.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageHexBrushSize;

/**
 * Sélection de la taille d'une brosse pour le dessin des hexagones.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboBrushSize.java,v 1.1 2006/11/07 21:21:05 guinnessman
 *          Exp $
 */
public class OptionCboBrushSize extends AbstractToolOption {

	private static final int BRUSH_MAX = 4;

	private JComboBox cbo;

	public OptionCboBrushSize() {
		cbo = new JComboBox();
		for (int i = 0; i <= BRUSH_MAX; i++) {
			cbo.addItem(i);
		}

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = (Integer) cbo.getSelectedItem();
				Bus.get().publish(new OrbeMessageHexBrushSize(size));
			}

		});
	}

	public void setup(OrbeControler controler) {
		if (controler != null && controler.getContext() != null) {
			int size = controler.getToolSettings().getHexBrushSize();
			cbo.setSelectedItem(size);
		}
	}

	public JComponent getComponent() {
		return cbo;
	}

	public String getLabel() {
		return GUIStrings.get("ToolOption.HexBrushSize");
	}

}
