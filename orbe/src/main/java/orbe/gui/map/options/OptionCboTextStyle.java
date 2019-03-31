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
import orbe.gui.map.CellRendererTextStyle;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.gui.message.OrbeMessageTextStyle;
import orbe.model.style.TextStyle;

/**
 * Sélection du style de texte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboHexTerrain.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public class OptionCboTextStyle extends AbstractToolOption {

	private JComboBox cbo;

	private boolean onSetup = false;

	private boolean onChange = false;
	
	private OrbeControler controler;

	public OptionCboTextStyle() {
		cbo = new JComboBox();
		cbo.setMaximumRowCount(20);
		cbo.setRenderer(new CellRendererTextStyle());

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!onSetup) {
					TextStyle style = (TextStyle) cbo.getSelectedItem();
					try {
						onChange = true;
						Bus.get().publish(new OrbeMessageTextStyle(style));
					} finally {
						onChange = false;
					}
				}
			}

		});

		Bus.get().subscribe(OrbeMessageTextStyle.class, new Subscriber<OrbeMessageTextStyle>() {

			public void receive(OrbeMessageTextStyle message) {
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
		return GUIStrings.get("ToolOption.TextStyle");
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
				List<TextStyle> styles = context.getMap().getSettings().getTextStyles().getStyles();
				for (TextStyle style : styles) {
					cbo.addItem(style);
				}
				cbo.setSelectedItem(controler.getToolSettings().getTextStyle());
			}
		} finally {
			onSetup = false;
		}
	}

}
