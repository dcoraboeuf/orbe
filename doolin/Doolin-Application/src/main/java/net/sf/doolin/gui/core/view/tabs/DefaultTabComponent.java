/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.core.view.tabs;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * Default tab component implementation. It manages an icon, a label and a close
 * button.
 * 
 * @TODO TabbedViewConstaints that manages a closeable state
 * @author Damien Coraboeuf
 * @version $Id: DefaultTabComponent.java,v 1.1 2007/07/31 15:33:05 guinnessman
 *          Exp $
 */
public class DefaultTabComponent extends AbstractTabComponent {

	private JLabel label;

	private TabbedViewContainer container;

	/**
	 * Constructor
	 */
	public DefaultTabComponent(TabbedViewContainer container) {
		this.container = container;
		label = new JLabel(" ");
		update();
	}

	public JComponent getComponent() {
		return label;
	}

	public void setTabbedViewContainer(TabbedViewContainer container) {
		this.container = container;
	}

	/**
	 * Updates the title and the icon of the tab component
	 */
	protected void update() {
		// Title
		String title = GUIUtils.getActualViewTitle(container.getView());
		label.setText(title);
		// Icon
		Icon icon = GUIUtils.getViewIcon(container.getView(), IconSize.SMALL);
		label.setIcon(icon);
		// TODO Closeable state
	}

	public TabbedViewContainer getTabbedViewContainer() {
		return container;
	}

}
