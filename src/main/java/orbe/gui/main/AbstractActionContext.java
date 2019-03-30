/*
 * Created on Oct 3, 2006
 */
package orbe.gui.main;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.ConfirmResult;
import orbe.gui.IActions;
import orbe.gui.IStrings;
import orbe.gui.context.OrbeContext;
import orbe.gui.message.OrbeMessageContextChanged;

/**
 * Action that interacts with the context.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractActionContext.java,v 1.1 2006/10/03 13:46:12
 *          guinnessman Exp $
 */
public abstract class AbstractActionContext extends AbstractActionMain {

	protected boolean closeCurrent() {
		OrbeContext current = getContext();
		if (current != null) {
			return tryToCloseCurrent();
		} else {
			return true;
		}
	}

	protected void openContext(OrbeContext ctx) {
		if (getContext() != null) {
			throw new IllegalStateException("The previous context has not been closed.");
		} else {
			setContext(ctx);
		}
	}

	protected void setContext(OrbeContext ctx) {
		getView().setViewData(ctx);
		Bus.get().publish(new OrbeMessageContextChanged(ctx));
	}

	protected boolean tryToCloseCurrent() {
		// Checks the status
		OrbeContext context = getContext();
		if (context.isDirty()) {
			// Asks for action
			ConfirmResult result = GUIUtils.getAlertManager().confirmSave(getView(), IStrings.ACTION_CLOSE_PROMPT);
			if (result == ConfirmResult.SAVE_AND_CLOSE) {
				// Saves
				ActionSave action = (ActionSave) Application.getApplication().getBean(IActions.ACTION_SAVE);
				getView().execute(action);
				// Save has not been successful
				if (context.isDirty()) {
					return false;
				} else {
					return doClose();
				}
			} else if (result == ConfirmResult.CLOSE) {
				return doClose();
			} else {
				return false;
			}
		} else {
			return doClose();
		}
	}

	protected boolean doClose() {
		// Actually close
		setContext(null);
		// Ok
		return true;
	}

}
