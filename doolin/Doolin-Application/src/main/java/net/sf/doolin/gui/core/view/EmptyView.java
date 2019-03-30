/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.validation.ValidationReport;

/**
 * View without content.
 * 
 * @author Damien Coraboeuf
 * @version $Id: EmptyView.java,v 1.3 2007/08/14 11:48:53 guinnessman Exp $
 */
public class EmptyView extends AbstractView {

	private JPanel panel;

	public void init() {
		super.init();
		panel = new JPanel();
	}

	public JComponent getComponent() {
		return panel;
	}

	public ViewContainer createViewContainer() {
		throw new IllegalStateException("This view cannot container any view.");
	}

	public void addChild(ViewContainer viewContainer) {
		throw new IllegalStateException("This view cannot container any view.");
	}

	public void validate(ValidationReport validationReport) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.core.View#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
	}

}
