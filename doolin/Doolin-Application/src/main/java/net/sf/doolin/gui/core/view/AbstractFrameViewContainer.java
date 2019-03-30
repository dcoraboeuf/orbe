/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JComponent;

import net.sf.doolin.gui.swing.BarContainer;

/**
 * This container is hosting a <code>{@link BarContainer}</code> panel.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractFrameViewContainer extends AbstractViewContainer {

	private BarContainer container;

	@Override
	protected void build() {
		container = new BarContainer();
	}

	@Override
	protected void setContent(JComponent component) {
		container.setContent(component);
	}

	protected BarContainer getContainer() {
		return container;
	}

}
