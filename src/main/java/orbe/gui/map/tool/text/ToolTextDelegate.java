/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.tool.text;

import java.awt.event.MouseEvent;

import orbe.gui.map.tool.ToolText;

/**
 * Cette classe est utilisée pour détacher des tâches de l'outil Texte
 * (déplacement, rotation) à d'autres classes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolTextDelegate.java,v 1.1 2006/11/23 16:22:22 guinnessman Exp $
 */
public abstract class ToolTextDelegate {

	/**
	 * Parent tool
	 */
	private ToolText tool;

	/**
	 * Initialisation.
	 * 
	 * @param tool
	 *            Outil créant la délégation.
	 */
	public ToolTextDelegate(ToolText tool) {
		this.tool = tool;
	}

	/**
	 * Initialisation de la délégation
	 * 
	 * @param e
	 *            Evénément déclenché
	 */
	public abstract void start(MouseEvent e);

	/**
	 * Continuation de la délégation
	 * 
	 * @param e
	 *            Evénément déclenché
	 */
	public abstract void drag(MouseEvent e);

	/**
	 * Fin de la délégation
	 * 
	 * @param e
	 *            Evénément déclenché
	 */
	public abstract void end(MouseEvent e);

	/**
	 * Annulation de la délégation
	 */
	public abstract void cancel();

	/**
	 * @return Parent tool.
	 */
	public ToolText getTool() {
		return tool;
	}

}
