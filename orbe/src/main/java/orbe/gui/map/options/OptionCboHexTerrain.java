/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.CellRendererTerrain;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.gui.message.OrbeMessageTerrain;
import orbe.model.style.HexTerrain;

/**
 * Sélection du terrain.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboHexTerrain.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public class OptionCboHexTerrain extends AbstractToolOption {

	protected static final int CBO_HEIGHT = CellRendererTerrain.CELL_WIDTH + 4;

	protected static final int CBO_WIDTH = 200;

	private JComboBox cbo;

	private boolean onSetup = false;

	private boolean onChange = false;
	
	private OrbeControler controler;

	public OptionCboHexTerrain() {
		cbo = new JComboBox();
		cbo.setPreferredSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setMaximumSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setMaximumRowCount(20);
		cbo.setRenderer(new CellRendererTerrain());

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!onSetup) {
					HexTerrain terrain = (HexTerrain) cbo.getSelectedItem();
					try {
						onChange = true;
						Bus.get().publish(new OrbeMessageTerrain(terrain));
					} finally {
						onChange = false;
					}
				}
			}

		});

		Bus.get().subscribe(OrbeMessageTerrain.class, new Subscriber<OrbeMessageTerrain>() {

			public void receive(OrbeMessageTerrain message) {
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
		return GUIStrings.get("ToolOption.HexTerrain");
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
				List<HexTerrain> terrains = context.getMap().getSettings().getTerrains().getTerrains();
				for (HexTerrain terrain : terrains) {
					cbo.addItem(terrain);
				}
				cbo.setSelectedItem(controler.getToolSettings().getHexTerrain());
			}
		} finally {
			onSetup = false;
		}
	}

}
