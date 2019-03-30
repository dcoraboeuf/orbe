/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.core.support;


/**
 * Just returns a predefined object.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FixedViewDataFactory.java,v 1.2 2007/08/15 09:05:25 guinnessman Exp $
 */
public class FixedViewDataFactory implements ViewDataFactory {

	private Object viewData;

	public Object getViewData(Object viewData) {
		return getViewData();
	}

	/**
	 * Returns the <code>viewData</code> property.
	 * 
	 * @return <code>viewData</code> property.
	 */
	public Object getViewData() {
		return viewData;
	}

	/**
	 * Sets the <code>viewData</code> property.
	 * 
	 * @param viewData
	 *            <code>viewData</code> property.
	 */
	public void setViewData(Object viewData) {
		this.viewData = viewData;
	}

}
