/*
 * Created on Sep 19, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import net.sf.doolin.gui.field.FieldColor;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.ColorSupport;

/**
 * Default support implementation for the <code>{@link FieldColor}</code>
 * field.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class SwingColorSupport extends AbstractSwingInfoFieldSupport<FieldColor, JPanel> implements ColorSupport {

	private JPanel panel;

	@Override
	protected JPanel createComponent() {
		// Panel for selection
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(16, 16));
		panel.setBorder(BorderFactory.createEtchedBorder());
		// Event handling
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (panel.isEnabled() && !getField().isReadOnly()) {
					doSelect();
				}
			}

		});
		// Ok
		return panel;
	}

	protected void doSelect() {
		Color color = panel.getBackground();
		color = JColorChooser.showDialog(panel, null, color);
		if (color != null) {
			panel.setBackground(color);
		}
	}

	public Color getValue() {
		return panel.getBackground();
	}

	public void setValue(Color c) {
		panel.setBackground(c);
	}
	
	public void bindEditEvent(final EventAction eventAction) {
		panel.addPropertyChangeListener("backgroundColor", new PropertyChangeListener() {
		
			public void propertyChange(PropertyChangeEvent evt) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
