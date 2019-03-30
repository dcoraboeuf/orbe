/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.CellRendererStyleLine;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.gui.message.OrbeMessageLineStyle;
import orbe.model.style.StyleLine;

/**
 * Sélection du style de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboHexTerrain.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public class OptionCboLineStyle extends AbstractToolOption {

	private JComboBox cbo;

	private boolean onSetup = false;

	private boolean onChange = false;

	private OrbeControler controler;

	public OptionCboLineStyle() {
		cbo = new JComboBox();
		cbo.setMaximumRowCount(20);
		cbo.setRenderer(new CellRendererStyleLine());

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!onSetup) {
					StyleLine style = (StyleLine) cbo.getSelectedItem();
					try {
						onChange = true;
						Bus.get().publish(new OrbeMessageLineStyle(style));
					} finally {
						onChange = false;
					}
				}
			}

		});

		Bus.get().subscribe(OrbeMessageLineStyle.class, new Subscriber<OrbeMessageLineStyle>() {

			public void receive(OrbeMessageLineStyle message) {
				if (!onChange) {
					cbo.setSelectedItem(message.getValue());
				}
			}

		});

		Bus.get().subscribe(OrbeMessageContextSettings.class, new Subscriber<OrbeMessageContextSettings>() {

			public void receive(OrbeMessageContextSettings message) {
				setup(controler);
			}

		});
	}

	public JComponent getComponent() {
		return cbo;
	}

	public String getLabel() {
		return GUIStrings.get("ToolOption.LineStyle");
	}

	public void setup(OrbeControler ctrl) {
		this.controler = ctrl;
		onSetup = true;
		try {
			if (controler == null) {
				cbo.removeAllItems();
				cbo.setEnabled(false);
			} else {
				cbo.removeAllItems();
				cbo.setEnabled(true);
				OrbeContext context = controler.getContext();
				List<StyleLine> styles = context.getMap().getSettings().getStyleLineList().getStyles();
				for (StyleLine style : styles) {
					cbo.addItem(style);
				}
				cbo.setSelectedItem(controler.getToolSettings().getLineStyle());
			}
		} finally {
			onSetup = false;
		}
	}

}
