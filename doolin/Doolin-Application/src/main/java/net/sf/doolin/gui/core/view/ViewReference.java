/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.ViewDataFactory;

public class ViewReference {

	private Object constraint;

	private View view;

	private ViewDataFactory viewDataFactory;

	public Object getConstraint() {
		return constraint;
	}

	public void setConstraint(Object constraint) {
		this.constraint = constraint;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public ViewDataFactory getViewDataFactory() {
		return viewDataFactory;
	}

	public void setViewDataFactory(ViewDataFactory viewDataFactory) {
		this.viewDataFactory = viewDataFactory;
	}

}
