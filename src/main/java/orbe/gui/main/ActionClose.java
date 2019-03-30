/*
 * Created on Oct 3, 2006
 */
package orbe.gui.main;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;

/**
 * Closes current context.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionClose.java,v 1.2 2006/10/03 13:57:09 guinnessman Exp $
 */
public class ActionClose extends AbstractActionContext {

	public ActionClose() {
		setEnabled(false);
	}

	@Override
	protected void process() {
		closeCurrent();
	}

	@Override
	protected void onContextChanged() {
		setView(GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN));
		setEnabled(getContext() != null);
	}

}
