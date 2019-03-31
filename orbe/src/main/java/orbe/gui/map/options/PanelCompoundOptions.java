/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import net.sf.doolin.gui.swing.SwingUtils;
import orbe.gui.map.core.OrbeControler;

import org.apache.commons.lang.StringUtils;

/**
 * Aligne une série d'options prédéfinies.
 * 
 * @author Damien Coraboeuf
 * @version $Id: PanelCompoundOptions.java,v 1.2 2006/11/09 13:15:38 guinnessman Exp $
 */
public abstract class PanelCompoundOptions extends PanelOptions {

	/**
	 * Liste des options
	 */
	private ArrayList<ToolOption> options = new ArrayList<ToolOption>();

	public PanelCompoundOptions() {
		super(new FlowLayout(FlowLayout.LEADING, 4, 4));
	}

	/**
	 * Ajout d'une option
	 */
	public void addOption(ToolOption option) {
		options.add(option);
	}

	/**
	 * Construction du composant.
	 */
	@Override
	public void setup(OrbeControler controler) {
		removeAll();
		for (ToolOption option : options) {
			// Setup
			option.setup(controler);
			// Label
			String text = option.getLabel();
			if (StringUtils.isNotBlank(text)) {
				JLabel label = createLabel(text);
				add(label);
			}
			// Component
			add(option.getComponent());
		}
	}

	protected JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		SwingUtils.setFontSize(label, 0.8f);
		return label;
	}
}
