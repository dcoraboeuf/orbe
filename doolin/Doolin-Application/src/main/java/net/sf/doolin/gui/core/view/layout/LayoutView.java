/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.core.view.layout;

import java.awt.LayoutManager;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.core.view.AbstractView;

/**
 * This view arranges subviews using a standard AWT layout and encapsulates them
 * using some decorators.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayoutView.java,v 1.3 2007/08/15 09:05:31 guinnessman Exp $
 */
public class LayoutView extends AbstractView {

	private LayoutManager layout;

	private LayoutConstraintAdapter layoutConstraintAdapter = new DefaultLayoutConstraintAdapter();

	private JPanel parent;

	/**
	 * Creates the view component using the given layout.
	 * 
	 * @see net.sf.doolin.gui.core.View#init()
	 */
	public void init() {
		super.init();
		parent = new JPanel(layout);
	}

	public JComponent getComponent() {
		return parent;
	}

	public ViewContainer createViewContainer() {
		return new LayoutViewContainer();
	}

	public void validate(ValidationReport validationReport) {
		// TODO Delegates to sub views
		throw new RuntimeException("NYI");
	}

	@Override
	public void addChild(ViewContainer viewContainer) {
		// Cast to specific
		LayoutViewContainer container = (LayoutViewContainer) viewContainer;
		// Constraints
		LayoutViewConstraint viewConstraint = (LayoutViewConstraint) viewContainer.getViewConstraint();
		Object confConstraint = viewConstraint.getLayoutConstraint();
		@SuppressWarnings("unchecked")
		Object actualConstraint = layoutConstraintAdapter.adapt(confConstraint);
		// Adds the component to the panel
		parent.add(container.getComponent(), actualConstraint);
		// General
		super.addChild(viewContainer);
	}

	@Override
	public void removeChild(ViewContainer viewContainer) {
		// Cast to specific
		LayoutViewContainer container = (LayoutViewContainer) viewContainer;
		// Removes the component to the panel
		parent.remove(container.getComponent());
		// General
		super.removeChild(viewContainer);
	}

	/**
	 * Returns the layout
	 * 
	 * @return Layout
	 */
	public LayoutManager getLayout() {
		return layout;
	}

	/**
	 * Sets the layout
	 * 
	 * @param layout
	 *            Layout
	 */
	public void setLayout(LayoutManager layout) {
		this.layout = layout;
	}

	/**
	 * Returns the layout constraint adapter
	 * 
	 * @return Layout constraint adapter
	 */
	public LayoutConstraintAdapter getLayoutConstraintAdapter() {
		return layoutConstraintAdapter;
	}

	/**
	 * Sets the layout constraint adapter
	 * 
	 * @param layoutConstraintAdapter
	 *            Layout constraint adapter
	 */
	public void setLayoutConstraintAdapter(LayoutConstraintAdapter layoutConstraintAdapter) {
		this.layoutConstraintAdapter = layoutConstraintAdapter;
	}

	/**
	 * Delegates to all subviews.
	 * 
	 * @see net.sf.doolin.gui.core.View#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		Set<ViewContainer> viewContainers = getChildrenViewContainers();
		for (ViewContainer viewContainer : viewContainers) {
			viewContainer.getView().setEnabled(enabled);
		}
	}

}
