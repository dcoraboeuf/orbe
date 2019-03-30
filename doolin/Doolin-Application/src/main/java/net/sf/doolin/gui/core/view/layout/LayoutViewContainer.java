/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.core.view.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.view.AbstractViewContainer;
import net.sf.doolin.gui.style.Style;
import net.sf.doolin.gui.swing.SwingUtils;

/**
 * Container used in a layout view. It adds decoration to the child view
 * (title).
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayoutViewContainer.java,v 1.2 2007/08/14 11:48:57 guinnessman Exp $
 */
public class LayoutViewContainer extends AbstractViewContainer {

	private JPanel container;

	private JPanel panelTitle;

	private JLabel labelTitle;

	/**
	 * @return Associated layout view
	 */
	protected LayoutView getLayoutView() {
		return (LayoutView) getView();
	}

	@Override
	protected void build() {
		container = new JPanel(new BorderLayout());
		// Constraints
		LayoutViewConstraint layoutViewConstraint = (LayoutViewConstraint) getViewConstraint();
		// Use style
		Style style = layoutViewConstraint.getStyle();
		if (style != null) {
			style.apply(container);
		}
		// Title
		if (layoutViewConstraint.isShowTitle()) {
			panelTitle = new JPanel(new FlowLayout(FlowLayout.LEADING, 3, 3));
			panelTitle.setBackground(SystemColor.activeCaption);
			container.add(panelTitle, BorderLayout.PAGE_START);
			labelTitle = new JLabel();
			labelTitle.setForeground(SystemColor.activeCaptionText);
			SwingUtils.setFontSize(labelTitle, 1.2f);
			panelTitle.add(labelTitle);
		}
	}

	@Override
	protected void setContent(JComponent component) {
		SwingUtils.setPreferredSize(component);
		container.add(component, BorderLayout.CENTER);
	}

	public void close() {
	}

	public boolean display(View parentView) {
		// Title
		updateTitle();
		// Ok
		return true;
	}

	/**
	 * Updates the title of the container according to the view data.
	 */
	protected void updateTitle() {
		if (labelTitle != null) {
			// Text
			String actualTitle = GUIUtils.getActualViewTitle(getView());
			labelTitle.setText(actualTitle);
			// Icon
			Icon icon = GUIUtils.getViewIcon(getView(), IconSize.SMALL);
			labelTitle.setIcon(icon);
		}
	}

	public Component getComponent() {
		return container;
	}

	@Override
	public void onViewDataChanged() {
		updateTitle();
	}

	/**
	 * Does nothing
	 */
	@Override
	public void activate() {
	}

}
