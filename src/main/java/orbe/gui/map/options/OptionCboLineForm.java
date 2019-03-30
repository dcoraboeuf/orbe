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
import orbe.gui.map.CellRendererLineForm;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageLineForm;
import orbe.model.element.line.LineForm;

/**
 * Sélection du type de ligne.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboBrushSize.java,v 1.1 2006/11/07 21:21:05 guinnessman
 *          Exp $
 */
public class OptionCboLineForm extends AbstractToolOption {

	private JComboBox cbo;

	public OptionCboLineForm() {
		cbo = new JComboBox();
		for (LineForm form : LineForm.values()) {
			cbo.addItem(form);
		}
		
		cbo.setRenderer(new CellRendererLineForm());

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LineForm form = (LineForm) cbo.getSelectedItem();
				Bus.get().publish(new OrbeMessageLineForm(form));
			}

		});
	}

	public void setup(OrbeControler controler) {
		if (controler != null && controler.getContext() != null) {
			LineForm form = controler.getToolSettings().getLineForm();
			cbo.setSelectedItem(form);
		}
	}

	public JComponent getComponent() {
		return cbo;
	}

	public String getLabel() {
		return GUIStrings.get("ToolOption.LineForm");
	}

}
