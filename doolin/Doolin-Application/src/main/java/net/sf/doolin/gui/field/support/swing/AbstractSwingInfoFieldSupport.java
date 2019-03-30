/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import javax.swing.JComponent;

import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.field.Field;
import net.sf.doolin.gui.swing.InfoComponent;

/**
 * This class implements a component that is encapsulated in an
 * <code>{@link InfoComponent}</code>, which is able to display some
 * validation information.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractSwingInfoFieldSupport.java,v 1.1 2007/08/07 16:47:05 guinnessman Exp $
 * @param <F>
 *            Type of field
 * @param <C>
 *            Type of underlying component
 */
public abstract class AbstractSwingInfoFieldSupport<F extends Field, C extends JComponent> extends AbstractSwingFieldSupport<F> {

	private InfoComponent<C> component;

	@Override
	protected final void build() {
		C fieldComponent = createComponent();
		component = new InfoComponent<C>(fieldComponent);
	}

	/**
	 * Creates the actual field component.
	 * 
	 * @return Component that supports the field.
	 */
	protected abstract C createComponent();

	public JComponent getComponent() {
		return component;
	}

	public void setValidationError(ValidationError validationError) {
		component.setValidationError(validationError);
	}

}
