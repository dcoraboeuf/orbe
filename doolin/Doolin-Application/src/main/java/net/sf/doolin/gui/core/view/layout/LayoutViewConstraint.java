/*
 * Created on Aug 10, 2007
 */
package net.sf.doolin.gui.core.view.layout;

import net.sf.doolin.gui.style.Style;

/**
 * View constraint suitable for the <code>{@link LayoutView}</code>.
 * 
 * @author Damien Coraboeuf
 * @version $Id: LayoutViewConstraint.java,v 1.2 2007/08/15 09:05:31 guinnessman Exp $
 */
public class LayoutViewConstraint {

	/**
	 * Constraint specific to the layout
	 */
	private Object layoutConstraint;

	private Style style = new LayoutViewDefaultStyle();

	private boolean showTitle = true;

	/**
	 * Returns the constraint specific to the layout
	 * 
	 * @return Constraint specific to the layout
	 */
	public Object getLayoutConstraint() {
		return layoutConstraint;
	}

	/**
	 * Sets the constraint specific to the layout
	 * 
	 * @param layoutConstraint
	 *            Constraint specific to the layout
	 */
	public void setLayoutConstraint(Object layoutConstraint) {
		this.layoutConstraint = layoutConstraint;
	}

	/**
	 * @return Style associated to the container
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets the style associated to the container
	 * 
	 * @param style
	 *            Style
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * Returns the <code>showTitle</code> property.
	 * 
	 * @return <code>showTitle</code> property.
	 */
	public boolean isShowTitle() {
		return showTitle;
	}

	/**
	 * Sets the <code>showTitle</code> property.
	 * 
	 * @param showTitle
	 *            <code>showTitle</code> property.
	 */
	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

}
