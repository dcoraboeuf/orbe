/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.ViewContainer;
import net.sf.doolin.gui.core.view.DialogViewContainer;
import net.sf.doolin.gui.core.view.FrameViewContainer;
import net.sf.doolin.gui.wizard.WizardView;
import net.sf.doolin.gui.wizard.WizardViewContainer;

/**
 * Default implementation for a view container factory.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultViewContainerFactory.java,v 1.3 2007/08/07 16:47:08 guinnessman Exp $
 */
public class DefaultViewContainerFactory extends AbstractViewContainerFactory {

	/**
	 * Creates a frame container.
	 * 
	 * @see FrameViewContainer
	 * @see net.sf.doolin.gui.core.ViewContainerFactory#createFrameViewContainer(net.sf.doolin.gui.core.View)
	 */
	public ViewContainer createFrameViewContainer(View view) {
		return new FrameViewContainer();
	}

	/**
	 * Creates a dialog container or wizard container, according to the type of
	 * view. If the view implements the <code>{@link WizardView}</code>
	 * interface, the returned container is a
	 * <code>{@link WizardViewContainer}</code>, if not, this a classic modal
	 * dialog <code>{@link DialogViewContainer}</code>.
	 * 
	 * @see DialogViewContainer
	 * @see WizardViewContainer
	 * @see net.sf.doolin.gui.core.ViewContainerFactory#createDialogViewContainer(net.sf.doolin.gui.core.View)
	 */
	public ViewContainer createDialogViewContainer(View view) {
		if (view instanceof WizardView) {
			return new WizardViewContainer();
		} else {
			return new DialogViewContainer();
		}
	}

}
