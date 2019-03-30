/*
 * Created on Sep 11, 2007
 */
package net.sf.doolin.gui.swing;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;


/**
 * This panel is able to host both toolbars and status bars.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class BarContainer extends JPanel {

	private int topRows;

	private int bottomRows;

	private JPanel panelTop;

	private JPanel panelBottom;

	/**
	 * Constructor.
	 */
	public BarContainer() {
		super(new BorderLayout());

		// Space at the top
		topRows = 0;
		panelTop = new JPanel(new GridBagLayout());
		add(panelTop, BorderLayout.PAGE_START);

		// Space at the bottom
		bottomRows = 0;
		panelBottom = new JPanel(new GridBagLayout());
		add(panelBottom, BorderLayout.PAGE_END);
	}

	/**
	 * Sets the main content of this panel.
	 * 
	 * @param component
	 *            Component to set as main content
	 */
	public void setContent(JComponent component) {
		add(component, BorderLayout.CENTER);
	}

	/**
	 * Adds a component to this container
	 * 
	 * @param component
	 *            Component to add
	 * @param position
	 *            Position of the component
	 */
	public void addBar(JComponent component, BarPosition position) {
		switch (position) {
		case BOTTOM:
			GridBagConstraints gc = createRowGC(bottomRows++);
			panelBottom.add(component, gc);
			break;
		default:
			gc = createRowGC(topRows++);
			panelTop.add(component, gc);
			break;
		}
	}

	/**
	 * Creates GC constraints for a row
	 * 
	 * @param row
	 *            Row index
	 * @return GC constraints
	 */
	protected GridBagConstraints createRowGC(int row) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = row;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 1;
		return gc;
	}

}
