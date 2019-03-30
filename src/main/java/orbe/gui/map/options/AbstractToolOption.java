/*
 * Created on Nov 8, 2006
 */
package orbe.gui.map.options;

import javax.swing.JButton;

import orbe.gui.IViews;
import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;

/**
 * Classe utilitaire pour les options d'outil.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractToolOption.java,v 1.1 2006/11/08 10:50:48 guinnessman
 *          Exp $
 */
public abstract class AbstractToolOption implements ToolOption {

	protected View getMainFrame() {
		return GUIUtils.getViewManager().getOpenedView(IViews.ID_FRAME_MAIN);
	}

	@SuppressWarnings("unchecked")
	protected <A extends Action> A getAction(String id) {
		return (A) Application.getApplication().getBean(id);
	}

	protected JButton createButton(String actionId) {
		View frame = getMainFrame();
		Action action = getAction(actionId);
		javax.swing.Action swingAction = GUIUtils.getSwingFactory().createSwingAction(action, frame, null, IconSize.SMALL);
		return new JButton(swingAction);
	}

}
