/*
 * Created on Oct 3, 2006
 */
package orbe.gui.main;

import net.sf.doolin.gui.core.support.GUIUtils;
import orbe.gui.IViews;
import orbe.gui.context.OrbeContext;
import orbe.model.OrbeMapConfig;

/**
 * New map.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ActionNew.java,v 1.3 2006/10/03 14:19:25 guinnessman Exp $
 */
public class ActionNew extends AbstractActionContext {

	@Override
	protected void process() {
		if (closeCurrent()) {
			newContext();
		}
	}

	protected void newContext() {
		OrbeMapConfig config = new OrbeMapConfig();
		boolean ok = GUIUtils.openDialog(getView(), IViews.ID_DIALOG_NEW, config);
		if (ok) {
			// Creates a map
			OrbeContext context = new OrbeContext(config);
			// Set the new context
			openContext(context);
		}
	}

}
