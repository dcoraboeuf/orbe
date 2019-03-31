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
import orbe.gui.map.CellRendererSymbol;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.message.OrbeMessageSymbol;
import orbe.model.style.HexSymbol;
import orbe.model.style.RepositoryHexSymbol;

/**
 * Sélection du symbole.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OptionCboHexSymbol.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public class OptionCboHexSymbol extends AbstractToolOption {

	protected static final int CBO_HEIGHT = CellRendererSymbol.CELL_WIDTH + 4;

	protected static final int CBO_WIDTH = 200;

	private JComboBox cbo;

	private boolean onChange = false;

	public OptionCboHexSymbol() {
		cbo = new JComboBox();
		cbo.setPreferredSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setMaximumSize(new Dimension(CBO_WIDTH, CBO_HEIGHT));
		cbo.setMaximumRowCount(20);
		cbo.setRenderer(new CellRendererSymbol());

		List<HexSymbol> symbols = RepositoryHexSymbol.getInstance().getSymbols();
		for (HexSymbol symbol : symbols) {
			cbo.addItem(symbol);
		}

		cbo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				HexSymbol symbol = (HexSymbol) cbo.getSelectedItem();
				try {
					onChange = true;
					Bus.get().publish(new OrbeMessageSymbol(symbol));
				} finally {
					onChange = false;
				}
			}

		});

		Bus.get().subscribe(OrbeMessageSymbol.class, new Subscriber<OrbeMessageSymbol>() {

			public void receive(OrbeMessageSymbol message) {
				if (!onChange) {
					cbo.setSelectedItem(message.getValue());
				}
			}

		});
	}

	public JComponent getComponent() {
		return cbo;
	}

	public String getLabel() {
		return GUIStrings.get("ToolOption.HexSymbol");
	}

	public void setup(OrbeControler controler) {
		if (controler == null) {
			cbo.setEnabled(false);
		} else {
			cbo.setEnabled(true);
			cbo.setSelectedItem(controler.getToolSettings().getHexSymbol());
		}
	}

}
